/**
 * @Source: NN adapted from https://github.com/neuroph/neuroph/tree/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples
 */

package thesis_neuroph.thesis_neuroph;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Random;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.data.norm.DecimalScaleNormalizer;
import org.neuroph.util.data.norm.MaxMinNormalizer;
import org.neuroph.util.data.norm.MaxNormalizer;
import org.neuroph.util.data.norm.Normalizer;
import org.neuroph.util.random.WeightsRandomizer;


/**
 * This sample shows hot to read network input and write network output to
 * database using Neuroph JDBC adapaters.
 * 
 * @source: Adapted from https://github.com/neuroph/neuroph/blob/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples/XorMultiLayerPerceptronSample.java
 * @source: Adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/jdbc_basic.html
 * 
 */
public class TrainedNeuralNetwork implements LearningEventListener {

	/**
	 * TODO Make variables private and add setters
	 */
	double linesOfCodeTotal;
	double numberOfMethods;
	double keywordMainFound;
	double keywordComparatorFound;
	double keywordNewFound;
	double keywordDoubleFound;
	double keywordFloatFound;
	double outputExpected;
	
	private int numberInputNodes;
	
	/**
	 * @TODO: May want to remove this variable later
	 */
	private Connection dbConnection = null;
	
	double[][] OUTPUT = {{0.1},
						{0.6},
						{0.4},
						{0.7}};

	public TrainedNeuralNetwork(DataPreProcessing preProcessedData) {
		if (preProcessedData.getStudentDataInput().length == 0) {
			/**
			 * @TODO: Fill this in
			 */
		}
		else {
			this.setNumberInputNodes(preProcessedData.getStudentDataInput()[0].length);
		}
		// Used for initially creating/saving the NN
		this.runNN(preProcessedData.getStudentDataInput());
	}

	
	public MultiLayerPerceptron setNN() {
		// create multi layer perceptron
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID,
        									this.getNumberInputNodes(),
        									Constants.NUMBER_HIDDEN_NODES,
        									Constants.NUMBER_OUTPUT_NODES_FROM_NN);
        myMlPerceptron.randomizeWeights(new WeightsRandomizer(new Random(123)));
        
        System.out.println("Weights myMlPerceptron: " + Arrays.toString(myMlPerceptron.getWeights()));

        myMlPerceptron.setLearningRule(new BackPropagation());
      
        myMlPerceptron.getLearningRule().setLearningRate(Constants.LEARNING_RATE);
        myMlPerceptron.getLearningRule().setMaxError(Constants.MAX_ERROR);
        myMlPerceptron.getLearningRule().setMaxIterations(Constants.MAX_ITERATIONS);
        // enable batch if using MomentumBackpropagation
       /* if( myMlPerceptron.getLearningRule() instanceof MomentumBackpropagation )
        	((MomentumBackpropagation)myMlPerceptron.getLearningRule()).setBatchMode(false);*/

        LearningRule learningRule = myMlPerceptron.getLearningRule();
        learningRule.addListener(this);
		
		return myMlPerceptron;
	}
	
	public DataSet setDataForDataSet(double[][] input) {
		// create training set
		DataSet trainingSet = new DataSet(numberInputNodes, 1); 
		/**
		 * @TODO: Process all input - this is just testing for 1 element
		 */
		for (int i = 0; i < input.length; i++) {
			trainingSet.addRow(new DataSetRow(
					input[i], Constants.EXPECTED_OUTPUT_FROM_NN[i]));
		}
		
		return trainingSet;
	}
	
	/**
	 * @source: https://github.com/neuroph/neuroph/blob/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples/NormalizationSample.java
	 * @param inputData
	 */
	public void normalizeData(DataSet inputData) {
		 Normalizer norm = new DecimalScaleNormalizer();
		 norm.normalize(inputData);
		// print out normalized training set
	        for (DataSetRow dataSetRow : inputData.getRows()) {
	            System.out.print("Input: " + Arrays.toString(dataSetRow.getInput()));
	            System.out.println("\tOutput: " + Arrays.toString(dataSetRow.getDesiredOutput()));            
	        }
	}
	
	public void runNN(double[][] inputArrayForNN) {
		DataSet data = this.setDataForDataSet(inputArrayForNN);
		
		this.normalizeData(data);
		
		MultiLayerPerceptron mlPerceptron = this.setNN();
		 // learn the training set
        System.out.println("Training neural network...");
        mlPerceptron.learn(data);

        // test perceptron
        System.out.println("Testing trained neural network in TrainedNeuralNetwork class");
        testNeuralNetwork(mlPerceptron, data);

        // save trained neural network
        mlPerceptron.save(Constants.NEURAL_NETWORK_NAME);

        // load saved neural network
       NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile(Constants.NEURAL_NETWORK_NAME);

        // test loaded neural network
        System.out.println("Testing loaded neural network in TrainedNeuralNetwork class");
        testNeuralNetwork(loadedMlPerceptron, data);
	}
	
	/**
     * @Source: https://github.com/neuroph/neuroph/blob/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples/XorMultiLayerPerceptronSample.java
     * Prints network output for the each element from the specified training set.
     * @param neuralNet neural network
     * @param testSet data set used for testing
     */
    public static void testNeuralNetwork(NeuralNetwork neuralNet, DataSet testSet) {

        for(DataSetRow trainingElement : testSet.getRows()) {
            neuralNet.setInput(trainingElement.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();

            System.out.print("Input testNN in TrainedNeuralNetwork class: " + Arrays.toString(trainingElement.getInput()) );
            System.out.println(" Output testNN in TrainedNeuralNetwork class: " + Arrays.toString(networkOutput) );
        }
    }

	@Override
	/**
	 * @Source: https://github.com/neuroph/neuroph/blob/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples/XorMultiLayerPerceptronSample.java
	 */
	 public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation)event.getSource();
        if (event.getEventType() != LearningEvent.Type.LEARNING_STOPPED)
            System.out.println(bp.getCurrentIteration() + ". iteration : "+ bp.getTotalNetworkError());
    }
	
	public int getNumberInputNodes() {
		return numberInputNodes;
	}


	public void setNumberInputNodes(int numberInputs) {
		numberInputNodes = numberInputs;
	}
	
	
}
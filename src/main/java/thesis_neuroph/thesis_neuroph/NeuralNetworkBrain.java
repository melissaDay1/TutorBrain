/**
 * @Source: NN adapted from https://github.com/neuroph/neuroph/tree/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples
 */

package thesis_neuroph.thesis_neuroph;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Random;

import org.json.JSONArray;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.data.norm.DecimalScaleNormalizer;
import org.neuroph.util.data.norm.Normalizer;
import org.neuroph.util.random.WeightsRandomizer;


public class NeuralNetworkBrain implements LearningEventListener {
	
	private static int numberInputNodes;
	
	/**
	 * Used for reading data from Server
	 * @param serverInput
	 */
	public NeuralNetworkBrain(JSONArray serverInput) {
		/**
		 * @TODO: Implement Factory pattern
		 */
		DataPreProcessing preprocessedData = new DataPreProcessing(serverInput);
		
		if (preprocessedData.getStudentDataInput().length == 0) {
			/**
			 * @TODO: Fill this in
			 */
		}
		else {
			this.setNumberInputNodes(preprocessedData.getStudentDataInput()[0].length);
		}
		// Used for initially creating/saving the NN
		this.trainNeuralNet(preprocessedData.getStudentDataInput());
	}
	
	
	public static void createTrainedNeuralNetwork(JSONArray serverInput) {
		NeuralNetworkBrain trainedNeuralNet = new NeuralNetworkBrain(serverInput);
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
		 * @TODO: Process all input
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
	
	public void trainNeuralNet(double[][] inputArrayForNN) {
		DataSet data = this.setDataForDataSet(inputArrayForNN);
		
		this.normalizeData(data);
		
		MultiLayerPerceptron mlPerceptron = this.setNN();
		 // learn the training set
        System.out.println("Training neural network...");
        mlPerceptron.learn(data);

        // test perceptron
        System.out.println("Testing trained neural network in TrainedNeuralNetwork class");
        testNeuralNetwork(mlPerceptron, data);
        
        mlPerceptron.save(Constants.NEURAL_NETWORK_NAME);

        // save trained neural network
        //getClass().getResource(Constants.NEURAL_NETWORK_NAME);
        
        //ClassLoader classLoader = getClass().getClassLoader();
        
       // mlPerceptron.save(classLoader.getResource(Constants.NEURAL_NETWORK_NAME).getPath());
       // ClassLoader classLoader = getClass().getClassLoader();
		//System.out.println("Before save " + classLoader.getResource(Constants.NEURAL_NETWORK_NAME).getFile());

       // mlPerceptron.save(classLoader.getResource(Constants.NEURAL_NETWORK_NAME).getFile());
        
        // load saved neural network
       //NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile(Constants.NEURAL_NETWORK_NAME);

        // test loaded neural network
        //System.out.println("Testing loaded neural network in TrainedNeuralNetwork class");
        //testNeuralNetwork(loadedMlPerceptron, data);
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

	//@Override
	/**
	 * @Source: https://github.com/neuroph/neuroph/blob/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples/XorMultiLayerPerceptronSample.java
	 */
	 public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation)event.getSource();
        if (event.getEventType() != LearningEvent.Type.LEARNING_STOPPED)
            System.out.println(bp.getCurrentIteration() + ". iteration : "+ bp.getTotalNetworkError());
    }
	
	public static int getNumberInputNodes() {
		return numberInputNodes;
	}

	public void setNumberInputNodes(int numberInputs) {
		numberInputNodes = numberInputs;
	}
}
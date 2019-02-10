/**
 * Copyright 2010 Neuroph Project http://neuroph.sourceforge.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @Source: NN adapted from https://github.com/neuroph/neuroph/tree/master/neuroph-2.9/Samples/src/main/java/org/neuroph/samples
 */

package thesis_neuroph.thesis_neuroph;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class NN implements LearningEventListener {

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
	
	private int NUM_VARIABLES = 8;
	
	private String neuralNetworkName = "trainedTutorData.nnet";
	/**
	 * @TODO: May want to remove this variable later
	 */
	private Connection dbConnection = null;
	
	double[][] OUTPUT = {{0.1},
						{0.6},
						{0.4},
						{0.7}};

	public NN(Connection connectionToDB) {
		String query = "select * from dummyData";
		List<double[]> data = this.processInputFromDB(connectionToDB, query);
		double[][] inputArrayTest = this.addInputDataToArray(data);
		this.runNN(inputArrayTest);
	}

	
	/**
	 * @Source: Adapted from http://www.ntu.edu.sg/home/ehchua/programming/java/jdbc_basic.html
	 * @param connectionToDB
	 * @return
	 */
	public List<double[]> processInputFromDB(Connection connectionToDB, String sqlQuery) {
		List<double[]> inputList = new ArrayList<>();
			//String password = "";
			try (
					// Step 2: Allocate a 'Statement' object in the Connection
					Statement stmt = connectionToDB.createStatement();) {
				// Step 3: Execute a SQL SELECT query, the query result
				// is returned in a 'ResultSet' object.
				//String strSelect = "select * from " + tableName;

				ResultSet rset = stmt.executeQuery(sqlQuery);
				
				int rowCount = 0;
				

				// Step 4: Process the ResultSet by scrolling the cursor forward via next().
				// For each row, retrieve the contents of the cells with getXxx(columnName).
				while (rset.next()) { // Move the cursor to the next row, return false if no more row
					// TODO: Set variables using setters
					// TODO: Put these column names for MySQL tables in the constants file and access via these to make easier to change
					
					this.linesOfCodeTotal = rset.getDouble("LinesOfCodeTotal");
					this.numberOfMethods = rset.getDouble("NumberOfMethods");
					this.keywordMainFound = rset.getDouble("KeywordMainFound");
					this.keywordComparatorFound = rset.getDouble("KeywordComparatorFound");
					this.keywordNewFound = rset.getDouble("KeywordNewFound");
					this.keywordDoubleFound = rset.getDouble("KeywordDoubleFound");
					this.keywordFloatFound = rset.getDouble("KeywordFloatFound");
					this.outputExpected = rset.getDouble("OutputExpected");
					
					inputList.add(new double[] {
							this.linesOfCodeTotal,
							this.numberOfMethods,
							this.keywordMainFound,
							this.keywordComparatorFound,
							this.keywordNewFound,
							this.keywordDoubleFound,
							this.keywordFloatFound,
							this.outputExpected
					});
					
				}
				/*for (double[] dataRow : inputList) {
					System.out.println(Arrays.toString(dataRow));
				} */

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			return inputList;
	}
	
	public double[][] addInputDataToArray(List<double[]> inputListData) {
		/**
		 * @TODO: Check input to make code safe for errors
		 */
		double[] element = inputListData.get(0);
		double[][] inputArray = new double[inputListData.size()][element.length];
		inputArray = inputListData.toArray(inputArray);
		/*for (double[] d : inputArray) {
			System.out.print(Arrays.toString(d));
		}*/
		return inputArray;
	}
	
	

	public MultiLayerPerceptron setNN() {
		// create multi layer perceptron
        MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, NUM_VARIABLES, 7, 1);
        myMlPerceptron.randomizeWeights(new WeightsRandomizer(new Random(123)));
        
        System.out.println(Arrays.toString(myMlPerceptron.getWeights()));

        myMlPerceptron.setLearningRule(new BackPropagation());
      
        myMlPerceptron.getLearningRule().setLearningRate(0.7);
        myMlPerceptron.getLearningRule().setMaxError(0.00001);
        myMlPerceptron.getLearningRule().setMaxIterations(10000);
        // enable batch if using MomentumBackpropagation
       /* if( myMlPerceptron.getLearningRule() instanceof MomentumBackpropagation )
        	((MomentumBackpropagation)myMlPerceptron.getLearningRule()).setBatchMode(false);*/

        LearningRule learningRule = myMlPerceptron.getLearningRule();
        learningRule.addListener(this);
		
		return myMlPerceptron;
	}
	
	public DataSet setDataForDataSet(double[][] input) {
		// create training set
		DataSet trainingSet = new DataSet(NUM_VARIABLES, 1); 
		/**
		 * @TODO: Process all input - this is just testing for 1 element
		 */
		for (int i = 0; i < input.length; i++) {
			trainingSet.addRow(new DataSetRow(
					input[i], OUTPUT[i]));
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
        System.out.println("Testing trained neural network");
        testNeuralNetwork(mlPerceptron, data);

        // save trained neural network
        mlPerceptron.save(this.getNeuralNetworkName());

        // load saved neural network
       NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

        // test loaded neural network
        System.out.println("Testing loaded neural network");
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

            System.out.print("Input: " + Arrays.toString(trainingElement.getInput()) );
            System.out.println(" Output: " + Arrays.toString(networkOutput) );
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
	
	public String getNeuralNetworkName() {
		return neuralNetworkName;
	}
	
	
	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}


	public Connection getDbConnection() {
		return dbConnection;
	}


	public int getNUM_VARIABLES() {
		return NUM_VARIABLES;
	}
	
	
}
package thesis_neuroph.thesis_neuroph;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.data.norm.DecimalScaleNormalizer;
import org.neuroph.util.data.norm.Normalizer;

public class StudentMessageCalculator {
	private String messageForStudent;
	private double[] nnOutput;
	private int messageCode;

	/**
	 * @param neuralNetworkForTutor
	 * @param connectionToDB
	 * @param studentID
	 * @param tableName
	 */
	public StudentMessageCalculator(JSONObject dataOneStudent) {
		/**
		 * @TODO: implement Factory pattern
		 */
		Messages messageOptions = new Messages();
		List<double[]> data = DataPreProcessing.processJSONObject(dataOneStudent);
		if (!data.isEmpty()) {
			double[] element = data.get(0);
			
			try {
				InputStream input = new URL("http://javiergs.com/asu-files/TutorData.nnet").openStream();
				this.loadNeuralNetwork(input, Constants.NEURAL_NETWORK_NAME, 
						element, Constants.NUMBER_INPUT_NODES_TO_NN);
				System.out.println("Neural network loaded successfully");
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}
		
		long msgCode = this.calculateMessageCode();
		/**
		 * @TODO: Uncomment when doing the training
		 */
		this.setMessageToDisplay(messageOptions, msgCode);
		this.setMessageCode(msgCode);
		//this.setMessageToDisplay(messageOptions, 60);
		//this.setMessageCode(60);
		System.out.println("Message Code: " + msgCode);
		System.out.println("Message for Student: " + this.getMessageForStudent());
	}

	public double[] loadNeuralNetwork(InputStream neuralNetInputStream, String neuralNetworkName, double[] studentData, 
			int numInputNodes) {
		// load saved neural network
		NeuralNetwork neuralNetwork = NeuralNetwork.load(neuralNetInputStream);
		
		
		//NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(neuralNetworkName);

		// test loaded neural network
		System.out.println("Testing loaded neural network in StudentMessageCalculator:");
		
		// Normalize input data
		DataSet data = this.setDataForDataSet(studentData, numInputNodes);
		
		/**
		 * @TODO: Factory Pattern
		 */
		Normalizer norm = new DecimalScaleNormalizer();
		norm.normalize(data);
		// print out normalized training set
		 System.out.println("Normalized data in StudentMessageCalculator:");
	        //Used for testing & debugging
		 	for (DataSetRow dataSetRow : data.getRows()) {
	            System.out.print("Normalized Input in StudentMessageCalculator:: " + Arrays.toString(dataSetRow.getInput()));
	            System.out.println("\tOutput in StudentMessageCalculator:: " + Arrays.toString(dataSetRow.getDesiredOutput()));            
	        }
		 	
		 double[] normalizedStudentInputData = data.getRowAt(0).getInput();
		
		neuralNetwork.setInput(normalizedStudentInputData);
		neuralNetwork.calculate();
		double[] networkOutput = neuralNetwork.getOutput();
		
		this.setNnOutput(networkOutput);

		System.out.print("Input in StudentMessageCalculator: " + Arrays.toString(normalizedStudentInputData));
		System.out.println(" Output in StudentMessageCalculator: " + Arrays.toString(networkOutput));
		
		return networkOutput;
	}
	
	public DataSet setDataForDataSet(double[] input, int numInputs) {
		// create training set
		/**
		 * @TODO: Factory Pattern
		 */
		DataSet trainingSet = new DataSet(numInputs);
		trainingSet.addRow(new DataSetRow(input));
		
		return trainingSet;
	}
	
	public long calculateMessageCode() {
		double nnOutputRaw = this.getNnOutput()[0];
		double nnOutput = 0;
		// Output is so small (in reality, bad) that special calculations need to be made
		if (nnOutputRaw < 0.1) {
			nnOutput = nnOutputRaw * 1000;
		}
		else {
			nnOutput = this.getNnOutput()[0] * 100; // Used for conversion to expected error code
		}
		long messageCode = Math.round(nnOutput / 10.0) * 10;
		return messageCode;
	}
	
	public void setMessageToDisplay(Messages messagesChoices, long messageCode) {
		String message = Constants.MESSAGES_BY_NUM.get((int) messageCode);
		//String message = messagesChoices.getMessages().get((int) messageCode);
		this.setMessageForStudent(message);
	}

	public double[] getNnOutput() {
		return nnOutput;
	}

	public void setNnOutput(double[] nnOutput) {
		this.nnOutput = nnOutput;
	}

	public String getMessageForStudent() {
		return messageForStudent;
	}

	public void setMessageForStudent(String messageForStudent) {
		this.messageForStudent = messageForStudent;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(long messageCode) {
		this.messageCode = (int) messageCode;
	}
	
	
}
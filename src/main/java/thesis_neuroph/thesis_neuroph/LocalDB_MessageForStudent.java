package thesis_neuroph.thesis_neuroph;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.util.data.norm.DecimalScaleNormalizer;
import org.neuroph.util.data.norm.Normalizer;

/**
 * This is used to calculate a message for the student when connecting to the local
 * MySQL DB without using the Server
 * @author Melissa
 *
 */
public class LocalDB_MessageForStudent {
	private String messageForStudent;
	private double[] nnOutput;

	/**
	 * @TODO: When connection is established from plug-in side, can delete the NN parameter
	 * @param neuralNetworkForTutor
	 * @param connectionToDB
	 * @param studentID
	 * @param tableName
	 */
	public LocalDB_MessageForStudent(LocalDB_NN neuralNetworkForTutor, Connection connectionToDB, int studentID, String tableName) {
		Messages messageOptions = new Messages();
		String queryForStudent = "select * from " + tableName + " where id = " + studentID;
		/**
		 * @TODO: May need to update this parameter to not get the connection from the NN class;
		 * instead should be given as a parameter
		 */
		List<double[]> data = neuralNetworkForTutor.processInputFromDB(neuralNetworkForTutor.getDbConnection(),
				queryForStudent);
		if (!data.isEmpty()) {
			double[] element = data.get(0);
			this.loadNeuralNetwork(neuralNetworkForTutor.getNeuralNetworkName(), 
					element, neuralNetworkForTutor.getNUM_VARIABLES());
		}
		
		long msgCode = this.calculateMessageCode();
		this.setMessageToDisplay(messageOptions, msgCode);
		System.out.println("Message Code: " + msgCode);
		System.out.println("Message for Student: " + this.getMessageForStudent());
	}

	public double[] loadNeuralNetwork(String neuralNetworkName, double[] studentData, int numInputVariables) {
		// load saved neural network
		NeuralNetwork neuralNetwork = NeuralNetwork.createFromFile(neuralNetworkName);

		// test loaded neural network
		System.out.println("Testing loaded neural network");
		
		// Normalize input data
		DataSet data = this.setDataForDataSet(studentData, numInputVariables);
		
		Normalizer norm = new DecimalScaleNormalizer();
		norm.normalize(data);
		// print out normalized training set
		 System.out.println("Normalized data");
	        //Used for testing & debugging
		 	for (DataSetRow dataSetRow : data.getRows()) {
	            System.out.print("Input: " + Arrays.toString(dataSetRow.getInput()));
	            System.out.println("\tOutput: " + Arrays.toString(dataSetRow.getDesiredOutput()));            
	        }
		 	
		 double[] normalizedStudentInputData = data.getRowAt(0).getInput();
		
		neuralNetwork.setInput(normalizedStudentInputData);
		neuralNetwork.calculate();
		double[] networkOutput = neuralNetwork.getOutput();
		
		this.setNnOutput(networkOutput);

		System.out.print("Input: " + Arrays.toString(normalizedStudentInputData));
		System.out.println(" Output: " + Arrays.toString(networkOutput));
		
		return networkOutput;
	}
	
	public DataSet setDataForDataSet(double[] input, int numInputs) {
		// create training set
		DataSet trainingSet = new DataSet(numInputs);
		trainingSet.addRow(new DataSetRow(input));
		
		return trainingSet;
	}
	
	public long calculateMessageCode() {
		double nnOutput = this.getNnOutput()[0] * 100; // Used for conversion to expected error code
		long messageCode = Math.round(nnOutput / 10.0) * 10;
		return messageCode;
	}
	
	public void setMessageToDisplay(Messages messagesChoices, long messageCode) {
		String message = messagesChoices.getMessages().get((int) messageCode);
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
}
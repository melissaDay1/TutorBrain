package thesis_neuroph.thesis_neuroph;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class TestingDriver {
	public static void main(String[] args) {
		// Used for testing local MySQL DB neural network message generation without Server connection
		/*TempConnection dbConnection = new TempConnection();
		NN neuralNetwork = new NN(dbConnection.getDbConnection());
		neuralNetwork.setDbConnection(dbConnection.getDbConnection());
		System.out.println("\n\nTESTING");
		MessageForStudent valueFromNN = new MessageForStudent(neuralNetwork, 
				neuralNetwork.getDbConnection(), 400, "dummyData"); */
		
		
		/**
		 * @TODO: Remove this connection when integrating Brain with plug-in
		 */
		ServerConnection serverConn = new ServerConnection();
		try {
			JSONArray jsonArrayData = serverConn.getStudentData();
			JSONObject studentObj = serverConn.getDataOneStudent(jsonArrayData, 0);
			/**
			 * @TODO: End part to delete
			 */
			
			/**
			 * @TODO: Remove this section when integrating with plug-in
			 * Plug-in assumes that a trained NN already exists
			 */
			NeuralNetworkBrain trainedNeuralNet = new NeuralNetworkBrain(jsonArrayData);
			/**
			 * @TODO: End part to delete
			 */
			
			TutorBrain brain = new TutorBrain(jsonArrayData);
			brain.getMessage(studentObj);
			brain.getMessageCode(studentObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

package thesis_neuroph.thesis_neuroph;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This is used for testing the neural network locally before integration with the plug-in.
 * This logic will be handled by the plug-in
 * @author Melissa
 *
 */
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
			//JSONArray jsonArrayData = serverConn.getStudentData();
			//JSONObject studentObj1 = serverConn.getDataOneStudent(jsonArrayData, 0);
			
			
			String path = "C:\\Users\\Melissa\\Documents\\Software_Engineering_Degree\\Thesis\\Documentation\\StudyWithStudents\\AI\\"
					+ "DataTrainedInitial.txt";
			JSONArray jsonArrayLocalTraining = LocalDataReaderTrainingData.getLocalJSONArray(path);
			JSONObject studentObj1 = LocalDataReaderTrainingData.getDataOneStudent(jsonArrayLocalTraining, 0);

			
			
			/*JSONObject studentObj2 = serverConn.getDataOneStudent(jsonArrayData, 1);
			JSONObject studentObj3 = serverConn.getDataOneStudent(jsonArrayData, 2);
			JSONObject studentObj4 = serverConn.getDataOneStudent(jsonArrayData, 3);
			JSONObject studentObj5 = serverConn.getDataOneStudent(jsonArrayData, 4);
			JSONObject studentObj6 = serverConn.getDataOneStudent(jsonArrayData, 5);*/


			/**
			 * @TODO: End part to delete
			 */
			
			/**
			 * @TODO: Remove this section when integrating with plug-in
			 * Plug-in assumes that a trained NN already exists
			 */
			NeuralNetworkBrain trainedNeuralNet = new NeuralNetworkBrain(jsonArrayLocalTraining);
			/**
			 * @TODO: End part to delete
			 */
			
			TutorBrain brain = new TutorBrain();
			JSONObject messageObj1 = brain.getMessage(studentObj1);
			System.out.println("\n" + messageObj1.toString());
			
			/*JSONObject messageObj2 = brain.getMessage(studentObj2);
			System.out.println("\n" + messageObj2.toString());
			
			JSONObject messageObj3 = brain.getMessage(studentObj3);
			System.out.println("\n" + messageObj3.toString());
			
			JSONObject messageObj4 = brain.getMessage(studentObj4);
			System.out.println("\n" + messageObj4.toString());
			
			JSONObject messageObj5 = brain.getMessage(studentObj5);
			System.out.println("\n" + messageObj5.toString());
			
			JSONObject messageObj6 = brain.getMessage(studentObj6);
			System.out.println("\n" + messageObj6.toString()); */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

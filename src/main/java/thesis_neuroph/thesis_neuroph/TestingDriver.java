package thesis_neuroph.thesis_neuroph;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class TestingDriver {
	public static void main(String[] args) {
		/**
		 * @TODO: Remove TempConnection object when integrating with plug-in
		 */
		//TempConnection dbConnection = new TempConnection();
		
		//NN neuralNetwork = new NN(dbConnection.getDbConnection());
		/**
		 * @TODO: Probably will need to remove the setDbConnection later
		 */
		//neuralNetwork.setDbConnection(dbConnection.getDbConnection());
		//System.out.println("\n\nTESTING");
		
		
		
		
		
		/*MessageForStudent valueFromNN = new MessageForStudent(neuralNetwork, 
				neuralNetwork.getDbConnection(), 400, "dummyData"); */
		
		
		ServerConnection serverConn = new ServerConnection();
		try {
			JSONArray jsonArrayData = serverConn.getStudentData();
			JSONObject studentObj = serverConn.getDataOneStudent(jsonArrayData, 0);
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

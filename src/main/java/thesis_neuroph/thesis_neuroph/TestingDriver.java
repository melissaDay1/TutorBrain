package thesis_neuroph.thesis_neuroph;

public class TestingDriver {
	public static void main(String[] args) {
		/**
		 * @TODO: Remove TempConnection object when integrating with plug-in
		 */
		TempConnection dbConnection = new TempConnection();
		
		NN neuralNetwork = new NN(dbConnection.getDbConnection());
		/**
		 * @TODO: Probably will need to remove the setDbConnection later
		 */
		neuralNetwork.setDbConnection(dbConnection.getDbConnection());
		System.out.println("\n\nTESTING");
		
		
		
		
		
		MessageForStudent valueFromNN = new MessageForStudent(neuralNetwork, 
				neuralNetwork.getDbConnection(), 400, "dummyData");
	}
}

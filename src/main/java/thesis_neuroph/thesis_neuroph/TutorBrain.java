package thesis_neuroph.thesis_neuroph;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;

public class TutorBrain {
	private String messageFromBrain = null;
	private int messageCodeFromBrain = -1;
	private TrainedNeuralNetwork trainedNN;
	private DataPreProcessing preprocessedData;
	
	public TutorBrain(JSONArray serverInput) {
		/**
		 * @TODO: Implement Factory pattern
		 */
		this.preprocessedData = new DataPreProcessing(serverInput);
		
		
		/**
		 * @TODO: This actually performs the training - will need to update 
		 * TrainedNeuralNetwork class later
		 */
		this.trainedNN = new TrainedNeuralNetwork(preprocessedData);
		
		System.out.println("\n\nEnd TutorBrain constructor");
		/**
		 * @TODO: Delete this section when integrating
		 */
		/*ServerConnection serverConn = new ServerConnection();
		StudentMessageCalculator messageInfo;
		try {
			JSONObject studentDataObject = serverConn.getDataOneStudent(serverInput, 0);
			messageInfo = new StudentMessageCalculator(
					trainedNN, studentDataObject, preprocessedData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		// **** End delete section
		
		
	}
	
	protected void calculateMessage(JSONObject dataForOneStudent) {
		StudentMessageCalculator messageInfo = new StudentMessageCalculator(
				this.getTrainedNN(), dataForOneStudent, this.getPreprocessedData());
		
		this.setMessageCodeFromBrain(messageInfo.getMessageCode());
		this.setMessageFromBrain(messageInfo.getMessageForStudent());
	}
	
	public String getMessage(JSONObject dataForOneStudent) {
		this.calculateMessage(dataForOneStudent);
		return this.getMessageFromBrain();
	}
	
	public int getMessageCode(JSONObject dataForOneStudent) {
		if (this.getMessageCodeFromBrain() < 0) {
			this.calculateMessage(dataForOneStudent);
			return this.getMessageCodeFromBrain();
		}
		else {
			return this.getMessageCodeFromBrain();
		}
	}

	protected String getMessageFromBrain() {
		return messageFromBrain;
	}

	protected void setMessageFromBrain(String messageFromBrain) {
		this.messageFromBrain = messageFromBrain;
	}

	protected int getMessageCodeFromBrain() {
		return messageCodeFromBrain;
	}

	protected void setMessageCodeFromBrain(int messageCodeFromBrain) {
		this.messageCodeFromBrain = messageCodeFromBrain;
	}

	protected TrainedNeuralNetwork getTrainedNN() {
		return trainedNN;
	}

	protected DataPreProcessing getPreprocessedData() {
		return preprocessedData;
	}
}

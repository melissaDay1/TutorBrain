package thesis_neuroph.thesis_neuroph;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;

public class TutorBrain {
	private String messageFromBrain = null;
	private int messageCodeFromBrain = -1;
	private DataPreProcessing preprocessedData;
	
	/**
	 * This constructor used when Neural Network is already trained
	 */
	public TutorBrain() {
		
	}
	
	/**
	 * Used when Neural Network will be trained
	 * @param serverInput
	 */
	public TutorBrain(JSONArray serverInput) {
		NeuralNetworkBrain trainedNeuralNet = new NeuralNetworkBrain(serverInput);
	}
	
	protected void calculateMessage(JSONObject dataForOneStudent) {
		StudentMessageCalculator messageInfo = new StudentMessageCalculator(dataForOneStudent);
		
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

	protected DataPreProcessing getPreprocessedData() {
		return preprocessedData;
	}
}

package thesis_neuroph.thesis_neuroph;

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
	 * Used when you need to train the Neural Network
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
	
	public JSONObject getMessage(JSONObject dataForOneStudent) {
		JSONObject messageObject = new JSONObject();
		this.calculateMessage(dataForOneStudent);
		String message = this.getMessageFromBrain();
		int messageCode = this.getMessageCodeFromBrain();
		
		messageObject.put("message", message);
		messageObject.put("messageCode", messageCode);
		
		return messageObject;
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

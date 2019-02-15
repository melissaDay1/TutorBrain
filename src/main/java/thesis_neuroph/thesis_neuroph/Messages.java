package thesis_neuroph.thesis_neuroph;

import java.util.HashMap;

public class Messages {
	private HashMap<Integer, String> messages;
	
	/**
	 * TODO: Change messages
	 */
	private String hint1 = "Add main";
	private String hint2 = "Add new";
	private String hint3 = "Use 'if'";
	private String hint4 = "I don't know how to help you";
	private String hint5 = "Add more methods";
	private String hint6 = "Use a double data type";
	private String hint7 = "Use comparator operator";
	
	public Messages() {
		messages = new HashMap<>();
		this.setMessages();
	}

	public void setMessages() {
		messages.put(10, hint1);
		messages.put(20, hint2);
		messages.put(30, hint3);
		messages.put(40, hint4);
		messages.put(50, hint5);
		messages.put(60, hint6);
		messages.put(70, hint7);
	}

	public HashMap<Integer, String> getMessages() {
		return messages;
	}
	
	

}

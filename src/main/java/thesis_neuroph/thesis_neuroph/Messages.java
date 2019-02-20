package thesis_neuroph.thesis_neuroph;

import java.util.HashMap;

/**
 * Messages to be given from the Brain to th plug-in
 * @author Melissa
 *
 */

public class Messages {
	private HashMap<Integer, String> messages;
	
	/**
	 * TODO: Change messages
	 */
	private String noReturn = "Remember to return a value.";
	private String success = "Way to go! You finished!";
	private String noLoops = "Recursion means no loops should be used.";
	private String noCodeWritten = "It doesn't look like you've written any code "
			+ "yet. Re-think that before you click that button.";
	private String help = "How can I help you?";
	private String cannotHelp = "I'm sorry, I am not able to help you right now.";
	private String hint7 = "Use comparator operator";
	
	public Messages() {
		messages = new HashMap<Integer, String>();
		this.setMessages();
	}

	/**
	 * Sets the HashMap with the key-value pairs for the messages
	 */
	public void setMessages() {
		messages.put(10, noReturn);
		messages.put(20, success);
		messages.put(30, noLoops);
		messages.put(40, noCodeWritten);
		messages.put(50, help);
		messages.put(60, cannotHelp);
		messages.put(70, hint7);
	}

	public HashMap<Integer, String> getMessages() {
		return messages;
	}
}
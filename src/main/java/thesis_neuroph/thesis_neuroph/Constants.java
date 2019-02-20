package thesis_neuroph.thesis_neuroph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * These are the Constants for the Brain
 * @author Melissa
 *
 */

public class Constants {
	public static final Map<String, Double> ERROR_MESSAGES = setErrorsHashmap();
	public static final Map<String, Double> ACTIONS = setActionsHashmap();
	public static final Map<Integer, String> MESSAGES_BY_NUM = setMessagesByNumHashmap();
	public static final Map<String, Double> MESSAGES_BY_STRING = setMessagesByStringHashmap();


	public static Map<String, Double> setErrorsHashmap() {
		Map<String, Double> errors = new HashMap<String, Double>();
		errors.put("StackOverflowError", 1.0);
		errors.put("cannot find symbol", 2.0);
		errors.put("{ expected", 3.0);
		errors.put("} expected", 4.0);
		errors.put("; expected", 5.0);
		errors.put("<identifier> expected", 6.0);
		errors.put("illegal start of expression", 7.0);
		errors.put("invalid method declaration; return type required", 8.0);
		errors.put("Other", 9.0);
		errors.put("none", 10.0);
		errors.put("IndexOutOfBoundException", 11.0);
		return Collections.unmodifiableMap(errors);
	}
	
	public static Map<String, Double> setActionsHashmap() {
		Map<String, Double> actions = new HashMap<String, Double>();
		actions.put("TIMER", 1.0);
		actions.put("RUN", 2.0);
		actions.put("DEBUG", 3.0);
		actions.put("ERROR", 4.0);
		actions.put("SUBMIT", 5.0);
		actions.put("HELP", 6.0);
		return Collections.unmodifiableMap(actions);
	}
	
	public static Map<Integer, String> setMessagesByNumHashmap() {
		Map<Integer, String> messages = new HashMap<Integer, String>();
		messages.put(10, "Remember to return a value.");
		messages.put(20, "Way to go! You finished!");
		messages.put(30, "Recursion means no loops should be used.");
		messages.put(40, "It doesn't look like you've written any code "
				+ "yet. Re-think that before you click that button.");
		messages.put(50, "How can I help you?");
		messages.put(60, "I'm sorry, I am not able to help you right now.");
		messages.put(70, "Use comparator operator");
		return Collections.unmodifiableMap(messages);
	}
	
	public static Map<String, Double> setMessagesByStringHashmap() {
		Map<String, Double> messages = new HashMap<String, Double>();
		messages.put("Remember to return a value.", 10.0);
		messages.put("Way to go! You finished!", 20.0);
		messages.put("Recursion means no loops should be used.", 30.0);
		messages.put("It doesn't look like you've written any code "
				+ "yet. Re-think that before you click that button.", 40.0);
		messages.put("How can I help you?", 50.0);
		messages.put("I'm sorry, I am not able to help you right now.", 60.0);
		messages.put("Use comparator operator", 70.0);
		return Collections.unmodifiableMap(messages);
	}

	//private static int numberInputNodesToNN = 0;
	public static final double[][] EXPECTED_OUTPUT_FROM_NN = {{40},
											{40},
											{50},
											{30},
											{10},
											{20}};
	public static final int NUMBER_OUTPUT_NODES_FROM_NN = 1;
	public static final int NUMBER_HIDDEN_NODES = 7;
	
	public static final double LEARNING_RATE = 0.7;
	public static final double MAX_ERROR = 0.00001;
	public static final int MAX_ITERATIONS = 10000;
	
	public static final String NEURAL_NETWORK_NAME = "trainedTutorData.nnet";
	
	public enum Action {
		TIMER,
		RUN,
		DEBUG,
		ERROR,
		SUBMIT,
		HELP
	}
	
}

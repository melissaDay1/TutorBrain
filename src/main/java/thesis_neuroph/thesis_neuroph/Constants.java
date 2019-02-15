package thesis_neuroph.thesis_neuroph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final Map<String, Double> ERROR_MESSAGES = setErrorsHashmap();

	public static Map<String, Double> setErrorsHashmap() {
		Map<String, Double> errors = new HashMap<>();
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

	//public static final int NUMBER_INPUT_NODES_TO_NN = 16;
	public static final double[][] EXPECTED_OUTPUT_FROM_NN = {{0.1},
											{0.6},
											{0.4},
											{0.7}};
	public static final int NUMBER_OUTPUT_NODES_FROM_NN = 1;
	public static final int NUMBER_HIDDEN_NODES = 7;
	
	public static final double LEARNING_RATE = 0.7;
	public static final double MAX_ERROR = 0.00001;
	public static final int MAX_ITERATIONS = 10000;
	
	public static final String NEURAL_NETWORK_NAME = "trainedTutorData.nnet";

}

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
	public static final Map<String, Double> ACTIONS = setActionsHashmap();
	public static final Map<Integer, String> MESSAGES_BY_NUM = setMessagesByNumHashmap();
	public static final Map<String, Double> MESSAGES_BY_STRING = setMessagesByStringHashmap();

	public static final int NUMBER_ERORR_MESSAGES = 7;
	
	public static final String[] ERROR_MESSAGE_NAMES = setErrorMessageNames();
	public static final Map<String, Double> ERROR_MESSAGES = setErrorsHashmap();

	public static final String STACKOVERFLOW_ERROR = "StackOverflowError";
	public static final String ARRAYINDEX_OUTOFBOUNDS = "ArrayIndexOutOfBoundsException";
	public static final String NULLPOINTER_EXCEPTION = "NullPointerException";
	public static final String ARITHMETIC_EXCEPTION = "ArithmeticException";
	public static final String COMPILATION_ISSUE = "UnresolvedCompilationIssue";
	public static final String NONE_ERROR = "None";
	public static final String OTHER_ERROR = "Other";

	public static final String NO_HELP_NOW_MESSAGE = "Sorry, I don't have any "
			+ "messages for your right now. Try again later." ;
	public static final String RECURSION_ISSUE_MESSAGE = "Think about breaking the "
			+ "problem into smaller parts. Ask yourself: What is the "
			+ "smallest problem I'm trying to solve?" ;
	public static final String ENCOURAGEMENT_DEBUG_MESSAGE = "Good thinking - Using "
			+ "the debug tool is very helpful for problems with recursion." ;
	public static final String SUCCESS_MESSAGE = "Nice job! You got the "
			+ "right answer!" ;
	public static final String CYCLOMATIC_COMPLEXITY_ISSUE_MESSAGE = "Think "
			+ "about changing the control flow of your code." ;
	public static final String IF_MISSING_MESSAGE = "You're missing an "
			+ "important part for recursion. Think about using a keyword." ;
	public static final String COMPARATOR_MISSING_MESSAGE = "Try checking the "
			+ "value of something in your code to determine how the "
			+ "recursion unfolds." ;
	public static final String COMMENT_LINES_ISSUE_MESSAGE = "Remember to "
			+ "clean up your code! That's a lot of comment lines." ;
	public static final String LOOP_ISSUE_MESSAGE = "Uh-oh! I think your code "
			+ "using a feature that shouldn't be there in recursion. "
			+ "Review the concept of recursion." ;
	public static final String RETURN_ISSUE_MESSAGE = "I'm looking for an "
			+ "important keyword in your code, but I think it's missing." ;
	public static final String KEYWORD_NEW_ISSUE_MESSAGE = "I think I see a "
			+ "keyword in your code that's not really needed for this type of situation." ;
	public static final String LOC_ISSUE_MESSAGE = "That's a lot of code in "
			+ "this method! You might want to think about shortening it "
			+ "or double-checking what you're doing." ;
	public static final String STACKOVERFLOW_ISSUE_MESSAGE = "Don't worry, "
			+ "StackOverflowError is a really common error with recursion. "
			+ "That's normal. Check that you reach your base case." ;
	public static final String ARRAYINDEXOUTOFBOUNDS_ISSUE_MESSAGE = "Software "
			+ "engineers get the ArrayIndexOutOfBoundsException quite a bit. "
			+ "It's a frustrating one. Using the debugger might help you." ;
	public static final String UNRESOLVED_COMPILATION_ERROR_MESSAGE = "I think "
			+ "you're missing some important syntax in your code." ;
	public static final String NO_ERRORS_MESSAGE = "Nice! No errors "
			+ "were found when you ran your code!" ;
	
	
	public static final String TOO_MUCH_CODE_MESSAGE = "It looks like you're "
			+ "on the right track. That seems like a lot of code, though. "
			+ "It probably does not need to be that long." ;
	public static final String  GOOD_IN_PROGRESS_MESSAGE = "Nice work! Keep it up.";
	public static final String HELP_REQUESTED_MESSAGE = "I'm here to help you. Once "
			+ "you take an action with your code, I can help you with a hint." ;
	public static final String CC_TOO_HIGH_MESSAGE = "That code looks pretty complex. "
			+ "Think about simplifying the logic a bit." ;
	public static final String ERROR_IDK_MESSAGE = "Hmmm, it looks like you should "
			+ "re-think something in your code." ;
	public static final String ARITH_ERROR_MESSAGE = "I think there's a "
			+ "problem with something with your math." ;
	public static final String NOT_ENOUGH_CODE_MESSAGE = "Keep going. It looks"
			+ " like you'll need some more code." ;
	public static final String EXTRA_KEYWORDS_MESSAGE = "I see some extra"
			+ " keywords that don't need to be used for this. Think"
			+ " about which ones you may not need." ;
	public static final String ARITH_ERROR_BUT_GOOD_MESSAGE = "You're really "
			+ "close. Just check your mathematical operations." ;
	public static final String UNRESOLVED_COMPILATION_ERROR_BUT_GOOD_MESSAGE = "Almost. "
			+ "I think you're missing some syntax, though." ;
	public static final String NO_BASE_CASE_MESSAGE = "This is common "
			+ "with recursion. Think about creating a base case." ;
	public static final String NO_CODE_WRITTEN_MESSAGE = "Try writing"
			+ " some code first, then I can help you." ;
	
	
	public static String[] setErrorMessageNames() {
		String[] messageNames = new String[NUMBER_ERORR_MESSAGES];
		messageNames[0] = STACKOVERFLOW_ERROR;
		messageNames[1] = ARRAYINDEX_OUTOFBOUNDS;
		messageNames[2] = NULLPOINTER_EXCEPTION;
		messageNames[3] = ARITHMETIC_EXCEPTION;
		messageNames[4] = COMPILATION_ISSUE;
		messageNames[5] = NONE_ERROR;
		messageNames[6] = OTHER_ERROR;
		return messageNames;
	}
	
	public static Map<String, Double> setErrorsHashmap() {
		Map<String, Double> errors = new HashMap<String, Double>();
		errors.put(ERROR_MESSAGE_NAMES[0], 1.0);
		errors.put(ERROR_MESSAGE_NAMES[1], 2.0);
		errors.put(ERROR_MESSAGE_NAMES[2], 3.0);
		errors.put(ERROR_MESSAGE_NAMES[3], 4.0);
		errors.put(ERROR_MESSAGE_NAMES[4], 5.0);
		errors.put(ERROR_MESSAGE_NAMES[5], 6.0);
		errors.put(ERROR_MESSAGE_NAMES[6], 7.0);
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
		messages.put(10, NO_HELP_NOW_MESSAGE);
		messages.put(20, RECURSION_ISSUE_MESSAGE);
		messages.put(30, ENCOURAGEMENT_DEBUG_MESSAGE);
		messages.put(40, SUCCESS_MESSAGE);
		messages.put(50, CYCLOMATIC_COMPLEXITY_ISSUE_MESSAGE);
		messages.put(60, IF_MISSING_MESSAGE);
		messages.put(70, COMPARATOR_MISSING_MESSAGE);
		messages.put(80, COMMENT_LINES_ISSUE_MESSAGE);
		messages.put(90, LOOP_ISSUE_MESSAGE);
		messages.put(100, RETURN_ISSUE_MESSAGE);
		messages.put(110, KEYWORD_NEW_ISSUE_MESSAGE);
		messages.put(120, LOC_ISSUE_MESSAGE);
		messages.put(130, STACKOVERFLOW_ISSUE_MESSAGE);
		messages.put(140, ARRAYINDEXOUTOFBOUNDS_ISSUE_MESSAGE);
		messages.put(150, UNRESOLVED_COMPILATION_ERROR_MESSAGE);
		messages.put(160, NO_ERRORS_MESSAGE);
		messages.put(170, TOO_MUCH_CODE_MESSAGE);
		messages.put(180, GOOD_IN_PROGRESS_MESSAGE);
		messages.put(190, HELP_REQUESTED_MESSAGE);
		messages.put(200, CC_TOO_HIGH_MESSAGE);
		messages.put(210, ERROR_IDK_MESSAGE);
		messages.put(220, ARITH_ERROR_MESSAGE);
		messages.put(230, NOT_ENOUGH_CODE_MESSAGE);
		messages.put(240, EXTRA_KEYWORDS_MESSAGE);
		messages.put(250, ARITH_ERROR_BUT_GOOD_MESSAGE);
		messages.put(260, UNRESOLVED_COMPILATION_ERROR_BUT_GOOD_MESSAGE);
		messages.put(270, NO_BASE_CASE_MESSAGE);
		messages.put(280, NO_CODE_WRITTEN_MESSAGE);

		return Collections.unmodifiableMap(messages);
	}
	
	public static Map<String, Double> setMessagesByStringHashmap() {
		Map<String, Double> messages = new HashMap<String, Double>();
		messages.put(NO_HELP_NOW_MESSAGE, 10.0);
		messages.put(RECURSION_ISSUE_MESSAGE, 20.0);
		messages.put(ENCOURAGEMENT_DEBUG_MESSAGE, 30.0);
		messages.put(SUCCESS_MESSAGE, 40.0);
		messages.put(CYCLOMATIC_COMPLEXITY_ISSUE_MESSAGE, 50.0);
		messages.put(IF_MISSING_MESSAGE, 60.0);
		messages.put(COMPARATOR_MISSING_MESSAGE, 70.0);
		messages.put(COMMENT_LINES_ISSUE_MESSAGE, 80.0);
		messages.put(LOOP_ISSUE_MESSAGE, 90.0);
		messages.put(RETURN_ISSUE_MESSAGE, 100.0);
		messages.put(KEYWORD_NEW_ISSUE_MESSAGE, 110.0);
		messages.put(LOC_ISSUE_MESSAGE, 120.0);
		messages.put(STACKOVERFLOW_ISSUE_MESSAGE, 130.0);
		messages.put(ARRAYINDEXOUTOFBOUNDS_ISSUE_MESSAGE, 140.0);
		messages.put(UNRESOLVED_COMPILATION_ERROR_MESSAGE, 150.0);
		messages.put(NO_ERRORS_MESSAGE, 160.0);
		messages.put(TOO_MUCH_CODE_MESSAGE, 170.0);
		messages.put(GOOD_IN_PROGRESS_MESSAGE, 180.0);
		messages.put(HELP_REQUESTED_MESSAGE, 190.0);
		messages.put(CC_TOO_HIGH_MESSAGE, 200.0);
		messages.put(ERROR_IDK_MESSAGE, 210.0);
		messages.put(ARITH_ERROR_MESSAGE, 220.0);
		messages.put(NOT_ENOUGH_CODE_MESSAGE, 230.0);
		messages.put(EXTRA_KEYWORDS_MESSAGE, 240.0);
		messages.put(ARITH_ERROR_BUT_GOOD_MESSAGE, 250.0);
		messages.put(UNRESOLVED_COMPILATION_ERROR_BUT_GOOD_MESSAGE, 260.0);
		messages.put(NO_BASE_CASE_MESSAGE, 270.0);
		messages.put(NO_CODE_WRITTEN_MESSAGE, 280.0);
		
		return Collections.unmodifiableMap(messages);
	}

	
	public static final int NUMBER_INPUT_NODES_TO_NN = 16;
	
	public static final double[][] EXPECTED_OUTPUT_FROM_NN = 
		{{130},
			{30},
			{200},
			{210},
			{100},
			{70},
			{90},
			{150},
			{220},
			{90},
			{170},
			{140},
			{190},
			{220},
			{50},
			{110},
			{90},
			{150},
			{230},
			{240},
			{250},
			{240},
			{250},
			{270},
			{140},
			{280},
			{270},
			{250},
			{70},
			{260},
			{170},
			{220},
			{60},
			{130},
			{140},
			{70},
			{60},
			{130},
			{190},
			{100},
			{170},
			{90},
			{90},
			{60},
			{230},
			{60},
			{130},
			{60},
			{150},
			{100},
			{60},
			{30},
			{90},
			{200},
			{200},
			{50},
			{80},
			{170},
			{50},
			{200},
			{90},
			{30},
			{260},
			{230},
			{60},
			{140},
			{170},
			{190},
			{190},
			{150},
			{130},
			{60},
			{40},
			{70},
			{30},
			{210},
			{40},
			{60},
			{60},
			{220},
			{190},
			{70},
			{60},
			{90},
			{70},
			{100},
			{210},
			{90},
			{230},
			{30},
			{60},
			{70},
			{60},
			{190},
			{60},
			{40},
			{190},
			{90},
			{60},
			{60}};
			
	public static final int NUMBER_OUTPUT_NODES_FROM_NN = 1;
	public static final int NUMBER_HIDDEN_NODES = 7;
	
	public static final double LEARNING_RATE = 0.7;
	public static final double MAX_ERROR = 0.00001;
	public static final int MAX_ITERATIONS = 10000;
	
	//public static final String NEURAL_NETWORK_NAME = "TutorData" + ".nnet";
	
	public static final String NEURAL_NETWORK_NAME = "C:\\Users\\Melissa\\Documents\\"
			+ "Software_Engineering_Degree\\Thesis\\Code\\"
			+ "TutorBrain\\src\\main\\java\\resources\\" + "TutorData" + ".nnet";
	
	public enum Action {
		RUN,
		DEBUG,
		ERROR,
		SUBMIT,
		HELP
	}
}
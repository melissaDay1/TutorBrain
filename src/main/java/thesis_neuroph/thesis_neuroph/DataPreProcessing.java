package thesis_neuroph.thesis_neuroph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This pre-processes data received from the server/plug-in.
 * Pre-processing includes storing all data as doubles.
 * @author Melissa
 *
 */

public class DataPreProcessing {
	/**
	 * @TODO: Add other variables with difficult types to data input: i.e. Date, etc.
	 */
	//private long id;
	/**
	 * @TODO: Probably won't send these to NN
	 */
	private double[][] studentDataInput;
	
	private static double action;
	private static double linesOfCodeTotal;
	private static double keywordComparatorFound;
	private static double keywordNewFound;
	private static double keywordDoubleFound;
	private static double keyWordFloatFound;
	private static double keywordIfFound;
	private static double keywordForWhileDoFound;
	private static double keywordReturnFound;
	private static double numberOfCommentLines;
	private static double errorType;
	private static double submissionDateTime;
	private static double assignmentCompletedSuccessfully;
	private static double messageGiven;
	private static double messageCode;
	private static double feedbackSurvey;
	private static double cyclomaticComplexity;

	/**
	 * Handles all data pre-processing for input to the NN.
	 * After converting inputs to doubles, this adds these values to the 2D
	 * 		double array variable: studentDataInput.
	 * Eventually, this 2D array will be sent to the NN.
	 * @param dataFromStudents	All input data from the Server
	 */
	public DataPreProcessing(JSONArray dataFromStudents) {
		/**
		 * @TODO: Create with Factory pattern/class???
		 */
		List<double[]> studentDataList = this.processJSONArray(dataFromStudents);
		double[][] inputArrayForNN = this.addInputDataToArray(studentDataList);
		this.studentDataInput = inputArrayForNN;
	}
	
	/**
	 * Pre-processes all values in the JSONArray
	 * @param dataFromStudentsArray All input data from the Server
	 * @return	List<double[]> containing the pre-processed values from Server
	 */
	public List<double[]> processJSONArray(JSONArray dataFromStudentsArray) {
		int jsonCount = 0;
		/**
		 * @TODO: Create with Factory pattern/class
		 */
		List<double[]> inputList = new ArrayList<double[]>();


		if (dataFromStudentsArray != null && dataFromStudentsArray.length() > 0) {

			while (jsonCount < dataFromStudentsArray.length()) {
				JSONObject jObj = dataFromStudentsArray.getJSONObject(jsonCount);
				
				processInputOneStudent(inputList, jObj);

				jsonCount++;
			}
		}
		return inputList;
	}
	
	/**
	 * Used for data preprocessing for message retrieval in Brain.
	 * Pre-processes the input data for one student by converting values to doubles.
	 * @param inputOneStudent	Input from the plug-in for one input row of data for one student
	 * @return	List<double[]> containing the pre-processed values for one row of data for one student
	 */
	public static List<double[]> processJSONObject(JSONObject inputOneStudent) {
		/**
		 * @TODO: Create with Factory pattern/class
		 */
		List<double[]> inputList = new ArrayList<double[]>();
		processInputOneStudent(inputList, inputOneStudent);
		
		return inputList;
	}
	
	/**
	 * Helper method to pre-process the input for one data row from one student
	 * @param inputDataList		The result from processJSONObject
	 * @param inputOneStudent	Input from the plug-in for one input row of data for one student
	 */
	public static void processInputOneStudent(List<double[]> inputDataList, JSONObject inputOneStudent) {
		if (!inputOneStudent.isNull("action")) {
			setAction(inputOneStudent.getString("action"));
		}
		else {
			setAction("Null");
		}
		setLinesOfCodeTotal(inputOneStudent.getInt("linesOfCodeTotal"));
		setKeywordComparatorFound(inputOneStudent.getInt("keywordComparatorFound"));
		setKeywordNewFound(inputOneStudent.getInt("keywordNewFound"));
		setKeywordDoubleFound(inputOneStudent.getInt("keywordDoubleFound"));
		setKeywordFloatFound(inputOneStudent.getInt("keyWordFloatFound"));
		setKeywordIfFound(inputOneStudent.getInt("keywordIfFound"));
		setKeywordForWhileDoFound(inputOneStudent.getInt("keywordForWhileDoFound"));
		setKeywordReturnFound(inputOneStudent.getInt("keywordReturnFound"));
		setNumberOfCommentLines(inputOneStudent.getInt("numberOfCommentLines"));

		if (!inputOneStudent.isNull("errorType")) {
			setErrorType(inputOneStudent.getString("errorType"));
		}
		else {
			setErrorType("Null");
		}
		
		setAssignmentCompletedSuccessfully(inputOneStudent.getInt("assignmentCompletedSuccessfully"));
		
		if (!inputOneStudent.isNull("messageGiven")) {
			setMessageGiven(inputOneStudent.getString("messageGiven"));

		}
		else {
			setMessageGiven("Null");
		}
		setMessageCode(inputOneStudent.getInt("messageCode"));
		setFeedbackSurvey(inputOneStudent.getInt("feedbackSurvey"));
		setCyclomaticComplexity(inputOneStudent.getInt("cyclomaticComplexity"));

		
		inputDataList.add(new double[] {
				getAction(),
				getLinesOfCodeTotal(),
				getKeywordComparatorFound(),
				getKeywordNewFound(),
				getKeywordDoubleFound(),
				getKeyWordFloatFound(),
				getKeywordIfFound(),
				getKeywordForWhileDoFound(),
				getKeywordReturnFound(),
				getNumberOfCommentLines(),
				getErrorType(),
				getAssignmentCompletedSuccessfully(),
				getMessageGiven(),
				getMessageCode(),
				getFeedbackSurvey(),
				getCyclomaticComplexity(),
		});
	}
	
	/**
	 * Creates the 2D double array for input to the neural network for training
	 * @param inputListData 	Result from processJSONArray(): contains the 
	 * 							list of pre-processed values as doubles
	 * @return	2D double array of all students' input data to be used as input to NN
	 */
	public double[][] addInputDataToArray(List<double[]> inputListData) {
		double[][] inputArray;
		/**
		 * @TODO: Consider what to do when size is 0
		 */
		if (inputListData.size() == 0) {
			inputArray = new double[1][1]; 
			System.out.println("No input from Server");
		}
		else {
			double[] element = inputListData.get(0);
			inputArray = new double[inputListData.size()][element.length];
			inputArray = inputListData.toArray(inputArray);
			/*for (double[] d : inputArray) {
				System.out.print(Arrays.toString(d));
			}*/
		}
		return inputArray;
	}
	
	/**
	 * Maps each action input to an appropriate value in the HashMap
	 * @TODO: Not sure if all these conditions are needed for actions
	 * @param actionInput
	 */
	public static void setAction(Constants.Action actionInput) {
		Map<String, Double> actionsCodes = Constants.ACTIONS;
		if (actionInput == Constants.Action.DEBUG) {
			action = lookUpActionCode("Debug", actionsCodes);
		}
		else if (actionInput == Constants.Action.ERROR) {
			action = lookUpActionCode("Error", actionsCodes);
		}
		else if (actionInput == Constants.Action.HELP) {
			action = lookUpActionCode("Help", actionsCodes);
		}
		else if (actionInput == Constants.Action.RUN) {
			action = lookUpActionCode("Run", actionsCodes);
		}
		else if (actionInput == Constants.Action.SUBMIT) {
			action = lookUpActionCode("Submit", actionsCodes);
		}
		else if (actionInput == Constants.Action.TIMER) {
			action = lookUpActionCode("Timer", actionsCodes);
		}
	}
	
	/**
	 * Helper method to find the double value corresponding to the action
	 * @param actionInput
	 * @param actionsCodes
	 * @return
	 */
	public static double lookUpActionCode(String actionInput, Map<String, Double> actionsCodes) {
		if (actionsCodes.containsKey(actionInput)) {
			return actionsCodes.get(actionInput);
		}
		else {
			return 0.0;
		}
	}
	
	public static double getLinesOfCodeTotal() {
		return linesOfCodeTotal;
	}

	public static void setLinesOfCodeTotal(int linesOfCodeTotalInput) {
		linesOfCodeTotal = linesOfCodeTotalInput;
	}

	public static double getKeywordComparatorFound() {
		return keywordComparatorFound;
	}

	public static void setKeywordComparatorFound(int keywordComparatorFoundInput) {
		keywordComparatorFound = keywordComparatorFoundInput;
	}

	public static double getKeywordNewFound() {
		return keywordNewFound;
	}

	public static void setKeywordNewFound(int keywordNewFoundInput) {
		keywordNewFound = keywordNewFoundInput;
	}

	public static double getKeywordDoubleFound() {
		return keywordDoubleFound;
	}

	public static void setKeywordDoubleFound(int keywordDoubleFoundInput) {
		keywordDoubleFound = keywordDoubleFoundInput;
	}

	public static double getKeyWordFloatFound() {
		return keyWordFloatFound;
	}

	public static void setKeyWordFloatFound(int keyWordFloatFoundInput) {
		keyWordFloatFound = keyWordFloatFoundInput;
	}

	public static double getKeywordIfFound() {
		return keywordIfFound;
	}

	public static void setKeywordIfFound(int keywordIfFoundInput) {
		keywordIfFound = keywordIfFoundInput;
	}

	public static double getKeywordForWhileDoFound() {
		return keywordForWhileDoFound;
	}

	public static void setKeywordForWhileDoFound(int keywordForWhileDoFoundInput) {
		keywordForWhileDoFound = keywordForWhileDoFoundInput;
	}

	public static double getKeywordReturnFound() {
		return keywordReturnFound;
	}

	public static void setKeywordReturnFound(int keywordReturnFoundInput) {
		keywordReturnFound = keywordReturnFoundInput;
	}

	public static double getNumberOfCommentLines() {
		return numberOfCommentLines;
	}

	public static void setNumberOfCommentLines(int numberOfCommentLinesInput) {
		numberOfCommentLines = numberOfCommentLinesInput;
	}

	public static double getErrorType() {
		return errorType;
	}

	/**
	 * @TODO: Update to handle all string inputs received from plug-in
	 * Calculates the double value for the error message received from plug-in
	 * @param errorTypeInput	The error message received from plug-in
	 */
	public static void setErrorType(String errorTypeInput) {
		Map<String, Double> errorCodes = Constants.ERROR_MESSAGES;
		double errorValue;
		if (errorCodes.containsKey(errorTypeInput)) {
			errorValue = errorCodes.get(errorTypeInput);
		}
		else {
			errorValue = 0.0;
		}
		errorType = errorValue;
	}

	public static double getSubmissionDateTime() {
		return submissionDateTime;
	}

	public static void setSubmissionDateTime(double submissionDateTimeInput) {
		submissionDateTime = submissionDateTimeInput;
	}

	public static double getAssignmentCompletedSuccessfully() {
		return assignmentCompletedSuccessfully;
	}

	public static void setAssignmentCompletedSuccessfully(int assignmentCompletedSuccessfullyInput) {
		assignmentCompletedSuccessfully = assignmentCompletedSuccessfullyInput;
	}

	public static double getMessageGiven() {
		return messageGiven;
	}

	public static void setMessageGiven(String messageGivenInput) {
		Map<String, Double> messageCodes = Constants.MESSAGES_BY_STRING;
		double messageValue;
		if (messageCodes.containsKey(messageGivenInput)) {
			messageValue = messageCodes.get(messageGivenInput);
		}
		else {
			messageValue = 0.0;
		}
		
		messageGiven = messageValue;
	}

	public static double getMessageCode() {
		return messageCode;
	}

	public static void setMessageCode(int messageCodeInput) {
		messageCode = messageCodeInput;
	}

	public static double getFeedbackSurvey() {
		return feedbackSurvey;
	}

	public static void setFeedbackSurvey(double feedbackSurveyInput) {
		feedbackSurvey = feedbackSurveyInput;
	}

	public static double getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}

	public static void setCyclomaticComplexity(double cyclomaticComplexityInput) {
		cyclomaticComplexity = cyclomaticComplexityInput;
	}

	public static void setKeywordFloatFound(int keyWordFloatFoundInput) {
		keyWordFloatFound = keyWordFloatFoundInput;
	}
	
	public static void setAction(String actionInput) {
		Map<String, Double> actionCodes = Constants.ACTIONS;
		double actionValue;
		if (actionCodes.containsKey(actionInput)) {
			actionValue = actionCodes.get(actionInput);
		}
		else {
			actionValue = 0.0;
		}
		action = actionValue;
	}
	
	public static double getAction() {
		return action;
	}

	public double[][] getStudentDataInput() {
		return studentDataInput;
	}
}
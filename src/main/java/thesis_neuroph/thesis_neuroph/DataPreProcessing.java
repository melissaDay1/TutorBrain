package thesis_neuroph.thesis_neuroph;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataPreProcessing {
	/**
	 * @TODO: Add other variables with difficult types to data input: i.e. Date, etc.
	 */

	//private long id;
	private double[][] studentDataInput;

	private double studentId;
	
	private double action;
	
	private double assignmentName;
	private static double linesOfCodeTotal;

	private static double keywordComparatorFound;
	private static double keywordNewFound;
	private static double keywordDoubleFound;
	private static double keyWordFloatFound;
	private static double keywordIfFound;
	private static double keywordForWhileDoFound;
	private static double keywordReturnFound;

	private static double numberOfCommentLines;
	private static double linesOfCodeChangedSinceLastRun;
	private static double errorType;
	private static double errorTotal;
	private static double numberRunAttempts;
	private static double runAttemptsSinceLastHint;
	private static double submissionDateTime;

	private static double assignmentCompletedSuccessfully;

	private static double errorCountSinceLastHint;
	private static double messageGiven;
	private static double messageCode;

	private static double feedbackSurvey;

	// In Review
	// ---------------------------
	private static double cyclomaticComplexity;
	private static double timerValue;
	private static double timeSinceLastRun;
	private static double timeIdle;
	private static double timeTotal;
	private static double timeWorking;
	private static double timeWithErrors;
	private static double timeUntilErrorFixed;
	private static double timeSinceLastHint;
	private static double timeMostRecentHint;
	private static double timeSecondMostRecentHint;

	// ---------------------------

	public DataPreProcessing(JSONArray dataFromStudents) {
		/**
		 * @TODO: Create with Factory pattern/class???
		 */
		List<double[]> studentDataList = this.processJSONArray(dataFromStudents);
		double[][] inputArrayForNN = this.addInputDataToArray(studentDataList);
		this.studentDataInput = inputArrayForNN;
	}
	
	public List<double[]> processJSONArray(JSONArray dataFromStudentsArray) {
		int jsonCount = 0;
		/**
		 * @TODO: Create with Factory pattern/class
		 */
		List<double[]> inputList = new ArrayList<double[]>();


		if (dataFromStudentsArray != null && dataFromStudentsArray.length() > 0) {

			while (jsonCount < dataFromStudentsArray.length()) {
				JSONObject jObj = dataFromStudentsArray.getJSONObject(jsonCount);
				
				this.processInputOneStudent(inputList, jObj);

				jsonCount++;
			}
		}
		return inputList;
	}
	
	/**
	 * Used for data preprocessing for message retrieval in Brain
	 * @param inputOneStudent
	 * @return
	 */
	public static List<double[]> processJSONObject(JSONObject inputOneStudent) {
		/**
		 * @TODO: Create with Factory pattern/class
		 */
		List<double[]> inputList = new ArrayList<double[]>();
		processInputOneStudent(inputList, inputOneStudent);
		
		return inputList;
	}
	
	public static void processInputOneStudent(List<double[]> inputDataList, JSONObject inputOneStudent) {
		//int studentId = inputOneStudent.getInt("studentId");
		/**
		 * @TODO: How to get this value from JSON???
		 */
		// Action action;	
		// String assignmentName;
		setLinesOfCodeTotal(inputOneStudent.getInt("linesOfCodeTotal"));
		setKeywordComparatorFound(inputOneStudent.getInt("keywordComparatorFound"));
		setKeywordNewFound(inputOneStudent.getInt("keywordNewFound"));
		setKeywordDoubleFound(inputOneStudent.getInt("keywordDoubleFound"));
		setKeywordDoubleFound(inputOneStudent.getInt("keyWordFloatFound"));
		setKeywordIfFound(inputOneStudent.getInt("keywordIfFound"));
		setKeywordForWhileDoFound(inputOneStudent.getInt("keywordForWhileDoFound"));
		setKeywordReturnFound(inputOneStudent.getInt("keywordReturnFound"));
		setNumberOfCommentLines(inputOneStudent.getInt("numberOfCommentLines"));
		setLinesOfCodeChangedSinceLastRun(inputOneStudent.getInt("linesOfCodeChangedSinceLastRun"));

		setErrorType(inputOneStudent.getString("errorType"));
		setErrorTotal(inputOneStudent.getInt("errorTotal"));
		setNumberRunAttempts(inputOneStudent.getInt("numberRunAttempts"));
		setRunAttemptsSinceLastHint(inputOneStudent.getInt("runAttemptsSinceLastHint"));
		/**
		 * @TODO: Figure out how to get the date from JObject
		 */
		// Date submissionDateTime = jObj.getDate("submissionDateTime");

		setAssignmentCompletedSuccessfully(inputOneStudent.getInt("assignmentCompletedSuccessfully"));

		setErrorCountSinceLastHint(inputOneStudent.getInt("errorCountSinceLastHint"));
		/**
		 * @TODO: May need this variable later
		 */
		//String messageGiven = jObj.getString("messageGiven");
		//int messageCode = jObj.getInt("messageCode");
		//int feedbackSurvey = jObj.getInt("feedbackSurvey");

		// In Review
		// ---------------------------
		/*
		 * private int cyclomaticComplexity; private float timerValue; private float
		 * timeSinceLastRun; private float timeIdle; private float timeTotal; private
		 * float timeWorking; private float timeWithErrors; private float
		 * timeUntilErrorFixed; private float timeSinceLastHint; private float
		 * timeMostRecentHint; private float timeSecondMostRecentHint;
		 */

		// ---------------------------
		
		inputDataList.add(new double[] {
				getLinesOfCodeTotal(),
				getKeywordComparatorFound(),
				getKeywordNewFound(),
				getKeywordDoubleFound(),
				getKeyWordFloatFound(),
				getKeywordIfFound(),
				getKeywordForWhileDoFound(),
				getKeywordReturnFound(),
				getNumberOfCommentLines(),
				getLinesOfCodeChangedSinceLastRun(),
				getErrorType(),
				getErrorTotal(),
				getNumberRunAttempts(),
				getRunAttemptsSinceLastHint(),
				//this.submissionDateTime,
				getAssignmentCompletedSuccessfully(),
				getErrorCountSinceLastHint(),
		});
	}
	
	public double[][] addInputDataToArray(List<double[]> inputListData) {
		/**
		 * @TODO: Consider what to do when size is 0
		 */
		if (inputListData.size() == 0) {
			
		}
		double[] element = inputListData.get(0);
		
		/**
		 * @TODO: Create with Factory pattern/class??
		 */
		double[][] inputArray = new double[inputListData.size()][element.length];
		inputArray = inputListData.toArray(inputArray);
		/*for (double[] d : inputArray) {
			System.out.print(Arrays.toString(d));
		}*/
		return inputArray;
	}
	

	public double getStudentId() {
		return studentId;
	}

	public void setStudentId(double studentId) {
		this.studentId = studentId;
	}

	public double getAction() {
		return action;
	}

	public void setAction(double action) {
		this.action = action;
	}

	public double getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(double assignmentName) {
		this.assignmentName = assignmentName;
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

	public static double getLinesOfCodeChangedSinceLastRun() {
		return linesOfCodeChangedSinceLastRun;
	}

	public static void setLinesOfCodeChangedSinceLastRun(int linesOfCodeChangedSinceLastRunInput) {
		linesOfCodeChangedSinceLastRun = linesOfCodeChangedSinceLastRunInput;
	}

	public static double getErrorType() {
		return errorType;
	}

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

	public static double getErrorTotal() {
		return errorTotal;
	}

	public static void setErrorTotal(int errorTotalInput) {
		errorTotal = errorTotalInput;
	}

	public static double getNumberRunAttempts() {
		return numberRunAttempts;
	}

	public static void setNumberRunAttempts(int numberRunAttemptsInput) {
		numberRunAttempts = numberRunAttemptsInput;
	}

	public static double getRunAttemptsSinceLastHint() {
		return runAttemptsSinceLastHint;
	}

	public static void setRunAttemptsSinceLastHint(int runAttemptsSinceLastHintInput) {
		runAttemptsSinceLastHint = runAttemptsSinceLastHintInput;
	}

	public static double getSubmissionDateTime() {
		return submissionDateTime;
	}

	public static void setSubmissionDateTime(double submissionDateTime) {
		submissionDateTime = submissionDateTime;
	}

	public static double getAssignmentCompletedSuccessfully() {
		return assignmentCompletedSuccessfully;
	}

	public static void setAssignmentCompletedSuccessfully(double assignmentCompletedSuccessfully) {
		assignmentCompletedSuccessfully = assignmentCompletedSuccessfully;
	}

	public static double getErrorCountSinceLastHint() {
		return errorCountSinceLastHint;
	}

	public static void setErrorCountSinceLastHint(int errorCountSinceLastHintInput) {
		errorCountSinceLastHint = errorCountSinceLastHintInput;
	}

	public static double getMessageGiven() {
		return messageGiven;
	}

	public static void setMessageGiven(double messageGiven) {
		messageGiven = messageGiven;
	}

	public double getMessageCode() {
		return messageCode;
	}

	public static void setMessageCode(double messageCode) {
		messageCode = messageCode;
	}

	public static double getFeedbackSurvey() {
		return feedbackSurvey;
	}

	public static void setFeedbackSurvey(double feedbackSurvey) {
		feedbackSurvey = feedbackSurvey;
	}

	public static double getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}

	public static void setCyclomaticComplexity(double cyclomaticComplexity) {
		cyclomaticComplexity = cyclomaticComplexity;
	}

	public static double getTimerValue() {
		return timerValue;
	}

	public static void setTimerValue(double timerValue) {
		timerValue = timerValue;
	}

	public static double getTimeSinceLastRun() {
		return timeSinceLastRun;
	}

	public static void setTimeSinceLastRun(double timeSinceLastRun) {
		timeSinceLastRun = timeSinceLastRun;
	}

	public static double getTimeIdle() {
		return timeIdle;
	}

	public static void setTimeIdle(double timeIdle) {
		timeIdle = timeIdle;
	}

	public static double getTimeTotal() {
		return timeTotal;
	}

	public static void setTimeTotal(double timeTotal) {
		timeTotal = timeTotal;
	}

	public static double getTimeWorking() {
		return timeWorking;
	}

	public static void setTimeWorking(double timeWorking) {
		timeWorking = timeWorking;
	}

	public static double getTimeWithErrors() {
		return timeWithErrors;
	}

	public static void setTimeWithErrors(double timeWithErrors) {
		timeWithErrors = timeWithErrors;
	}

	public static double getTimeUntilErrorFixed() {
		return timeUntilErrorFixed;
	}

	public static void setTimeUntilErrorFixed(double timeUntilErrorFixed) {
		timeUntilErrorFixed = timeUntilErrorFixed;
	}

	public static double getTimeSinceLastHint() {
		return timeSinceLastHint;
	}

	public static void setTimeSinceLastHint(double timeSinceLastHint) {
		timeSinceLastHint = timeSinceLastHint;
	}

	public static double getTimeMostRecentHint() {
		return timeMostRecentHint;
	}

	public static void setTimeMostRecentHint(double timeMostRecentHint) {
		timeMostRecentHint = timeMostRecentHint;
	}

	public static double getTimeSecondMostRecentHint() {
		return timeSecondMostRecentHint;
	}

	public static void setTimeSecondMostRecentHint(double timeSecondMostRecentHint) {
		timeSecondMostRecentHint = timeSecondMostRecentHint;
	}

	public double[][] getStudentDataInput() {
		return studentDataInput;
	}
}
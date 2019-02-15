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
	private double linesOfCodeTotal;

	private double keywordComparatorFound;
	private double keywordNewFound;
	private double keywordDoubleFound;
	private double keyWordFloatFound;
	private double keywordIfFound;
	private double keywordForWhileDoFound;
	private double keywordReturnFound;

	private double numberOfCommentLines;
	private double linesOfCodeChangedSinceLastRun;
	private double errorType;
	private double errorTotal;
	private double numberRunAttempts;
	private double runAttemptsSinceLastHint;
	private double submissionDateTime;

	private double assignmentCompletedSuccessfully;

	private double errorCountSinceLastHint;
	private double messageGiven;
	private double messageCode;

	private double feedbackSurvey;

	// In Review
	// ---------------------------
	private double cyclomaticComplexity;
	private double timerValue;
	private double timeSinceLastRun;
	private double timeIdle;
	private double timeTotal;
	private double timeWorking;
	private double timeWithErrors;
	private double timeUntilErrorFixed;
	private double timeSinceLastHint;
	private double timeMostRecentHint;
	private double timeSecondMostRecentHint;

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
	public List<double[]> processJSONObject(JSONObject inputOneStudent) {
		/**
		 * @TODO: Create with Factory pattern/class
		 */
		List<double[]> inputList = new ArrayList<double[]>();
		this.processInputOneStudent(inputList, inputOneStudent);
		
		return inputList;
	}
	
	public void processInputOneStudent(List<double[]> inputDataList, JSONObject inputOneStudent) {
		int studentId = inputOneStudent.getInt("studentId");
		/**
		 * @TODO: How to get this value from JSON???
		 */
		// Action action;	
		// String assignmentName;
		this.setLinesOfCodeTotal(inputOneStudent.getInt("linesOfCodeTotal"));
		this.setKeywordComparatorFound(inputOneStudent.getInt("keywordComparatorFound"));
		this.setKeywordNewFound(inputOneStudent.getInt("keywordNewFound"));
		this.setKeywordDoubleFound(inputOneStudent.getInt("keywordDoubleFound"));
		this.setKeywordDoubleFound(inputOneStudent.getInt("keyWordFloatFound"));
		this.setKeywordIfFound(inputOneStudent.getInt("keywordIfFound"));
		this.setKeywordForWhileDoFound(inputOneStudent.getInt("keywordForWhileDoFound"));
		this.setKeywordReturnFound(inputOneStudent.getInt("keywordReturnFound"));
		this.setNumberOfCommentLines(inputOneStudent.getInt("numberOfCommentLines"));
		this.setLinesOfCodeChangedSinceLastRun(inputOneStudent.getInt("linesOfCodeChangedSinceLastRun"));

		this.setErrorType(inputOneStudent.getString("errorType"));
		this.setErrorTotal(inputOneStudent.getInt("errorTotal"));
		this.setNumberRunAttempts(inputOneStudent.getInt("numberRunAttempts"));
		this.setRunAttemptsSinceLastHint(inputOneStudent.getInt("runAttemptsSinceLastHint"));
		/**
		 * @TODO: Figure out how to get the date from JObject
		 */
		// Date submissionDateTime = jObj.getDate("submissionDateTime");

		this.setAssignmentCompletedSuccessfully(inputOneStudent.getInt("assignmentCompletedSuccessfully"));

		this.setErrorCountSinceLastHint(inputOneStudent.getInt("errorCountSinceLastHint"));
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
				this.linesOfCodeTotal,
				this.keywordComparatorFound,
				this.keywordNewFound,
				this.keywordDoubleFound,
				this.keyWordFloatFound,
				this.keywordIfFound,
				this.keywordForWhileDoFound,
				this.keywordReturnFound,
				this.numberOfCommentLines,
				this.linesOfCodeChangedSinceLastRun,
				this.errorType,
				this.errorTotal,
				this.numberRunAttempts,
				this.runAttemptsSinceLastHint,
				//this.submissionDateTime,
				this.assignmentCompletedSuccessfully,
				this.errorCountSinceLastHint,
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

	public double getLinesOfCodeTotal() {
		return linesOfCodeTotal;
	}

	public void setLinesOfCodeTotal(int linesOfCodeTotalInput) {
		this.linesOfCodeTotal = linesOfCodeTotalInput;
	}

	public double getKeywordComparatorFound() {
		return keywordComparatorFound;
	}

	public void setKeywordComparatorFound(int keywordComparatorFoundInput) {
		this.keywordComparatorFound = keywordComparatorFoundInput;
	}

	public double getKeywordNewFound() {
		return keywordNewFound;
	}

	public void setKeywordNewFound(int keywordNewFoundInput) {
		this.keywordNewFound = keywordNewFoundInput;
	}

	public double getKeywordDoubleFound() {
		return keywordDoubleFound;
	}

	public void setKeywordDoubleFound(int keywordDoubleFoundInput) {
		this.keywordDoubleFound = keywordDoubleFoundInput;
	}

	public double getKeyWordFloatFound() {
		return keyWordFloatFound;
	}

	public void setKeyWordFloatFound(int keyWordFloatFoundInput) {
		this.keyWordFloatFound = keyWordFloatFoundInput;
	}

	public double getKeywordIfFound() {
		return keywordIfFound;
	}

	public void setKeywordIfFound(int keywordIfFoundInput) {
		this.keywordIfFound = keywordIfFoundInput;
	}

	public double getKeywordForWhileDoFound() {
		return keywordForWhileDoFound;
	}

	public void setKeywordForWhileDoFound(int keywordForWhileDoFoundInput) {
		this.keywordForWhileDoFound = keywordForWhileDoFoundInput;
	}

	public double getKeywordReturnFound() {
		return keywordReturnFound;
	}

	public void setKeywordReturnFound(int keywordReturnFoundInput) {
		this.keywordReturnFound = keywordReturnFoundInput;
	}

	public double getNumberOfCommentLines() {
		return numberOfCommentLines;
	}

	public void setNumberOfCommentLines(int numberOfCommentLinesInput) {
		this.numberOfCommentLines = numberOfCommentLinesInput;
	}

	public double getLinesOfCodeChangedSinceLastRun() {
		return linesOfCodeChangedSinceLastRun;
	}

	public void setLinesOfCodeChangedSinceLastRun(int linesOfCodeChangedSinceLastRunInput) {
		this.linesOfCodeChangedSinceLastRun = linesOfCodeChangedSinceLastRunInput;
	}

	public double getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorTypeInput) {
		Map<String, Double> errorCodes = Constants.ERROR_MESSAGES;
		double errorValue;
		if (errorCodes.containsKey(errorTypeInput)) {
			errorValue = errorCodes.get(errorTypeInput);
		}
		else {
			errorValue = 0.0;
		}
		this.errorType = errorValue;
	}

	public double getErrorTotal() {
		return errorTotal;
	}

	public void setErrorTotal(int errorTotalInput) {
		this.errorTotal = errorTotalInput;
	}

	public double getNumberRunAttempts() {
		return numberRunAttempts;
	}

	public void setNumberRunAttempts(int numberRunAttemptsInput) {
		this.numberRunAttempts = numberRunAttemptsInput;
	}

	public double getRunAttemptsSinceLastHint() {
		return runAttemptsSinceLastHint;
	}

	public void setRunAttemptsSinceLastHint(int runAttemptsSinceLastHintInput) {
		this.runAttemptsSinceLastHint = runAttemptsSinceLastHintInput;
	}

	public double getSubmissionDateTime() {
		return submissionDateTime;
	}

	public void setSubmissionDateTime(double submissionDateTime) {
		this.submissionDateTime = submissionDateTime;
	}

	public double getAssignmentCompletedSuccessfully() {
		return assignmentCompletedSuccessfully;
	}

	public void setAssignmentCompletedSuccessfully(double assignmentCompletedSuccessfully) {
		this.assignmentCompletedSuccessfully = assignmentCompletedSuccessfully;
	}

	public double getErrorCountSinceLastHint() {
		return errorCountSinceLastHint;
	}

	public void setErrorCountSinceLastHint(int errorCountSinceLastHintInput) {
		this.errorCountSinceLastHint = errorCountSinceLastHintInput;
	}

	public double getMessageGiven() {
		return messageGiven;
	}

	public void setMessageGiven(double messageGiven) {
		this.messageGiven = messageGiven;
	}

	public double getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(double messageCode) {
		this.messageCode = messageCode;
	}

	public double getFeedbackSurvey() {
		return feedbackSurvey;
	}

	public void setFeedbackSurvey(double feedbackSurvey) {
		this.feedbackSurvey = feedbackSurvey;
	}

	public double getCyclomaticComplexity() {
		return cyclomaticComplexity;
	}

	public void setCyclomaticComplexity(double cyclomaticComplexity) {
		this.cyclomaticComplexity = cyclomaticComplexity;
	}

	public double getTimerValue() {
		return timerValue;
	}

	public void setTimerValue(double timerValue) {
		this.timerValue = timerValue;
	}

	public double getTimeSinceLastRun() {
		return timeSinceLastRun;
	}

	public void setTimeSinceLastRun(double timeSinceLastRun) {
		this.timeSinceLastRun = timeSinceLastRun;
	}

	public double getTimeIdle() {
		return timeIdle;
	}

	public void setTimeIdle(double timeIdle) {
		this.timeIdle = timeIdle;
	}

	public double getTimeTotal() {
		return timeTotal;
	}

	public void setTimeTotal(double timeTotal) {
		this.timeTotal = timeTotal;
	}

	public double getTimeWorking() {
		return timeWorking;
	}

	public void setTimeWorking(double timeWorking) {
		this.timeWorking = timeWorking;
	}

	public double getTimeWithErrors() {
		return timeWithErrors;
	}

	public void setTimeWithErrors(double timeWithErrors) {
		this.timeWithErrors = timeWithErrors;
	}

	public double getTimeUntilErrorFixed() {
		return timeUntilErrorFixed;
	}

	public void setTimeUntilErrorFixed(double timeUntilErrorFixed) {
		this.timeUntilErrorFixed = timeUntilErrorFixed;
	}

	public double getTimeSinceLastHint() {
		return timeSinceLastHint;
	}

	public void setTimeSinceLastHint(double timeSinceLastHint) {
		this.timeSinceLastHint = timeSinceLastHint;
	}

	public double getTimeMostRecentHint() {
		return timeMostRecentHint;
	}

	public void setTimeMostRecentHint(double timeMostRecentHint) {
		this.timeMostRecentHint = timeMostRecentHint;
	}

	public double getTimeSecondMostRecentHint() {
		return timeSecondMostRecentHint;
	}

	public void setTimeSecondMostRecentHint(double timeSecondMostRecentHint) {
		this.timeSecondMostRecentHint = timeSecondMostRecentHint;
	}

	public double[][] getStudentDataInput() {
		return studentDataInput;
	}

	
	
}
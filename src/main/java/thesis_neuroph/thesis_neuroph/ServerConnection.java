package thesis_neuroph.thesis_neuroph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.eclipse.core.resources.ResourcesPlugin;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

//import com.example.tutorhelpplugin.launching.TutorPluginLogTracker;
//import com.example.tutorhelpplugin.splashHandlers.InteractiveSplashHandler;

public class ServerConnection {

	/**
	 * Get Assignments using API
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONArray getStudentData() throws IOException, ParseException {

		/*
		 * String directoryPath =
		 * ResourcesPlugin.getWorkspace().getRoot().getLocation().toString() +
		 * File.separator + "AssignmentList_Cosmo_Client";
		 * 
		 * String userCourseName = InteractiveSplashHandler.login_courseName;
		 */

		// For local test
		// userCourseName = "CSE360";

		// Local machine URL
		// URL url = new URL("http://localhost:8080/assignments");

		// Manohar AWS URL
		// URL url = new URL("http://34.224.41.66:8080/assignments");

		// Local machine URL
		// URL url = new URL("http://localhost:8080/assignments");
		URL url = new URL("http://localhost:8080/server/inputs");
		
		

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

		String output;
		JSONArray jObjList = new JSONArray();

		while ((output = br.readLine()) != null) {
			jObjList = (JSONArray) new JSONTokener(output).nextValue();
		}

		System.out.println("Success");
		conn.disconnect();
		return jObjList;
	}
	
	/**
	 * Get data from one student
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONObject getDataOneStudent(JSONArray inputJSONArray, int index) throws IOException, ParseException {
		JSONObject studentObject;
		URL url = new URL("http://localhost:8080/server/inputs");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}

		if (!inputJSONArray.isNull(index)) {
			studentObject = inputJSONArray.getJSONObject(index);
		}
		else {
			studentObject = null;
		}
		
		conn.disconnect();
		return studentObject;
	}
}
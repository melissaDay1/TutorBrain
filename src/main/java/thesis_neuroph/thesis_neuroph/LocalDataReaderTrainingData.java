package thesis_neuroph.thesis_neuroph;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class LocalDataReaderTrainingData {
	
	/**
	 * Retrieves a JSONArray that's stored locally in a file
	 * Note: Bug exists that JSONArray must start with 2 opening brackets: [[
	 * @return
	 * @throws JSONException
	 * @throws IOException
	 * @throws org.json.simple.parser.ParseException 
	 */
	public static JSONArray getLocalJSONArray(String path) throws JSONException, IOException, org.json.simple.parser.ParseException {
		InputStream inputStream = new FileInputStream(path);
		
		BufferedReader br = new BufferedReader(new InputStreamReader((inputStream)));

		String output;
		JSONArray jObjList = null;
		
		StringBuilder builder = new StringBuilder();
		for (String line = null; (line = br.readLine()) != null;) {
            builder.append(line).append("\n");
        } 
		
		JSONTokener tokener = new JSONTokener(builder.toString());
        try {
        	JSONObject obj = new JSONObject(tokener);                
        } catch (JSONException e) {             

    		JSONTokener tokener2 = new JSONTokener(builder.toString());
            try {
            	jObjList = new JSONArray(tokener);
            } catch (JSONException e1) {                    
                e1.printStackTrace();
            }
        }
		return jObjList;
	}
	
	
	/**
	 * Get data from one student
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public static JSONObject getDataOneStudent(JSONArray inputJSONArray, int index) throws IOException, ParseException {
		JSONObject studentObject;

		if (!inputJSONArray.isNull(index)) {
			studentObject = inputJSONArray.getJSONObject(index);
		}
		else {
			studentObject = null;
		}
		return studentObject;
	}
	
	/**
	 * This method not working -- want to use it to read in outputs from file
	 * @param fileName
	 * @return
	 */
	public double[][] readOutputDataFromCSV(String fileName) {
		String thisLine; 
		int count=0; 
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileName);
			
			DataInputStream myInput = new DataInputStream(fis);

			List<Double[]> lines = new ArrayList<Double[]>();
			try {
				while ((thisLine = myInput.readLine()) != null) {
				    String[] inputLineAsString = thisLine.split(",");
				    String stringIn = inputLineAsString[0];
				    System.out.println(stringIn);
				    //double inputNum = Double.parseDouble(stringIn);
				   // Double[] arr = {inputNum};
					//lines.add(arr);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// convert our list to a String array.
			String[][] array = new String[lines.size()][0];
			lines.toArray(array);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
	    }
}
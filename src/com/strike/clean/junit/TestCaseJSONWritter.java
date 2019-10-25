package com.strike.clean.junit;

import com.strike.clean.rule.Rule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCaseJSONWritter
{
	private static final String TESTCASE_OUTPUT_PATH = "/Volumes/Official/persoo/TestCase.json";
	private static final Logger LOGGER = Logger.getLogger(TestCaseJSONWritter.class.getName());
			
	public static void main(String[] arg)
	{
		try
		{
			boolean iterateOver = Boolean.TRUE;
			JSONArray arrayJSON = getTestCaseJSON();
			
			try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
			{
				do 
				{
					System.out.println(UnitMessageContants.MESSAGE);
					int value = Integer.parseInt(reader.readLine());
					
					switch(value)
					{
						case 0:
							iterateOver = Boolean.FALSE;
							break;
						case 1:
							addTestCase(arrayJSON, reader);
							break;
						case 2:
							deleteTestCase(arrayJSON, reader);
							break;
						case 3:
							System.out.println(arrayJSON);
							break;
						default:
							System.out.println(UnitMessageContants.ENTER_VALID_NUMBER);
					}
				}while(iterateOver);
			}

			writeToFile(arrayJSON.toString());
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	private static void addTestCase(JSONArray array, BufferedReader reader) throws Exception
	{
		JSONObject testCaseJSON = getTestCaseTemplate();
		JSONArray outcomeArray = testCaseJSON.getJSONArray(UnitMessageContants.OUTCOMES_ARRAY);
		
		System.out.println(UnitMessageContants.OUTCOME_MESSAGE);
		
		for(int i = 0; i < outcomeArray.length(); i++)
		{
			String[] outcomes = reader.readLine().split(",");

			int count = 0;
			int type = Integer.parseInt(outcomes[0]);
			
			if(type == -1)
			{
				break;
			}
			else 
			{
				if(!Rule.Outcome.getNoCountIntList().contains(type))
				{
					count = Integer.parseInt(outcomes[1]);
				}

				JSONArray outcomeSet = new JSONArray();

				outcomeSet.put(type);
				outcomeSet.put(count);

				outcomeArray.put(outcomeSet);	
			}
		}

		System.out.println(UnitMessageContants.ENTER_TEST_CASE_NAME);
		testCaseJSON.put(UnitMessageContants.TEST_CASE_NAME, reader.readLine());
		System.out.println(UnitMessageContants.ENTER_TEST_CASE_RESULT);
		testCaseJSON.put(UnitMessageContants.TEST_CASE_RESULT, reader.readLine());
		System.out.println(UnitMessageContants.ADDED);

		array.put(testCaseJSON);
	}

	private static void deleteTestCase(JSONArray array, BufferedReader reader) throws Exception
	{
		System.out.println(UnitMessageContants.ENTER_INDEX_TO_DELETE);
		int result = Integer.parseInt(reader.readLine());
		
		if(result > 0)
		{
			int deleteIndex = 0;

			if(array.length() > deleteIndex - 1)
			{
				List<Object> cases = new ArrayList<>();

				for(int i = 0; i < array.length(); i++)
				{
					if(deleteIndex -1 != i)
					{
						cases.add(array.get(i));
					}
				}

				array = new JSONArray(cases);
			}
			System.out.println(UnitMessageContants.DELETED);
		}
	}
	
	private static JSONObject getTestCaseTemplate()  throws Exception
	{
		JSONObject template = new JSONObject();
		
		template.put(UnitMessageContants.TEST_CASE_NAME, UnitMessageContants.EMPTY);
		template.put(UnitMessageContants.TEST_CASE_RESULT, UnitMessageContants.EMPTY);
		template.put(UnitMessageContants.OUTCOMES_ARRAY, new JSONArray());
		
		return template;
	}
	
	public static JSONArray getTestCaseJSON() throws Exception
	{
		String data = readTestCaseFile();
		JSONArray dataJSON = new JSONArray();
		
		if(data != null && !data.equalsIgnoreCase(UnitMessageContants.EMPTY))
		{
			dataJSON = new JSONArray(data);
		}
		
		return dataJSON;
	}
			
	private static String readTestCaseFile()
	{
		StringBuilder builder = new StringBuilder();
		
		try(Reader reader = new FileReader(getOutputFile()))
		{
			int unicode = 0;
			while((unicode = reader.read()) != -1)
			{
				builder.append((char)unicode);
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		
		return builder.toString();
	}
	
	private static void writeToFile(String data)
	{
		try(Writer writer = new FileWriter(getOutputFile()))
		{
			writer.write(data);
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	
	private static File getOutputFile() throws Exception
	{
		File file = new File(TESTCASE_OUTPUT_PATH);
		
		if(!file.exists())
		{
			file.createNewFile();
		}
		
		return file;
	}
}

package com.strike.clean.junit;

import com.strike.clean.actions.Controller;
import com.strike.clean.actions.GameController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StrikerTester
{
	private static final Logger LOGGER = Logger.getLogger(StrikerTester.class.getName());
	@Test
	void doTest()
	{
		try
		{
			JSONArray testcases = TestCaseJSONWritter.getTestCaseJSON();

			for(int i = 0; i < testcases.length(); i++)
			{
				String testCaseName = null;
				try
				{
					JSONObject testcase = testcases.getJSONObject(i);
					testCaseName = testcase.getString(UnitMessageContants.TEST_CASE_NAME);;
					
					Controller controller = new GameController(Controller.InputMode.JSON);
					
					controller.setOutcomes(testcase.getJSONArray(UnitMessageContants.OUTCOMES_ARRAY));
					controller.invokeGame();
					String result = controller.getGameResult();
					
					assertEquals(result,testcase.getString(UnitMessageContants.TEST_CASE_RESULT));
				}
				catch(Exception e)
				{
					LOGGER.log(Level.SEVERE, "TestCase Name : "+testCaseName + "\n" +e.getMessage(),e);
				}
			}
		}
		catch(Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
}

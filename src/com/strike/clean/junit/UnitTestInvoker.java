package com.strike.clean.junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class UnitTestInvoker 
{
	public static void main(String[] arg)
	{
		Result result = JUnitCore.runClasses(StrikerTester.class);
		
		for(Failure failure : result.getFailures())
		{
			System.out.println(failure);
		}

		System.out.println(result.wasSuccessful());
	}
}

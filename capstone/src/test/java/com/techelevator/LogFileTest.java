package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class LogFileTest {
	
	private LogFile myLog;
	
	@Before
	public void setup() {
		myLog = new LogFile();
	}
	
	@Test
	public void file_writes_properly() {
		try {
			myLog.log("Test String") ;
			Assert.assertTrue(true);
		} catch(Exception e) {
			Assert.assertTrue(false);
		}
		
	}

}

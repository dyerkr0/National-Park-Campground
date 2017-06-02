package com.techelevator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SalesReportTest {
	private SalesReport mySalesReport;
	private List<String> myList;
	
	@Before
	public void setup() {
		mySalesReport = new SalesReport();
		myList = new ArrayList<String>();
		myList.add("one");
		myList.add("two");
		myList.add("three");
	}
	
	@Test
	public void report_writes_properly() {
		try {
			mySalesReport.report(myList);
			Assert.assertTrue(true);
		} catch(Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void total_amount_writes_properly() {
		try {
			mySalesReport.totalAmount("Test String");
			Assert.assertTrue(true);
		} catch(Exception e) {
			Assert.assertTrue(false);
		}
	}

}

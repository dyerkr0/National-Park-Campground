package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

;

public class VendingMachineTest {
	
	private VendingMachine myVendingMachine;
	
	@Before
	public void setup() {
		myVendingMachine = new VendingMachine();
	}
	
	@Test
	public void get_contents_works_as_intended() {
		Assert.assertEquals("Potato Crisps", myVendingMachine.getContents().get("A1").getName());
	}	

}

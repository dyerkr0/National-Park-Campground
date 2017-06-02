package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class VendingMachineItemTest {
	
	private VendingMachineItem myVendingMachineItem;
	
	@Before
	public void setup() {
		
		myVendingMachineItem = new VendingMachineItem("A1", "Potato Crisps", new DollarAmount(300));
	}
	
	@Test
	public void vending_machine_item_instantiates_properly() {
		Assert.assertEquals("A1", myVendingMachineItem.getSlotId());
		Assert.assertEquals("Potato Crisps", myVendingMachineItem.getName());
		Assert.assertEquals(new DollarAmount(300), myVendingMachineItem.getPrice());
		Assert.assertEquals(5, myVendingMachineItem.getQuantity());
	}

}

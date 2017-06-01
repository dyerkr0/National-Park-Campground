package com.techelevator;

public class VendingMachineItem {
	private String slotId;
	private String name;
	private DollarAmount price;
	private int quantity;
	
	public VendingMachineItem(String slotId, String name, DollarAmount price) {
		this.slotId = slotId;
		this.name = name;
		this.price = price;
		quantity = 5;
	}
	
	public String getSlotId() {
		return slotId;
	}
	public String getName() {
		return name;
	}
	public DollarAmount getPrice() {
		return price;
	}
	public void setSlotId(String slotId) {
		this.slotId = slotId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(DollarAmount price) {
		this.price = price;
	}
	

}

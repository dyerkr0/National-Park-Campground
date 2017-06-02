package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	private SalesReport mySales = new SalesReport();
	
	private Map<String, VendingMachineItem> contents = new LinkedHashMap<String, VendingMachineItem>();
	
	public VendingMachine() {
		File inventory = new File("vendingmachine.csv");
		try(Scanner goodsScanner = new Scanner(inventory)) {
			while(goodsScanner.hasNextLine()) {
				String line = goodsScanner.nextLine();
				String[] storage = line.split("\\|"); //storing each item in between the | character into an array
				String slotId = storage[0];
				String name = storage[1];
				DollarAmount price = DollarAmount.parseDollarAmount(storage[2]);
				contents.put(slotId, new VendingMachineItem(slotId, name, price));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, VendingMachineItem> getContents() {
		return contents;
	}
	
	public void soldItems() {
		Map<String, VendingMachineItem> soldItemList = getContents();
		List<String> reportList = new ArrayList<String>();
		DollarAmount totalSales = new DollarAmount(0);
		for (String itemTrait : soldItemList.keySet()) { 
			reportList.add(soldItemList.get(itemTrait).getName() + "|" + (5 - soldItemList.get(itemTrait).getQuantity()));
			}
		mySales.report(reportList);
		
		for (String itemsSold : soldItemList.keySet()) {
			int quantitySold = 5 - soldItemList.get(itemsSold).getQuantity();
			if(quantitySold > 0) {
				totalSales = totalSales.plus(soldItemList.get(itemsSold).getPrice().times(quantitySold));
			}
		}
		
		mySales.totalAmount(totalSales.toString());
		
	}
	
}

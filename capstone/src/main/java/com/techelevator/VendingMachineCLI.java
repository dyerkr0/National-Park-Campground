package com.techelevator;

import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE };
	
	private Menu menu;
	private VendingMachine myVendingMachine;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	public void run(VendingMachine myVendingMachine) {
		this.myVendingMachine = myVendingMachine;
		while(true) {
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				Map<String, VendingMachineItem> myItemList = myVendingMachine.getContents();
				for(String itemTrait : myItemList.keySet()) { //Format this at some point before submission
					System.out.println(myItemList.get(itemTrait).getSlotId());
					System.out.println(myItemList.get(itemTrait).getName());
					System.out.println(myItemList.get(itemTrait).getPrice());
					System.out.println(myItemList.get(itemTrait).getQuantity());
				}
			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
		}
	}
}

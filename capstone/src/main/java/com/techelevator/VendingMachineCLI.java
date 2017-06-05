package com.techelevator;

import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT,
			SUB_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;
	private VendingMachine myVendingMachine;
	private DollarAmount currentMoney = new DollarAmount(0);
	private DollarAmount moneyIn = new DollarAmount(0);
	private List<String> consume = new ArrayList<String>();
	private LogFile myLogFile = new LogFile();
	private GiveChange giveChange = new GiveChange();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run(VendingMachine myVendingMachine) {
		this.myVendingMachine = myVendingMachine;

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String choice2 = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
					if (choice2.equals(SUB_MENU_OPTION_FEED_MONEY)) {
						feedMoney();
					} else if (choice2.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
						selectProduct();
					} else if (choice2.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
						giveChange.dispenseChange(currentMoney);
						myLogFile.log(" GIVE CHANGE " + currentMoney + " $0.00");
						currentMoney = new DollarAmount(0);
						enjoyPurchase();
						myVendingMachine.soldItems();
						break;
					}
				}
			}
		}
	}

	private void displayItems() {
		myVendingMachine.showInventory();
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Current money: " + currentMoney);
	}
	
	private void feedMoney() {
		moneyIn = currentMoney;
		DollarAmount feedMoney = new DollarAmount(0);
		System.out.println("Please insert money – this machine accepts the following denominations:");
		System.out.println("**** $1, $2, $5, $10 ****");
		Scanner userAdd = new Scanner(System.in);
		int userInput = userAdd.nextInt();
		if(userInput == 1 || userInput == 2 || userInput == 5 || userInput == 10) {
			feedMoney = new DollarAmount(userInput * 100);
			currentMoney = currentMoney.plus(feedMoney);
			System.out.println("Current money: " + currentMoney);
			myLogFile.log(" FEED MONEY " + moneyIn + " " + currentMoney);
		} else {
			System.out.println("Invalid currency amount. Please try again.");
		}
	}

	private void selectProduct() {
		String keyCheck = "[A-D][1-4]";
		System.out.println("Please enter the Slot ID of your desired item.");
		Scanner userChoice = new Scanner(System.in);
		String userBuy = userChoice.nextLine();
		if (!Pattern.matches(keyCheck, userBuy)) {
			System.out.println("Invalid selection. Please try again.");
		} else {
			if (myVendingMachine.getContents().get(userBuy).getQuantity() == 0) {
				System.out.println("That item is SOLD OUT. Please make another selection.");
				System.out.println("Current money: " + currentMoney);
			}
			DollarAmount thisPurchase = myVendingMachine.getContents().get(userBuy).getPrice();
			if (thisPurchase.isGreaterThan(currentMoney)) {
				System.out.println("You must insert more money to make this purchase.");
			} else if(!thisPurchase.isGreaterThan(currentMoney) && myVendingMachine.getContents().get(userBuy).getQuantity() != 0) {
				moneyIn = currentMoney;
				currentMoney = currentMoney.minus(thisPurchase);
				consume.add(userBuy);
				int buyout = myVendingMachine.getContents().get(userBuy).getQuantity();
				myVendingMachine.getContents().get(userBuy).setQuantity(buyout - 1);
				System.out.println("Current money: " + currentMoney);
				myLogFile.log(" " + myVendingMachine.getContents().get(userBuy).getName() + " " + myVendingMachine.getContents().get(userBuy).getSlotId() + " " + moneyIn + " " + currentMoney);
			}
		}
	}

	private void enjoyPurchase() {
		for (String key : consume) {
			if (key.contains("A")) {
				System.out.println("Crunch Crunch, Yum!");
			}
			if (key.contains("B")) {
				System.out.println("Munch Munch, Yum!");
			}
			if (key.contains("C")) {
				System.out.println("Glug Glug, Yum!");
			}
			if (key.contains("D")) {
				System.out.println("Chew Chew, Yum!");
			}
		}
		consume.removeAll(consume);
	}
}

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
	private String keyCheck = "[A-D][1-4]";
	private List<String> consume = new ArrayList<String>();
	private int quarterCount = 0;
	private int dimeCount = 0;
	private int nickelCount = 0;
	private LogFile myLogFile = new LogFile();

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
						makeChange();
						enjoyPurchase();
						break;
					}
				}
			}
		}
	}

	private void displayItems() {
		Map<String, VendingMachineItem> myItemList = myVendingMachine.getContents();
		for (String itemTrait : myItemList.keySet()) { 
			System.out.print(myItemList.get(itemTrait).getSlotId() + " ");
			System.out.print(myItemList.get(itemTrait).getName() + " ");
			System.out.print(myItemList.get(itemTrait).getPrice() + " ");
			if (myItemList.get(itemTrait).getQuantity() == 0) {
				System.out.print("SOLD OUT");
			} else {
				System.out.print(myItemList.get(itemTrait).getQuantity());
			}
			System.out.println(" ");
			System.out.println(" ");
		}
		System.out.println("Current money: " + currentMoney);
	}

	private void feedMoney() {
		moneyIn = currentMoney;
		System.out.println("Please insert money – this machine accepts the following demoninations:");
		System.out.println("**** $1, $2, $5, $10 ****");
		Scanner userAdd = new Scanner(System.in);
		DollarAmount feedMoney = new DollarAmount(userAdd.nextInt() * 100);
		currentMoney = currentMoney.plus(feedMoney);
		System.out.println("Current money: " + currentMoney);
		myLogFile.log("FEED MONEY" + moneyIn + " " + currentMoney);
	}

	private void selectProduct() {
		System.out.println("Please enter the Slot ID of your desired item.");
		Scanner userChoice = new Scanner(System.in);
		String userBuy = userChoice.nextLine();
		// Is it a valid key?
		if (!Pattern.matches(keyCheck, userBuy)) {
			System.out.println("Invalid selection. Please try again.");
		} else {
			// Are there still things to be sold?
			if (myVendingMachine.getContents().get(userBuy).getQuantity() == 0) {
				System.out.println("That item is SOLD OUT. Please make another selection.");
			}
			// Is there enough money to buy it?
			DollarAmount thisPurchase = myVendingMachine.getContents().get(userBuy).getPrice();
			if (thisPurchase.isGreaterThan(currentMoney)) {
				System.out.println("You must insert more money to make this purchase.");
			} else {
				moneyIn = currentMoney;
				currentMoney = currentMoney.minus(thisPurchase);
				consume.add(userBuy);
				int buyout = myVendingMachine.getContents().get(userBuy).getQuantity();
				myVendingMachine.getContents().get(userBuy).setQuantity(buyout - 1);
				System.out.println("Current money: " + currentMoney);
				myLogFile.log(myVendingMachine.getContents().get(userBuy).getName() + myVendingMachine.getContents().get(userBuy).getSlotId() + moneyIn + " " + currentMoney);
			}
		}
	}

	private void makeChange() {
		System.out.println("You did not spend " + currentMoney.toString() + ".");
		int makeChange = currentMoney.hashCode();
		while (makeChange > 0) {
			while (makeChange - 25 >= 0) {
				quarterCount++;
				makeChange = makeChange - 25;
			}
			while (makeChange - 10 >= 0) {
				dimeCount++;
				makeChange = makeChange - 10;
			}
			while (makeChange - 5 >= 0) {
				nickelCount++;
				makeChange = makeChange - 5;
			}
		}
		moneyIn = currentMoney;
		currentMoney = currentMoney.minus(currentMoney);
		System.out.println("You got " + quarterCount + " quarters, " + dimeCount + " dimes, and " + nickelCount
				+ " nickels back in change.");
		myLogFile.log("GIVE CHANGE" + moneyIn + " " + currentMoney);
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
	}
}

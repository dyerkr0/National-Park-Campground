package com.techelevator;

public class GiveChange {
	
	public DollarAmount dispenseChange(DollarAmount currentMoney) {
		
		
		System.out.println("You did not spend " + currentMoney.toString() + ".");
		int quarterCount = 0;
		int dimeCount = 0;
		int nickelCount = 0;
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
		System.out.println("You got " + quarterCount + " quarters, " + dimeCount + " dimes, and " + nickelCount
				+ " nickels back in change.");
		
		return currentMoney;
	}

}

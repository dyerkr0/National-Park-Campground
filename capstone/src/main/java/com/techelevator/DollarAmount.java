package com.techelevator;

public class DollarAmount {
public static final DollarAmount ZERO_DOLLARS = new DollarAmount(0);
    
    private int totalAmountInCents;
    
    public DollarAmount(int totalAmountInCents) {
        this.totalAmountInCents = totalAmountInCents;
    }
    public DollarAmount(int dollars, int cents) {
    	this.totalAmountInCents = dollars * 100 + cents;
    }
    
    public int getCents() {
        return (int)(totalAmountInCents % 100);
    }
    
    public int getDollars() {
        return totalAmountInCents / 100;
    }
    
    public boolean isGreaterThan(DollarAmount amountToCompare) {
        return this.totalAmountInCents > amountToCompare.totalAmountInCents;
    }
    
    public boolean isGreaterThanOrEqualTo(DollarAmount amountToCompare) {
        return this.totalAmountInCents >= amountToCompare.totalAmountInCents;
    }
    
    public boolean isLessThan(DollarAmount amountToCompare) {
        return this.totalAmountInCents < amountToCompare.totalAmountInCents;
    }
    
    public boolean isLessThanOrEqualTo(DollarAmount amountToCompare) {
        return this.totalAmountInCents <= amountToCompare.totalAmountInCents;
    }
    
    public boolean isNegative() {
        return totalAmountInCents < 0;
    }
    
    public DollarAmount minus(DollarAmount amountToSubtract) {
        return new DollarAmount(this.totalAmountInCents - amountToSubtract.totalAmountInCents);
    }
    
    public DollarAmount plus(DollarAmount amountToAdd) {
        return new DollarAmount(this.totalAmountInCents + amountToAdd.totalAmountInCents);
    }

    public int compareTo(DollarAmount amountToCompare) {
        if(this.isGreaterThan(amountToCompare)) {
            return 1;
        } else if(this.isLessThan(amountToCompare)) {
            return -1;
        } else {
            return 0;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof DollarAmount) { //saying if its not null and is a dollar amount, then you can say the total amount below is equal
            return this.totalAmountInCents == ((DollarAmount)obj).totalAmountInCents; //comparing what you see above to the total dollar amount
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {//hash, how to order these things. need to return some integer that will uniquely identify this dollar amount.
    	return totalAmountInCents;
    }
    
//    new DollarAmount(3210).toString() new DollarAmount(1000).toString() → “$10.00” new DollarAmount(1).toString() → “$0.01”
    @Override
    public String toString() {
    	return "$" + getDollars() + "." + getCents();
    }
    
}


}

package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DollarAmountTest {

	private DollarAmount myDollarAmount;
	private DollarAmount newDollarAmount;
	private DollarAmount negDollarAmount;
	private int amount = 0;

	@Before
	public void setup() {
		myDollarAmount = new DollarAmount(10, 50);
		newDollarAmount = new DollarAmount(100);
		negDollarAmount = new DollarAmount(-2709);
	}

	@Test
	public void one_argument_constructor_init_properly() {
		Assert.assertEquals(0, newDollarAmount.getCents());
		Assert.assertEquals(1, newDollarAmount.getDollars());
	}

	@Test
	public void comparators_return_properly() {
		Assert.assertTrue(myDollarAmount.isGreaterThan(newDollarAmount));
		Assert.assertFalse(negDollarAmount.isGreaterThan(newDollarAmount));
		Assert.assertTrue(myDollarAmount.isGreaterThanOrEqualTo(newDollarAmount));
		Assert.assertFalse(negDollarAmount.isGreaterThanOrEqualTo(newDollarAmount));
		Assert.assertTrue(newDollarAmount.isLessThan(myDollarAmount));
		Assert.assertFalse(myDollarAmount.isLessThan(newDollarAmount));
		Assert.assertTrue(newDollarAmount.isLessThanOrEqualTo(myDollarAmount));
		Assert.assertFalse(myDollarAmount.isLessThanOrEqualTo(newDollarAmount));
		Assert.assertFalse(myDollarAmount.isNegative());
		Assert.assertTrue(negDollarAmount.isNegative());
		Assert.assertEquals(0, myDollarAmount.compareTo(myDollarAmount));
		Assert.assertEquals(1, myDollarAmount.compareTo(newDollarAmount));
		Assert.assertEquals(-1, negDollarAmount.compareTo(newDollarAmount));
	}

	@Test
	public void equals_to_override_returns_properly() {
		DollarAmount compare;
		Object myObj = new Object();
		Assert.assertFalse(myDollarAmount.equals(null));
		Assert.assertFalse(myDollarAmount.equals(myObj));
		compare = new DollarAmount(1050);
		Assert.assertTrue(myDollarAmount.equals(compare));
		compare = new DollarAmount(600);
		Assert.assertFalse(myDollarAmount.equals(compare));

	}

	@Test
	public void add_and_subtract_return_properly() {
		int lessCents = myDollarAmount.minus(newDollarAmount).hashCode();
		Assert.assertEquals(950, lessCents);
		int nonCents = myDollarAmount.plus(newDollarAmount).hashCode();
		Assert.assertEquals(1150, nonCents);
	}

	@Test
	public void to_string_returns_properly() {
		String test = myDollarAmount.toString();
		Assert.assertEquals("$10.50", test);
		DollarAmount someDollarAmount = new DollarAmount(0, 1);
		Assert.assertEquals("$0.01", someDollarAmount.toString());
	}
	
	@Test
	public void dollar_amount_times_returns_properly() {
		amount = 5;
		DollarAmount multiTest = new DollarAmount(500);
		Assert.assertEquals(multiTest, newDollarAmount.times(amount));
	}

}



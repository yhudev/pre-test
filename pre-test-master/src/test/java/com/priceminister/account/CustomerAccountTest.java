package com.priceminister.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.BasicAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 *
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 *
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 *
 */
public class CustomerAccountTest {

	private Account customerAccount;
	private AccountRule customerAccountRule;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		this.customerAccount = new BasicAccount();
		this.customerAccountRule = new CustomerAccountRule();
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		assertEquals(Double.valueOf(0.0d), customerAccount.getBalance());
	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 */
	@Test
	public void testAddPositiveAmount() {
		Double originalBalance = customerAccount.getBalance();
		Double addedAmount = 35.5d;
		try {
			customerAccount.add(addedAmount, this.customerAccountRule);
			assertEquals(addedAmount, Double.valueOf(customerAccount.getBalance() - originalBalance));
		} catch (IllegalInputValueException e) {
			fail("Illegal input value.");
		}
	}

	/**
	 * Adds invalid number of money to the account and checks that the new
	 * balance is as expected.
	 */
	@Test
	public void testAddNonPositiveAmount() {
		Double originalBalance = customerAccount.getBalance();
		Double addedAmount = 0d;
		try {
			customerAccount.add(addedAmount, this.customerAccountRule);
			assertEquals(addedAmount, Double.valueOf(customerAccount.getBalance() - originalBalance));
		} catch (IllegalInputValueException e) {
			fail("Wrong exception thrown.");
		}

		addedAmount = -42.5d;
		try {
			customerAccount.add(addedAmount, this.customerAccountRule);
			fail("Exception not thrown error.");
		} catch (IllegalInputValueException e) {
			// TODO: add catch exception
			assertEquals(addedAmount, e.getInputValue());
		}
	}

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the
	 * logic contained in CustomerAccountRule; feel free to refactor the
	 * existing code.
	 */
	@Test
	public void testWithdrawAndReportBalanceIllegalBalance() {
		Double withdrawAmount = customerAccount.getBalance() + 10;
		try {
			customerAccount.withdrawAndReportBalance(withdrawAmount, customerAccountRule);
			fail("Exception not thrown error.");
		} catch (IllegalBalanceException e) {
			// TODO: add catch exception
			assertEquals(Double.valueOf(-10d), e.getBalance());
		} catch (IllegalInputValueException e) {
			fail("Wrong exception thrown.");
		}
	}

	@Test
	public void testWithdrawAndReportBalanceValidBalance() {
		Double withdrawnAmount = 10d;
		try {
			customerAccount.add(withdrawnAmount, customerAccountRule);
			customerAccount.withdrawAndReportBalance(withdrawnAmount, customerAccountRule);
		} catch (IllegalInputValueException | IllegalBalanceException e) {
			fail("Wrong exception thrown.");
		}
	}

}
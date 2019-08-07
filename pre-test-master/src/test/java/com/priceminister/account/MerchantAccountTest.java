package com.priceminister.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.BasicAccount;
import com.priceminister.account.implementation.MerchantAccountRule;
import com.priceminister.constants.AccountRuleConstant;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;

public class MerchantAccountTest {

	private Account merchantAccount;
	private AccountRule merchantAccountRule;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() {
		this.merchantAccount = new BasicAccount();
		this.merchantAccountRule = new MerchantAccountRule();
	}

	@Test
	public void testWithdrawAndReportBalanceValidBalanceInvalidPlatfond() {
		Double withdrawnAmount = 10d + AccountRuleConstant.MERCHANT;
		try {
			merchantAccount.add(withdrawnAmount, merchantAccountRule);
			merchantAccount.withdrawAndReportBalance(withdrawnAmount, merchantAccountRule);
			fail("Expected exception not thrown.");
		} catch (IllegalBalanceException e) {
			fail("Wrong exception thrown.");
		} catch (IllegalInputValueException e) {
			// TODO: add catch exception
			assertEquals(withdrawnAmount, e.getInputValue());
		}
	}
}

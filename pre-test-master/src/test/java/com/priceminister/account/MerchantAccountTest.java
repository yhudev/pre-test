package com.priceminister.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.BasicAccount;
import com.priceminister.account.implementation.MerchantAccountRule;
import com.priceminister.constants.AccountRuleConstant;
import com.priceminister.exceptions.IllegalInputValueException;
import com.priceminister.exceptions.IllegalOperationException;

/**
 * Tests for merchant with merchant account rule.
 *
 * @author Yue HU
 *
 */
public class MerchantAccountTest {

    private Account merchantAccount;
    private AccountRule merchantAccountRule;

    /**
     * Set up for merchant tests. Create account and rule.
     */
    @Before
    public void setUp() {
        this.merchantAccount = new BasicAccount();
        this.merchantAccountRule = new MerchantAccountRule();
    }

    /**
     * Tests that withdraw amount above plafond results.
     */
    @Test
    public void testWithdrawAndReportBalanceValidBalanceInvalidPlatfond() {
        Double withdrawnAmount = 10d + AccountRuleConstant.PLAFOND_MERCHANT;
        try {
            merchantAccount.withdrawAndReportBalance(withdrawnAmount, merchantAccountRule);
            fail("Expected exception not thrown.");
        } catch (IllegalInputValueException e) {
            assertEquals(withdrawnAmount, e.getIllegalValue());
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException thrown.");
        }
    }

    /**
     * Tests that withdraw amount above plafond results in dry run mode.
     */
    @Test
    public void testWithdrawAndReportBalanceValidBalanceInvalidPlatfondDryRun() {
        Double originalBalance = 0.0d;
        Double withdrawnAmount = 10d + AccountRuleConstant.PLAFOND_MERCHANT;
        try {
            merchantAccount.withdrawAndReportBalance(withdrawnAmount, merchantAccountRule, true);
            fail("Expected exception not thrown.");
        } catch (IllegalInputValueException e) {
            assertEquals(withdrawnAmount, e.getIllegalValue());
            assertEquals(Double.valueOf(0d), Double.valueOf(merchantAccount.getBalance() - originalBalance));
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException thrown.");
        }
    }
}

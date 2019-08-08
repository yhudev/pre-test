package com.priceminister.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.BasicAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;
import com.priceminister.exceptions.IllegalOperationException;

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
     * Adds zero to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddZeroAmount() {
        Double originalBalance = customerAccount.getBalance();
        Double addedAmount = 0d;
        try {
            customerAccount.add(addedAmount, this.customerAccountRule);
            assertEquals(addedAmount, Double.valueOf(customerAccount.getBalance() - originalBalance));
        } catch (IllegalInputValueException e) {
            fail("Unexpected IllegalInputValueException thrown.");
        }
    }

    /**
     * Adds invalid number of money to the account and checks that the new
     * balance is as expected.
     */
    @Test
    public void testAddNegativeAmount() {
        Double addedAmount = -42.5d;
        try {
            customerAccount.add(addedAmount, this.customerAccountRule);
            fail("Expected IllegalInputValueException not thrown.");
        } catch (IllegalInputValueException e) {
            assertEquals(addedAmount, e.getIllegalValue());
        }
    }

    /**
     * Test for dry run mode of add positive amount.
     */
    @Test
    public void testAddPositiveAmountDryRun() {
        Double originalBalance = customerAccount.getBalance();
        Double addedAmount = 35.5d;
        try {
            customerAccount.add(addedAmount, this.customerAccountRule, true);
            assertEquals(Double.valueOf(0d), Double.valueOf(customerAccount.getBalance() - originalBalance));
        } catch (IllegalInputValueException e) {
            fail("Illegal input value.");
        }
    }

    /**
     * Adds invalid number of money to the account and checks that the new
     * balance is as expected.
     */
    @Test
    public void testAddNegativeAmountDryRun() {
        Double originalBalance = customerAccount.getBalance();
        Double addedAmount = -42.5d;
        try {
            customerAccount.add(addedAmount, this.customerAccountRule, true);
            fail("Expected IllegalInputValueException not thrown.");
        } catch (IllegalInputValueException e) {
            assertEquals(addedAmount, e.getIllegalValue());
            assertEquals(Double.valueOf(0d), Double.valueOf(customerAccount.getBalance() - originalBalance));
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
            fail("Expected IllegalBalanceException not thrown.");
        } catch (IllegalBalanceException e) {
            assertEquals(Double.valueOf(-10d), e.getIllegalValue());
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException exception thrown.");
        }
    }

    /**
     * Tests that withdraw operation works.
     */
    @Test
    public void testWithdrawAndReportBalanceValidBalance() {
        Double withdrawnAmount = 10d;
        try {
            customerAccount.add(withdrawnAmount, customerAccountRule);
            Double originalBalance = customerAccount.getBalance();
            customerAccount.withdrawAndReportBalance(withdrawnAmount, customerAccountRule);
            assertEquals(originalBalance, Double.valueOf(customerAccount.getBalance() + withdrawnAmount));
        } catch (IllegalOperationException e) {
            fail("Wrong exception thrown.");
        }
    }

    /**
     * Tests that dry run for illegal balance withdraw.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalanceDryRun() {
        Double originalBalance = customerAccount.getBalance();
        Double withdrawAmount = originalBalance + 10;
        try {
            customerAccount.withdrawAndReportBalance(withdrawAmount, customerAccountRule, true);
            fail("Expected IllegalBalanceException not thrown.");
        } catch (IllegalBalanceException e) {
            assertEquals(Double.valueOf(-10d), e.getIllegalValue());
            assertEquals(Double.valueOf(0d), Double.valueOf(customerAccount.getBalance() - originalBalance));
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException exception thrown.");
        }
    }

    /**
     * Tests that withdraw operation in dry run mode.
     */
    @Test
    public void testWithdrawAndReportBalanceValidBalanceDryRun() {
        Double originalBalance = 0.0d;
        Double withdrawnAmount = 10d;
        try {
            customerAccount.add(withdrawnAmount, customerAccountRule);
            originalBalance = customerAccount.getBalance();
            customerAccount.withdrawAndReportBalance(withdrawnAmount, customerAccountRule, true);
            assertEquals(Double.valueOf(0d), Double.valueOf(customerAccount.getBalance() - originalBalance));
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationExceptionexception thrown in dry run test.");
        }
    }

}

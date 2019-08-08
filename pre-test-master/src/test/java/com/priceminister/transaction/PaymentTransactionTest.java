package com.priceminister.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.BasicAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import com.priceminister.account.implementation.MerchantAccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;
import com.priceminister.exceptions.IllegalOperationException;
import com.priceminister.transaction.implementation.PaymentTransaction;

/**
 * Tests for payment transaction.
 *
 * @author Yue HU
 *
 */
public class PaymentTransactionTest {
    private BasicAccount customerAccount;
    private BasicAccount merchantAccount;
    private CustomerAccountRule customerAccountRule;
    private MerchantAccountRule merchantAccountRule;
    private PaymentTransaction paymentTransaction;

    /**
     * Set up for payment transaction tests
     *
     * @throws IllegalInputValueException
     */
    @Before
    public void setUp() throws IllegalInputValueException {
        this.customerAccount = new BasicAccount();
        this.customerAccountRule = new CustomerAccountRule();
        customerAccount.add(100d, this.customerAccountRule);
        this.merchantAccount = new BasicAccount();
        this.merchantAccountRule = new MerchantAccountRule();
        this.paymentTransaction = new PaymentTransaction();
    }

    /**
     * Tests payment with invalid amount.
     */
    @Test
    public void testPaymentWithInvalidAmount() {
        Double transferAmount = -10d;
        Double originalCustomerBalance = customerAccount.getBalance();
        Double originalMerchantBalance = merchantAccount.getBalance();
        try {
            paymentTransaction.transfer(customerAccount, customerAccountRule, merchantAccount, merchantAccountRule,
                    transferAmount);
            fail("Expected exception not thrown.");
        } catch (IllegalInputValueException e) {
            assertEquals(transferAmount, e.getIllegalValue());
            assertEquals(originalCustomerBalance, customerAccount.getBalance());
            assertEquals(originalMerchantBalance, merchantAccount.getBalance());
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException thrown in testPaymentWithInvalidAmount.");
        }
    }

    /**
     * Tests payment with illegal balance.
     */
    @Test
    public void testPaymentWithIllegalBalance() {
        Double transferAmount = 200d;
        Double originalCustomerBalance = customerAccount.getBalance();
        Double originalMerchantBalance = merchantAccount.getBalance();
        try {
            paymentTransaction.transfer(customerAccount, customerAccountRule, merchantAccount, merchantAccountRule,
                    transferAmount);
            fail("Expected exception not thrown.");
        } catch (IllegalBalanceException e) {
            assertEquals(customerAccount.getBalance(), Double.valueOf(e.getIllegalValue() + transferAmount));
            assertEquals(originalCustomerBalance, customerAccount.getBalance());
            assertEquals(originalMerchantBalance, merchantAccount.getBalance());
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException thrown in testPaymentWithIllegalBalance.");
        }
    }

    /**
     * Tests payment with valid amount.
     */
    @Test
    public void testPaymentWithValidAmount() {
        Double transferAmount = 20d;
        Double originalCustomerBalance = customerAccount.getBalance();
        Double originalMerchantBalance = merchantAccount.getBalance();
        try {
            paymentTransaction.transfer(customerAccount, customerAccountRule, merchantAccount, merchantAccountRule,
                    transferAmount);
            assertEquals(originalCustomerBalance, Double.valueOf(customerAccount.getBalance() + transferAmount));
            assertEquals(originalMerchantBalance, Double.valueOf(merchantAccount.getBalance() - transferAmount));
        } catch (IllegalOperationException e) {
            fail("Wrong IllegalOperationException thrown in testPaymentWithValidAmount.");
        }
    }
}

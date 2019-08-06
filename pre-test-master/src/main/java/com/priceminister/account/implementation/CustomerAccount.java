package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

/**
 * This class represents a customer account.
 *
 * @author Yue HU
 *
 */
public class CustomerAccount implements Account {

    private Double balance;

    /**
     * Constructor for customer account. Initialize balance.
     */
    public CustomerAccount() {
        this.resetBalance();
    }

    public void add(Double addedAmount) {
        this.balance += addedAmount;
    }

    public Double getBalance() {
        return this.balance;
    }

    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
        // TODO Auto-generated method stub
        return null;
    }

    private void resetBalance() {
        this.balance = 0.0d;
    }
}

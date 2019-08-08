package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;
import com.priceminister.exceptions.IllegalOperationException;

/**
 * This class represents a basic account.
 *
 * @author Yue HU
 *
 */
public class BasicAccount implements Account {

    private Double balance;

    /**
     * Constructor for customer account. Initialize balance.
     */
    public BasicAccount() {
        this.resetBalance();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.priceminister.account.Account#add(java.lang.Double,
     * com.priceminister.account.AccountRule)
     */
    @Override
    public void add(Double addedAmount, AccountRule rule) throws IllegalInputValueException {
        this.add(addedAmount, rule, false);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.priceminister.account.Account#withdrawAndReportBalance(java.lang.
     * Double, com.priceminister.account.AccountRule)
     */
    @Override
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalOperationException {
        return this.withdrawAndReportBalance(withdrawnAmount, rule, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.priceminister.account.Account#add(java.lang.Double,
     * com.priceminister.account.AccountRule, boolean)
     */
    @Override
    public void add(Double addedAmount, AccountRule rule, boolean dryRun) throws IllegalInputValueException {
        if (!rule.addAmountValidated(addedAmount)) {
            throw new IllegalInputValueException(addedAmount);
        }
        if (!dryRun) {
            this.balance += addedAmount;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.priceminister.account.Account#withdrawAndReportBalance(java.lang.
     * Double, com.priceminister.account.AccountRule, boolean)
     */
    @Override
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule, boolean dryRun)
            throws IllegalOperationException {
        if (!rule.plafondChecked(withdrawnAmount)) {
            throw new IllegalInputValueException(withdrawnAmount);
        }
        Double resultingAccountBalance = this.balance - withdrawnAmount;
        if (!rule.withdrawPermitted(resultingAccountBalance)) {
            throw new IllegalBalanceException(resultingAccountBalance);
        }
        if (!dryRun) {
            this.balance = resultingAccountBalance;
        }
        return this.balance;

    }

    /*
     * (non-Javadoc)
     *
     * @see com.priceminister.account.Account#getBalance()
     */
    @Override
    public Double getBalance() {
        return this.balance;
    }

    private void resetBalance() {
        this.balance = 0.0d;
    }
}

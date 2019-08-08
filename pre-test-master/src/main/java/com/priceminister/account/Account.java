package com.priceminister.account;

import com.priceminister.exceptions.IllegalInputValueException;
import com.priceminister.exceptions.IllegalOperationException;

/**
 * This class represents a simple account. It doesn't handle different
 * currencies, all money is supposed to be of standard currency EUR.
 */
public interface Account {

    /**
     * Adds money to this account.
     *
     * @param addedAmount
     *            - the money to add
     * @param rule
     *            - the AccountRule that defines which balance is allowed for
     *            this account
     * @throws IllegalInputValueException
     *             if the input value is not permitted
     */
    public void add(Double addedAmount, AccountRule rule) throws IllegalInputValueException;

    /**
     * Adds money to this account.
     *
     * @param addedAmount
     *            - the money to add
     * @param rule
     *            - the AccountRule that defines which balance is allowed for
     *            this account
     * @param dryRun
     *            - test add operation legality without operate the balance if
     *            this parameter is true.
     * @throws IllegalInputValueException
     *             if the input value is not permitted
     */
    public void add(Double addedAmount, AccountRule rule, boolean dryRun) throws IllegalInputValueException;

    /**
     * Withdraws money from the account.
     *
     * @param withdrawnAmount
     *            - the money to withdraw
     * @param rule
     *            - the AccountRule that defines which balance is allowed for
     *            this account
     * @return the remaining account balance
     * @throws IllegalOperationException
     *             if the withdrawal leaves the account with a forbidden balance
     *             or the input value is not permitted
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalOperationException;

    /**
     * Withdraws money from the account.
     *
     * @param withdrawnAmount
     *            - the money to withdraw
     * @param rule
     *            - the AccountRule that defines which balance is allowed for
     *            this account
     * @param dryRun
     *            - test add operation legality without operate the balance if
     *            this parameter is true.
     * @return the remaining account balance
     * @throws IllegalOperationException
     *             if the withdrawal leaves the account with a forbidden balance
     *             or the input value is not permitted
     */
    public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule, boolean dryRun)
            throws IllegalOperationException;

    /**
     * Gets the current account balance.
     *
     * @return the account's balance
     */
    public Double getBalance();
}

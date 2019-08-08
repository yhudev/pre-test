package com.priceminister.transaction;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.exceptions.IllegalOperationException;

/**
 * Transaction interface.
 *
 * @author Yue HU
 *
 */
public interface Transaction {
    /**
     * Transfer money from one account to another.
     *
     * @param fromAccount
     *            - Withdraw account
     * @param fromAccountRule
     *            - Withdraw account rule
     * @param toAccount
     *            - Target account
     * @param toAccountRule
     *            - Target account rule
     * @param transferAmount
     *            - Transfer amount
     * @return true if the transaction success, else false
     * @throws IllegalOperationException
     *             thrown IllegalBalanceException if illegal balance in withdraw
     *             account, thrown IllegalInputValueException if transfer amount
     *             is not permitted
     */
    public boolean transfer(Account fromAccount, AccountRule fromAccountRule, Account toAccount,
            AccountRule toAccountRule, Double transferAmount) throws IllegalOperationException;
}

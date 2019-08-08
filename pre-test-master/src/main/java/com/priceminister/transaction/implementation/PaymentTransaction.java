package com.priceminister.transaction.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.exceptions.IllegalOperationException;
import com.priceminister.transaction.Transaction;

/**
 * Transaction implementation for payment. Implements {@code Transaction}.
 *
 * @author Yue HU
 *
 */
public class PaymentTransaction implements Transaction {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.priceminister.transaction.Transaction#transfer(com.priceminister.
     * account.Account, com.priceminister.account.AccountRule,
     * com.priceminister.account.Account, com.priceminister.account.AccountRule,
     * java.lang.Double)
     */
    @Override
    public boolean transfer(Account fromAccount, AccountRule fromAccountRule, Account toAccount,
            AccountRule toAccountRule, Double transferAmount) throws IllegalOperationException {
        // Dry run to test rules.
        fromAccount.withdrawAndReportBalance(transferAmount, fromAccountRule, true);
        toAccount.add(transferAmount, toAccountRule, true);
        // Do the operations.
        fromAccount.withdrawAndReportBalance(transferAmount, fromAccountRule);
        toAccount.add(transferAmount, toAccountRule);
        return true;
    }

}

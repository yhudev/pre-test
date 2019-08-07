package com.priceminister.transaction.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;
import com.priceminister.transaction.Transaction;

public class PaymentTransaction implements Transaction {

	@Override
	public boolean transfer(Account fromAccount, AccountRule fromAccountRule, Account toAccount,
			AccountRule toAccountRule, Double transferAmount)
			throws IllegalBalanceException, IllegalInputValueException {
		fromAccount.withdrawCheck(transferAmount, fromAccountRule);
		toAccount.addCheck(transferAmount, toAccountRule);
		fromAccount.withdrawCheck(transferAmount, fromAccountRule);
		toAccount.addCheck(transferAmount, toAccountRule);
		fromAccount.withdrawAndReportBalance(transferAmount, fromAccountRule);
		toAccount.add(transferAmount, toAccountRule);
		return true;
	}

}

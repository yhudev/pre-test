package com.priceminister.transaction;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;

public interface Transaction {
	public boolean transfer(Account fromAccount, AccountRule fromAccountRule, Account toAccount,
			AccountRule toAccountRule, Double transferAmount)
			throws IllegalBalanceException, IllegalInputValueException;
}

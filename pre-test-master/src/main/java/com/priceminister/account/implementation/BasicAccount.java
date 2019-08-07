package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.exceptions.IllegalBalanceException;
import com.priceminister.exceptions.IllegalInputValueException;

/**
 * This class represents a customer account.
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

	@Override
	public boolean addCheck(Double addedAmount, AccountRule rule) throws IllegalInputValueException {
		if (!rule.addAmountValidated(addedAmount)) {
			throw new IllegalInputValueException(addedAmount);
		}
		return true;
	}

	@Override
	public void add(Double addedAmount, AccountRule rule) throws IllegalInputValueException {
		// TODO
		this.balance += addedAmount;
	}

	@Override
	public Double getBalance() {
		return this.balance;
	}

	@Override
	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
			throws IllegalBalanceException, IllegalInputValueException {
		if (!rule.plafondChecked(withdrawnAmount)) {
			throw new IllegalInputValueException(withdrawnAmount);
		}
		Double resultingAccountBalance = this.balance - withdrawnAmount;
		if (!rule.withdrawPermitted(resultingAccountBalance)) {
			throw new IllegalBalanceException(resultingAccountBalance);
		}
		this.balance = resultingAccountBalance;
		return this.balance;

	}

	private void resetBalance() {
		this.balance = 0.0d;
	}

	@Override
	public Double withdrawCheck(Double withdrawnAmount, AccountRule rule)
			throws IllegalBalanceException, IllegalInputValueException {
		// TODO Auto-generated method stub
		return null;
	}
}

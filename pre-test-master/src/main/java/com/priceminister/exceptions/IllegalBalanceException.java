package com.priceminister.exceptions;

public class IllegalBalanceException extends Exception {

	private static final long serialVersionUID = -9204191749972551939L;

	private final Double balance;

	public IllegalBalanceException(Double illegalBalance) {
		balance = illegalBalance;
	}

	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	@Override
	public String toString() {
		return "Illegal account balance: " + balance;
	}
}

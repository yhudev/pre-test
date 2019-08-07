package com.priceminister.exceptions;

public class IllegalInputValueException extends Exception {

	private static final long serialVersionUID = 7069980483481898739L;

	private final Double inputValue;

	public IllegalInputValueException(Double inputValue) {
		this.inputValue = inputValue;
	}

	/**
	 * @return the inputValue
	 */
	public Double getInputValue() {
		return inputValue;
	}

	@Override
	public String toString() {
		return "Illegal input value: " + inputValue;
	}
}

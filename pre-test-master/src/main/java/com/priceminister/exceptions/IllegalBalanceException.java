package com.priceminister.exceptions;

/**
 * Illegal balance exception. Thrown when the balance is not in expected range.
 *
 * @author Yue HU
 *
 */
public class IllegalBalanceException extends IllegalOperationException {

    private static final long serialVersionUID = -9204191749972551939L;

    /**
     * Constructor {@link IllegalBalanceException}
     *
     * @param illegalValue
     *            Illegal value
     */
    public IllegalBalanceException(Double illegalValue) {
        super(illegalValue);
    }

    @Override
    public String toString() {
        return "Illegal account balance: " + this.getIllegalValue();
    }
}

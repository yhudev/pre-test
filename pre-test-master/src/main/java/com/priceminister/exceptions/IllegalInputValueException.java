package com.priceminister.exceptions;

/**
 * Illegal input value exception. Thrown when the input value is not in expected
 * range.
 *
 * @author Yue HU
 *
 */
public class IllegalInputValueException extends IllegalOperationException {

    private static final long serialVersionUID = 7069980483481898739L;

    /**
     * Constructor {@link IllegalInputValueException}
     *
     * @param illegalValue
     */
    public IllegalInputValueException(Double illegalValue) {
        super(illegalValue);
    }

    @Override
    public String toString() {
        return "Illegal input value: " + this.getIllegalValue();
    }
}

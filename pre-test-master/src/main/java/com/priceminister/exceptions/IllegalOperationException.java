package com.priceminister.exceptions;

/**
 * Illegal operation exception.
 *
 * @author Yue HU
 *
 */
public abstract class IllegalOperationException extends Exception {

    private static final long serialVersionUID = 187859124861981069L;

    private final Double illegalValue;

    /**
     * Constructor {@link IllegalOperationException}
     *
     * @param illegalValue
     *            Illegal value that causes this exception.
     */
    public IllegalOperationException(Double illegalValue) {
        this.illegalValue = illegalValue;
    }

    /**
     * Constructor {@link IllegalOperationException}
     *
     * @param illegalValue
     *            Illegal value that causes this exception.
     * @param e
     *            Exception reason.
     */
    public IllegalOperationException(Double illegalValue, Throwable e) {
        super(e);
        this.illegalValue = illegalValue;
    }

    /**
     * Constructor {@link IllegalOperationException}
     *
     * @param e
     *            Exception reason.
     */
    public IllegalOperationException(Throwable e) {
        super(e);
        this.illegalValue = Double.NaN;
    }

    /**
     * @return Illegal value that causes the exception.
     */
    public Double getIllegalValue() {
        return illegalValue;
    }

}

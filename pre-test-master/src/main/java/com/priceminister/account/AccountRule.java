package com.priceminister.account;

/**
 * Checks if the requested operation is permitted.
 */
public interface AccountRule {

    /**
     * Checks if the resulting account balance after a withdrawal is OK for the
     * specific type of account.
     *
     * @param resultingAccountBalance
     *            - the amount resulting of the withdrawal
     * @return true if the operation is permitted, false otherwise
     */
    public boolean withdrawPermitted(Double resultingAccountBalance);

    /**
     * Checks if the addedAmount is valid.
     *
     * @param addedAmount
     *            - the amount to be added
     * @return true if the amount is valid, false otherwise
     */
    public boolean addAmountValidated(Double addedAmount);

    /**
     * Checks if the withdrawn amount is valid.
     *
     * @param withdrawnAmount
     *            the amount to be withdrawn.
     * @return true if withdrawn amount is under plafond.
     */
    public boolean plafondChecked(Double withdrawnAmount);
}

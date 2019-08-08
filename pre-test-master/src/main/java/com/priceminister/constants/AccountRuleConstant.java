package com.priceminister.constants;

/**
 * Constant used in account rules.
 *
 * @author Yue HU
 *
 */
public class AccountRuleConstant {

    private AccountRuleConstant() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Plafond of customer account
     */
    public static final Double PLAFOND_CUSTOMER = Double.MAX_VALUE;
    /**
     * Plafond of merchant account
     */
    public static final Double PLAFOND_MERCHANT = 1000d;
}

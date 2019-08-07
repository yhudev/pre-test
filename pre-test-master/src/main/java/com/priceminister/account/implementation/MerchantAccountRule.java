/*
 * =============================================================================
 *
 *   PRICE MINISTER APPLICATION
 *   Copyright (c) 2000 Babelstore.
 *   All Rights Reserved.
 *
 *   $Source$
 *   $Revision$
 *   $Date$
 *   $Author$
 *
 * =============================================================================
 */
package com.priceminister.account.implementation;

import com.priceminister.account.AccountRule;
import com.priceminister.constants.AccountRuleConstant;

public class MerchantAccountRule implements AccountRule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.priceminister.account.AccountRule#withdrawPermitted(java.lang.Double)
	 */
	@Override
	public boolean withdrawPermitted(Double resultingAccountBalance) {
		return resultingAccountBalance >= 0;
	}

	@Override
	public boolean addAmountValidated(Double addedAmount) {
		return addedAmount >= 0;
	}

	@Override
	public boolean plafondChecked(Double withdrawnAmount) {
		return withdrawnAmount <= AccountRuleConstant.MERCHANT;
	}

}

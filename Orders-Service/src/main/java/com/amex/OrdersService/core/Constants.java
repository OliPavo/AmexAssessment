package com.amex.OrdersService.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Constants {
	static final String NAME_APPLE = "Apple";
	static final String NAME_ORANGE = "Orange";
	static final BigDecimal PRICE_APPLE = new BigDecimal("0.60").setScale(2,RoundingMode.HALF_UP);
	static final BigDecimal PRICE_ORANGE = new BigDecimal("0.25").setScale(2,RoundingMode.HALF_UP);	
	static final String EXCEPTION_ITEM_NOT_FOUND = "Item not found. Please order valid items only(Apple and/or orange)";
	static final String EXCEPTION_INVALID_QUANTITY = "Item quantity invalid. Quantity must be 0 or greater.";
	static final String EXCEPTION_DUPLICATE_LINE_ITEM = "Duplicate line items found in order. Update quantity and have unique items seperate.";
	static final int DISCOUNT_APPLE_QUANTITY = 2;
	static final int DISCOUNT_ORANGE_QUANTITY = 3;
}

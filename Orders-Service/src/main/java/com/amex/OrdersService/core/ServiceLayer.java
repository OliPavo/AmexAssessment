package com.amex.OrdersService.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.amex.OrdersService.exception.DuplicateLineItemException;
import com.amex.OrdersService.exception.InvalidQuantityException;
import com.amex.OrdersService.exception.ItemNotFoundException;
import com.amex.OrdersService.infra.Order;
import com.amex.OrdersService.infra.Item;
import com.amex.OrdersService.infra.Summary;

@Service
public class ServiceLayer {
	
	/**
	* Processes Order by validating items and returns order summary after calculating total cost.
	*
	* @param  order  Order to be processed by item name and item quantity.
	* @return  summary   Returns summary object with order, total cost and message.
	*/
	public Summary processOrder(Order order) {
		Summary summary = new Summary();
		
		HashMap<String, Integer> itemCounts = new HashMap<>();
		
		BigDecimal subTotalCost = BigDecimal.ZERO;
		
		StringBuilder message = new StringBuilder();
		
		//Validating item names and quantity then calculating total cost
		for(Item item : order.getItems()) {
			if(Constants.NAME_APPLE.equalsIgnoreCase(item.getItemName())) {
				item.setItemPrice(Constants.PRICE_APPLE);
			} else if(Constants.NAME_ORANGE.equalsIgnoreCase(item.getItemName())) {
				item.setItemPrice(Constants.PRICE_ORANGE);
			} else {
				throw new ItemNotFoundException(Constants.EXCEPTION_ITEM_NOT_FOUND);
			}		
			
			if(item.getItemQuantity() < 0) {
				throw new InvalidQuantityException(Constants.EXCEPTION_INVALID_QUANTITY);
			}
			
			if(itemCounts.containsKey(item.getItemName())) {
				throw new DuplicateLineItemException(Constants.EXCEPTION_DUPLICATE_LINE_ITEM);
			}
			
			itemCounts.putIfAbsent(item.getItemName(), item.getItemQuantity());
						
			subTotalCost = subTotalCost.add(item.getItemPrice()
					.multiply(BigDecimal.valueOf(item.getItemQuantity()))).setScale(2,RoundingMode.HALF_UP);
		}		
		
		order.setTotalCost(subTotalCost.subtract(calculateDiscountTotal(itemCounts)));	
		
		summary.setOrder(order);
		message.append(MessageFormat.format("Total cost of the order: ${0} Total discount applied: ${1}", 
								order.getTotalCost().toString(), calculateDiscountTotal(itemCounts).toString()));
		
		summary.setMessage(message.toString());
			
		return summary;
	}
	
	/**
	* Calculates the total discount after applying offers for Apples and Oranges.
	*
	* @param  itemCounts  The quantity of each item in the order.
	* @return  discountTotal   Returns the total amount discounted from the items in the order.
	*/
	private BigDecimal calculateDiscountTotal(HashMap<String, Integer> itemCounts) {
		BigDecimal discountTotal = BigDecimal.ZERO;
		
		if(itemCounts.containsKey(Constants.NAME_APPLE) && (itemCounts.get(Constants.NAME_APPLE) >= Constants.DISCOUNT_APPLE_QUANTITY)) {
			discountTotal = discountTotal.add((Constants.PRICE_APPLE)
					.multiply(BigDecimal.valueOf(itemCounts.get(Constants.NAME_APPLE)/Constants.DISCOUNT_APPLE_QUANTITY)));
		}
		
		if(itemCounts.containsKey(Constants.NAME_ORANGE) && itemCounts.get(Constants.NAME_ORANGE) >= Constants.DISCOUNT_ORANGE_QUANTITY) {
			discountTotal = discountTotal.add((Constants.PRICE_ORANGE)
					.multiply(BigDecimal.valueOf(itemCounts.get(Constants.NAME_ORANGE)/Constants.DISCOUNT_ORANGE_QUANTITY)));
		}
		
		return discountTotal;
	}
}

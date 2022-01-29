package com.amex.OrdersService.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;

import org.springframework.stereotype.Service;

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
		
		BigDecimal totalCost = BigDecimal.ZERO;
		
		StringBuilder message = new StringBuilder();
		
		//Validating item names and quantity then calculating total cost
		for(Item item: order.getItems()) {
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
			
			totalCost = totalCost.add(item.getItemPrice().multiply(BigDecimal.valueOf(item.getItemQuantity()))).setScale(2,RoundingMode.HALF_UP);
		}
		
		order.setTotalCost(totalCost);	
		
		summary.setOrder(order);
		message.append(MessageFormat.format("Total cost of the order: ${0}", totalCost.toString()));
		summary.setMessage(message.toString());
			
		return summary;
	}
}

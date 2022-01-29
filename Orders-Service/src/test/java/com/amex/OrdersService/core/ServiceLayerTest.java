package com.amex.OrdersService.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amex.OrdersService.exception.InvalidQuantityException;
import com.amex.OrdersService.exception.ItemNotFoundException;
import com.amex.OrdersService.infra.Item;
import com.amex.OrdersService.infra.Order;
import com.amex.OrdersService.infra.Summary;

public class ServiceLayerTest {
	private ServiceLayer serviceLayer;
	
	@Before
    public void setUp() {
        serviceLayer = new ServiceLayer();
    }

	@Test
    public void shouldGenerateCorrectSummaryForTwoItemOrder() {
		Order order = new Order();
		Item item1 = new Item("Apple", 7);
		Item item2 = new Item("Orange", 8);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $6.20");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test(expected = ItemNotFoundException.class)
    public void shouldThrowExceptionForInvalidItemInOrder() {
		Order order = new Order();
		Item item1 = new Item("Bagel", 4);
		List<Item> items = new ArrayList<>();
		items.add(item1);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.00");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test(expected = InvalidQuantityException.class)
    public void shouldThrowExceptionForInvalidItemQuantityInOrder() {
		Order order = new Order();
		Item item1 = new Item("Apple", -2);
		List<Item> items = new ArrayList<>();
		items.add(item1);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.00");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
}

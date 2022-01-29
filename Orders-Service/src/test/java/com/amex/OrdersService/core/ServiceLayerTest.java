package com.amex.OrdersService.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.amex.OrdersService.exception.InvalidQuantityException;
import com.amex.OrdersService.exception.ItemNotFoundException;
import com.amex.OrdersService.exception.DuplicateLineItemException;
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
    public void shouldGenerateCorrectSummaryForTwoItemOrderAfterBothDiscountOffers() {
		Order order = new Order();
		Item item1 = new Item("Apple", 2);
		Item item2 = new Item("Orange", 3);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.10 Total discount applied: $0.85");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test(expected = DuplicateLineItemException.class)
    public void shouldThrowExceptionForDuplicateItemsInOrder() {
		Order order = new Order();
		Item item1 = new Item("Apple", 2);
		Item item2 = new Item("Apple", 3);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.10 Total discount applied: $0.85");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test
    public void shouldGenerateCorrectSummaryForTwoItemOrderAfterBothDiscountOffersDoubled() {
		Order order = new Order();
		Item item1 = new Item("Apple", 4);
		Item item2 = new Item("Orange", 6);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $2.20 Total discount applied: $1.70");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test
    public void shouldGenerateCorrectSummaryForTwoItemOrderAfterBothDiscountOffersPlusRemainder() {
		Order order = new Order();
		Item item1 = new Item("Apple", 7);
		Item item2 = new Item("Orange", 10);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $4.15 Total discount applied: $2.55");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test
    public void shouldGenerateCorrectSummaryForOrangeDiscountOfferOnly() {
		Order order = new Order();
		Item item1 = new Item("Apple", 1);
		Item item2 = new Item("Orange", 3);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.10 Total discount applied: $0.25");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test
    public void shouldGenerateCorrectSummaryForOrangeDiscountOfferOnlyPlusRemainder() {
		Order order = new Order();
		Item item1 = new Item("Apple", 1);
		Item item2 = new Item("Orange", 4);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.35 Total discount applied: $0.25");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test
    public void shouldGenerateCorrectSummaryForAppleDiscountOfferOnly() {
		Order order = new Order();
		Item item1 = new Item("Apple", 2);
		Item item2 = new Item("Orange", 1);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $0.85 Total discount applied: $0.60");
     
        Summary fromService = serviceLayer.processOrder(order);

        assertEquals(expectedOutput.getMessage(), fromService.getMessage());
    }
	
	@Test
    public void shouldGenerateCorrectSummaryForAppleDiscountOfferOnlyPlusRemainder() {
		Order order = new Order();
		Item item1 = new Item("Apple", 3);
		Item item2 = new Item("Orange", 1);
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
        order.setItems(items);

        Summary expectedOutput = new Summary();
        expectedOutput.setOrder(order);
        expectedOutput.setMessage("Total cost of the order: $1.45 Total discount applied: $0.60");
     
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

package com.amex.OrdersService.infra;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Embeddable
public class Item {
	@NotEmpty(message = "Must provide item name!")
	private String itemName;
	private BigDecimal itemPrice;
	@PositiveOrZero(message = "Quantity must be 0 or greater!")
	private int itemQuantity;
	
	public Item(String name, BigDecimal price) {
		itemName = name;
		itemPrice = price;
	}
	
	public Item(String name, int quantity) {
		itemName = name;
		itemQuantity = quantity;
	}
	
	public Item() {
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public BigDecimal getItemPrice() {
		return itemPrice;
	}
	
	public void setItemPrice(BigDecimal productPrice) {
		this.itemPrice = productPrice;
	}
	
	public int getItemQuantity() {
		return itemQuantity;
	}
	
	public void setItemQuantity(int quantity) {
		this.itemQuantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemPrice, itemName, itemQuantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(itemPrice, other.itemPrice)
				&& Objects.equals(itemName, other.itemName) && itemQuantity == other.itemQuantity;
	}
}

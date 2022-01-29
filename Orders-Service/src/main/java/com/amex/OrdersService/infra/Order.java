package com.amex.OrdersService.infra;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="orders")
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
	private int orderId;
	@NotNull(message = "Must provide items to order!")
	@ElementCollection
	private List<Item> items;
	private BigDecimal discountCost;
	private BigDecimal totalCost;
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public BigDecimal getDiscountCost() {
		return discountCost;
	}

	public void setDiscountCost(BigDecimal discountCost) {
		this.discountCost = discountCost;
	}

	@Override
	public int hashCode() {
		return Objects.hash(discountCost, items, orderId, totalCost);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(discountCost, other.discountCost) && Objects.equals(items, other.items)
				&& orderId == other.orderId && Objects.equals(totalCost, other.totalCost);
	}
}

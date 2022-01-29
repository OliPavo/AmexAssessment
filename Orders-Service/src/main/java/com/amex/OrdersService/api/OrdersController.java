package com.amex.OrdersService.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amex.OrdersService.core.ServiceLayer;
import com.amex.OrdersService.exception.OrderNotFoundException;
import com.amex.OrdersService.infra.Order;
import com.amex.OrdersService.infra.Summary;

@RestController
@RefreshScope
public class OrdersController {

    @Autowired
    private ServiceLayer serviceLayer;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Summary submitOrder(@Valid @RequestBody Order order) {
        return serviceLayer.processOrder(order);
    }
    
    @GetMapping("/order/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order fetchOrder(@PathVariable int id){
        Order order = serviceLayer.getOrder(id);
        if(order == null)
            throw new OrderNotFoundException("No order found with id " + id);
        return order;
    }
    
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> fetchAllOrders(){
        List<Order> orders = serviceLayer.getAllOrders();
        if(orders.size() == 0)
            throw new OrderNotFoundException("No orders found.");
        return orders;
    }
}
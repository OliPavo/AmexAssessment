package com.amex.OrdersService.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amex.OrdersService.core.ServiceLayer;
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
}
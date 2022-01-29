package com.amex.OrdersService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateLineItemException extends RuntimeException {

    public DuplicateLineItemException(String exception) {
        super(exception);
    }
}
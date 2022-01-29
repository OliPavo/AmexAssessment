package com.amex.OrdersService.api;
import com.amex.OrdersService.exception.DuplicateLineItemException;
import com.amex.OrdersService.exception.ExceptionResponse;
import com.amex.OrdersService.exception.InvalidQuantityException;
import com.amex.OrdersService.exception.ItemNotFoundException;
import com.amex.OrdersService.exception.OrderNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleItemNotFoundException(ItemNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleOrderNotFoundException(OrderNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
        
     @ExceptionHandler(InvalidQuantityException.class)
     public final ResponseEntity<ExceptionResponse> handleInvalidQuantityException(InvalidQuantityException ex, WebRequest request) {
         ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                 request.getDescription(false));
         return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
     
     @ExceptionHandler(DuplicateLineItemException.class)
     public final ResponseEntity<ExceptionResponse> handleDuplicateLineItemException(DuplicateLineItemException ex, WebRequest request) {
         ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                 request.getDescription(false));
         return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
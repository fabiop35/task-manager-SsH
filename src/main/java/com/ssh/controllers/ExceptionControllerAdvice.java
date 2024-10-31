package com.ssh.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssh.exceptions.NotEnoughMoneyException;
import com.ssh.model.ErrorDetails;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDetails> exceptionNotEnoughMoneyHandler(NotEnoughMoneyException msg) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(">>Not enough money to make the payment: "+msg);
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }

}

package com.ssh.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.exceptions.NotEnoughMoneyException;
import com.ssh.model.ErrorDetails;
import com.ssh.model.PaymentDetails;
import com.ssh.services.PaymentService;


@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final Logger log = Logger.getLogger(PaymentController.class.getName());

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("payment")
    public ResponseEntity<?> payment() {
        //try {
        PaymentDetails paymentDetails = paymentService.processPayment();
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(paymentDetails);

        /*} catch (NotEnoughMoneyException e) {
            ErrorDetails errorDetails = new ErrorDetails();
            errorDetails.setMessage("Not enough money to make the payment: " + e.getMessage());
            return ResponseEntity
                    .badRequest()
                    .body(errorDetails);
        }*/
    }

    @PostMapping("makePayment")
    public ResponseEntity<PaymentDetails> makePayment(@RequestBody PaymentDetails paymentDetails) {
        log.log(Level.INFO, "Received payment: {0}", paymentDetails.getAmount());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(paymentDetails);
    }

}

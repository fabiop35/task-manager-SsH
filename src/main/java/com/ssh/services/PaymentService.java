package com.ssh.services;

import org.springframework.stereotype.Service;

import com.ssh.exceptions.NotEnoughMoneyException;
import com.ssh.model.PaymentDetails;

@Service
public class PaymentService {

    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }

}

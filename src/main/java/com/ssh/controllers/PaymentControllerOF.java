package com.ssh.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssh.model.Payment;
import com.ssh.proxies.PaymentsProxy;

@RestController
public class PaymentControllerOF {

    private final PaymentsProxy paymentsProxy;

    public PaymentControllerOF(PaymentsProxy paymentsProxy) {
        this.paymentsProxy = paymentsProxy;
    }

    @PostMapping("/paymentOF")
    public Payment createPayment(@RequestBody Payment payment) {

        String requestId = UUID.randomUUID().toString();
        return paymentsProxy.createPayment(requestId, payment);
    }
}

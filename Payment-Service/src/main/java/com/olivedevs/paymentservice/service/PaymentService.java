package com.olivedevs.paymentservice.service;

import com.olivedevs.paymentservice.dtos.OrderCreatedEvent;

public interface PaymentService {

    public void processPayment(OrderCreatedEvent paymentRequest);
}

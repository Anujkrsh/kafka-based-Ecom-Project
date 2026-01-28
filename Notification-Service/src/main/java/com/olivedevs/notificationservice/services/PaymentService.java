package com.olivedevs.notificationservice.services;

import com.olivedevs.notificationservice.dtos.PaymentEvent;

public interface PaymentService {
    void paymentSuccessService(PaymentEvent paymentEvent);
    void paymentFailedService(PaymentEvent paymentEvent);
}

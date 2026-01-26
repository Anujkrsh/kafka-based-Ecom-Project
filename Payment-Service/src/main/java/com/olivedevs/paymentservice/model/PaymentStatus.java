package com.olivedevs.paymentservice.model;

import lombok.Data;
import lombok.ToString;

@ToString
public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED,
    REFUNDED
}

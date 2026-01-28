package com.olivedevs.notificationservice.dtos;

import lombok.ToString;

@ToString
public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED,
    REFUNDED
}

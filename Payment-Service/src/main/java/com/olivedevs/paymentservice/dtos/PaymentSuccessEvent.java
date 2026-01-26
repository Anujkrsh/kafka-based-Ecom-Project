package com.olivedevs.paymentservice.dtos;


import com.olivedevs.paymentservice.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentSuccessEvent {

    private String orderId;
    private String customerId;
    private BigDecimal totalAmount;
    private PaymentStatus status;
    private String paymentId;
}

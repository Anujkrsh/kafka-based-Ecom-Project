package com.olivedevs.paymentservice.dtos;


import com.olivedevs.paymentservice.model.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaymentEvent {

    private String orderId;
    private String customerId;
    private BigDecimal totalAmount;
    private PaymentStatus status;
    private String paymentId;
}

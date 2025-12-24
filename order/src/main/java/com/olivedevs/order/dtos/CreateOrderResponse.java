package com.olivedevs.order.dtos;

import com.olivedevs.order.models.OrderStatus;
import lombok.*;

import java.math.BigDecimal;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private String orderId;
    private OrderStatus status;
    private BigDecimal totalAmount;
}

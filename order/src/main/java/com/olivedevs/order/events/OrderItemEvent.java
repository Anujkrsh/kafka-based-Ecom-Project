package com.olivedevs.order.events;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemEvent {
    private String productId;
    private Integer quantity;
    private BigDecimal price;
}

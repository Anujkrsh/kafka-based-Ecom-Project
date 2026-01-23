package com.olivedevs.order.dtos;

import com.olivedevs.order.events.OrderItemEvent;
import com.olivedevs.order.models.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
    private String orderId;
    private String customerId;
    private BigDecimal totalAmount;
    private OrderStatus status;;
    private Instant createdAt;
    private List<OrderItemEvent> items;
}

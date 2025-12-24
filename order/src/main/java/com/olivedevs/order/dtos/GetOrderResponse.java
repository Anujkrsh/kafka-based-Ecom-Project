package com.olivedevs.order.dtos;

import com.olivedevs.order.models.OrderItem;
import com.olivedevs.order.models.OrderStatus;
import lombok.*;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderResponse {

    @NonNull
    String customerId;
    List<OrderItem> products;
    private String orderId;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private Instant createdAt;
}

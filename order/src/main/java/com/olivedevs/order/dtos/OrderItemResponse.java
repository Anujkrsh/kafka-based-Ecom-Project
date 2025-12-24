package com.olivedevs.order.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
        private UUID id;
        private String productId;
        private Integer quantity;
        private BigDecimal price;
}

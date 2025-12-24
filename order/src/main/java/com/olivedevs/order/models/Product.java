package com.olivedevs.order.models;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    String productId;
    Integer quantity;
    BigDecimal price;
}

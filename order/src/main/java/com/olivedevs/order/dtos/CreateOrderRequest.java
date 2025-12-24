package com.olivedevs.order.dtos;

import com.olivedevs.order.models.Product;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NonNull
    String customerId;
    List<Product> products;

}

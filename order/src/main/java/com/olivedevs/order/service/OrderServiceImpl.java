package com.olivedevs.order.service;

import com.olivedevs.order.dtos.CreateOrderRequest;
import com.olivedevs.order.dtos.CreateOrderResponse;
import com.olivedevs.order.dtos.GetOrderResponse;
import com.olivedevs.order.models.Order;
import com.olivedevs.order.models.OrderItem;
import com.olivedevs.order.models.OrderStatus;
import com.olivedevs.order.models.Product;
import com.olivedevs.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request) {

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(Instant.now());

        BigDecimal total = BigDecimal.ZERO;

        for (Product itemRequest : request.getProducts()) {

            OrderItem item = new OrderItem();
            item.setProductId(itemRequest.getProductId());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(itemRequest.getPrice());
            item.setOrder(order);

            order.getItems().add(item);

            BigDecimal itemTotal =
                    itemRequest.getPrice()
                            .multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            total = total.add(itemTotal);
        }

        order.setTotalAmount(total);

         orderRepository.save(order);
         return new CreateOrderResponse(order.getOrderId(), order.getStatus(),total);
    }

    @Override
    public GetOrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        return new GetOrderResponse(order.getCustomerId(), order.getItems(), order.getOrderId(),order.getTotalAmount(),order.getStatus(),order.getCreatedAt());
    }
}



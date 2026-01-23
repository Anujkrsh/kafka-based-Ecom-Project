package com.olivedevs.order.service;

import com.olivedevs.order.dtos.*;
import com.olivedevs.order.events.OrderItemEvent;
import com.olivedevs.order.models.Order;
import com.olivedevs.order.models.OrderItem;
import com.olivedevs.order.models.OrderStatus;
import com.olivedevs.order.models.Product;
import com.olivedevs.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final KafkaProducerService kafkaProducerService;

    public OrderServiceImpl(OrderRepository orderRepository,KafkaProducerService kafkaProducerService) {
        this.orderRepository = orderRepository;
        this.kafkaProducerService = kafkaProducerService;
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
        OrderCreatedEvent event = OrderCreatedEvent.builder().orderId(order.getOrderId())
                .customerId(order.getCustomerId()).totalAmount(order.getTotalAmount())
                .status(order.getStatus()).createdAt(order.getCreatedAt())
                .items(order.getItems().stream()
                        .map(item -> new OrderItemEvent(
                                item.getProductId(),
                                item.getQuantity(),
                                item.getPrice()))
                        .toList())
                .build();

        kafkaProducerService.publishOrderCreatedEvent(event);
         return new CreateOrderResponse(order.getOrderId(), order.getStatus(),total);
    }

    @Override
    public GetOrderResponse getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderItemResponse> orderItems = order.getItems().
                stream()
                .map(item-> new OrderItemResponse(item.getId(),item.getProductId(), item.getQuantity(), item.getPrice()))
                .toList();
        return new GetOrderResponse(order.getCustomerId(),orderItems, order.getOrderId(),order.getTotalAmount(),order.getStatus(),order.getCreatedAt());
    }
}



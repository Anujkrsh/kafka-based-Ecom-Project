package com.olivedevs.order.controller;

import com.olivedevs.order.dtos.CreateOrderRequest;
import com.olivedevs.order.dtos.CreateOrderResponse;
import com.olivedevs.order.dtos.GetOrderResponse;
import com.olivedevs.order.dtos.OrderCreatedEvent;
import com.olivedevs.order.service.KafkaProducerService;
import com.olivedevs.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class Controller {

 private final OrderService orderService;
 private final KafkaProducerService kafkaProducerService;

    public Controller(OrderService orderService, KafkaProducerService kafkaProducerService) {
        this.orderService = orderService;
        this.kafkaProducerService = kafkaProducerService;
    }

 @PostMapping("/orders")
 public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
    return ResponseEntity.ok(orderService.createOrder(request));
   }


    @GetMapping("/orders/{id}")
    public ResponseEntity<GetOrderResponse> getOrderResponseResponseEntity(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/test-kafka")
    public String testKafka() {
        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId("test-123")
                .customerId("customer-1")
                .totalAmount(BigDecimal.valueOf(100.0))
                .build();

        kafkaProducerService.publishOrderCreatedEvent(event);
        return "Message sent to Kafka!";
    }
}

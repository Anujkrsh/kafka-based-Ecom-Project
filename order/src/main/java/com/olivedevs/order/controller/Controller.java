package com.olivedevs.order.controller;

import com.olivedevs.order.dtos.CreateOrderRequest;
import com.olivedevs.order.dtos.CreateOrderResponse;
import com.olivedevs.order.dtos.GetOrderResponse;
import com.olivedevs.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

 private final OrderService orderService;

    public Controller(OrderService orderService) {
        this.orderService = orderService;
    }

 @PostMapping("/orders")
 public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
    return ResponseEntity.ok(orderService.createOrder(request));
   }


    @GetMapping("/orders/{id}")
    public ResponseEntity<GetOrderResponse> getOrderResponseResponseEntity(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}

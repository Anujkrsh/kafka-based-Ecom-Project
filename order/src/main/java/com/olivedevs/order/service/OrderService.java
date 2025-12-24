package com.olivedevs.order.service;

import com.olivedevs.order.dtos.CreateOrderRequest;
import com.olivedevs.order.dtos.CreateOrderResponse;
import com.olivedevs.order.dtos.GetOrderResponse;

public interface OrderService {

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    public GetOrderResponse getOrderById(String orderId);
}

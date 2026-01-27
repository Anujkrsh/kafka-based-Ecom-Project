package com.olivedevs.paymentservice.event;

import com.olivedevs.paymentservice.dtos.OrderCreatedEvent;
import com.olivedevs.paymentservice.model.PaymentStatus;
import com.olivedevs.paymentservice.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "order-created" , groupId = "payment-group")
    public void consumeOrderCreatedEvent(@NonNull OrderCreatedEvent event){
        log.info("Received OrderCreatedEvent: {}", event.getOrderId());
        paymentService.processPayment(event);
    }
}

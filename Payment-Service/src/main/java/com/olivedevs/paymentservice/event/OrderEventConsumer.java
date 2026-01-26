package com.olivedevs.paymentservice.event;

import com.olivedevs.paymentservice.dtos.OrderCreatedEvent;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    @KafkaListener(topics = "order-created" , groupId = "notification-group")
    public void consumeOrderCreatedEvent(@NonNull OrderCreatedEvent event){
        log.info("Received OrderCreatedEvent: {}", event.getOrderId());
    }
}

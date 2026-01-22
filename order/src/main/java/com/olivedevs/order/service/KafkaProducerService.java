package com.olivedevs.order.service;

import com.olivedevs.order.dtos.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Publishing OrderCreatedEvent for orderId: {}", event.getOrderId());

        kafkaTemplate.send("order-created", event.getOrderId(), event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Event published successfully for orderId: {}", event.getOrderId());
                    } else {
                        log.error("Failed to publish event for orderId: {}", event.getOrderId(), ex);
                    }
                });
    }
}

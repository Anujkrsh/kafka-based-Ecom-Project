package com.olivedevs.notificationservice.configs;

import com.olivedevs.notificationservice.dtos.OrderCreatedEvent;
import com.olivedevs.notificationservice.services.NotificationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "order-created", groupId = "notification-group")
    public void consumeOrderCreatedEvent(@NonNull OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent: {}", event.getOrderId());
        notificationService.sendCreateOrderNotification(event);
    }
}


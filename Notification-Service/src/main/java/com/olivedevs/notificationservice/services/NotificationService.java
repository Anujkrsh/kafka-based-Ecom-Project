package com.olivedevs.notificationservice.services;

import com.olivedevs.notificationservice.dtos.OrderCreatedEvent;
import com.olivedevs.notificationservice.model.Notifications;
import com.olivedevs.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendCreateOrderNotification(OrderCreatedEvent event){
        log.info("Sending notification for the order: {}", event.getOrderId());
        String message= String.format
                ("Hello Customer %s, your order %s has been placed successfully"
                        ,event.getCustomerId(),event.getOrderId());

        Notifications notification= Notifications.builder().
                orderId(event.getOrderId())
                .customerId(event.getCustomerId())
                .message(message).
                type("Email").status(event.getStatus())
                .sentAt(Instant.now()).build();
        log.info("Notification saved with id: {}", notificationRepository.save(notification).getId());

    }

}

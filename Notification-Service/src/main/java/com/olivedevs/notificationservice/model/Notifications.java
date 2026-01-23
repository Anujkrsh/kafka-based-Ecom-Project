package com.olivedevs.notificationservice.model;


import com.olivedevs.notificationservice.dtos.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@Document(collection = "notifications")
public class Notifications {
    @Id
    private String id;
    private String orderId;
    private String customerId;
    private String message;
    private String type; // EMAIL, SMS, PUSH
    private OrderStatus status; // SENT, FAILED
    private Instant sentAt;
}

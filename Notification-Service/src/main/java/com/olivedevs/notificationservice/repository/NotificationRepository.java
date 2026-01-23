package com.olivedevs.notificationservice.repository;

import com.olivedevs.notificationservice.model.Notifications;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notifications,String> {
    List<Notifications> findByCustomerId(String customerId);
}

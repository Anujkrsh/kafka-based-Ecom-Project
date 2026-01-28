package com.olivedevs.notificationservice.services;

import com.olivedevs.notificationservice.dtos.OrderStatus;
import com.olivedevs.notificationservice.dtos.PaymentEvent;
import com.olivedevs.notificationservice.model.Notifications;
import com.olivedevs.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final NotificationRepository repository;

    @Override
    public void paymentSuccessService(PaymentEvent paymentEvent) {
        log.info("Payment success event received: {}", paymentEvent.getPaymentId());
        List<Notifications> notificationsList = repository.findByCustomerId(paymentEvent.getCustomerId());
         notificationsList.forEach(
                notifications-> {
                    notifications.builder()
                            .status(OrderStatus.CONFIRMED)
                            .message("Your order with id "+paymentEvent.getOrderId()+" has been confirmed")
                            .build();
                    repository.save(notifications);
                });

         log.info("Payment success event processed for payment id: {}", paymentEvent.getPaymentId());
    }

    @Override
    public void paymentFailedService(PaymentEvent paymentEvent) {
        log.info("Payment failed event received: {}", paymentEvent.getPaymentId());
        List<Notifications> notificationsList = repository.findByCustomerId(paymentEvent.getCustomerId());
        notificationsList.forEach(
                notifications-> {
                    notifications.builder()
                            .status(OrderStatus.CANCELLED)
                            .message("Your order with id "+paymentEvent.getOrderId()+" has been Cancelled Due to Payment Issue")
                            .build();
                    repository.save(notifications);
                });

        log.info("Payment failed event processed for payment id: {}", paymentEvent.getPaymentId());
    }


}

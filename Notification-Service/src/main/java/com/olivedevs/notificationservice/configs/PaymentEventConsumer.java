package com.olivedevs.notificationservice.configs;

import com.olivedevs.notificationservice.dtos.PaymentEvent;
import com.olivedevs.notificationservice.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentEventConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "payment-success", groupId = "payment-group")
    public void consumePaymentSuccessEvent(PaymentEvent event){
        log.info("Received PaymentSuccessEvent {}",event.getPaymentId());
        paymentService.paymentSuccessService(event);
    }

    @KafkaListener(topics = "payment-failed", groupId = "payment-group")
    public void consumePaymentFailedEvent(PaymentEvent event){
        log.info("Received Payment failed Event {}",event.getPaymentId());
        paymentService.paymentFailedService(event);
    }
}

package com.olivedevs.paymentservice.service;

import com.olivedevs.paymentservice.dtos.OrderCreatedEvent;
import com.olivedevs.paymentservice.dtos.PaymentEvent;
import com.olivedevs.paymentservice.model.Payment;
import com.olivedevs.paymentservice.model.PaymentStatus;
import com.olivedevs.paymentservice.repo.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private final Random random = new Random();
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void processPayment(OrderCreatedEvent request){
        log.info("Payment Request Received");
        Payment payment = Payment.builder().orderId(request.getOrderId())
              .customerId(request.getCustomerId()).amount(request.getTotalAmount())
              .status(PaymentStatus.PENDING).paymentMethod("Credit_Card").transactionId("")
              .build();

        paymentRepository.save(payment);
        log.info("Payment record created: {}", payment.getId());

        boolean paymentSuccess = random.nextInt(100) < 80;

        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (paymentSuccess) {
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setTransactionId("txn_" + UUID.randomUUID().toString().substring(0, 8));
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setFailureReason("Insufficient funds");
        }

        paymentRepository.save(payment);

        PaymentEvent paymentEvent = PaymentEvent.builder()
                .orderId(request.getOrderId())
                .paymentId(payment.getId())
                .customerId(request.getCustomerId())
                .totalAmount(request.getTotalAmount())
                .status(paymentSuccess ? PaymentStatus.SUCCESS  : PaymentStatus.FAILED)
                .build();


        String topic = payment.getStatus() == PaymentStatus.SUCCESS ? "payment-success" : "payment-failed";
        kafkaTemplate.send(topic, request.getOrderId(), paymentEvent);

        log.info("Payment {} for order: {} - Published to {}",
                paymentSuccess ? "succeeded" : "failed",
                request.getOrderId(),
                topic);
    }
}

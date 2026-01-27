package com.olivedevs.paymentservice.event;

import com.olivedevs.paymentservice.dtos.PaymentSuccessEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@AllArgsConstructor
public class PaymentEventProducer {

    private KafkaTemplate<String, PaymentSuccessEvent> kafkaTemplate;

    public void sendPaymentEvent(PaymentSuccessEvent event){
        log.info("Sending payment event: {}", event);
        kafkaTemplate.send("payment-success", event.getPaymentId() ,event);
    }

}

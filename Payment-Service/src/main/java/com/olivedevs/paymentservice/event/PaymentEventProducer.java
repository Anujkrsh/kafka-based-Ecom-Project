package com.olivedevs.paymentservice.event;

import com.olivedevs.paymentservice.dtos.PaymentEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@AllArgsConstructor
public class PaymentEventProducer {

    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentEvent(PaymentEvent event){
        log.info("Sending payment event: {}", event);
        kafkaTemplate.send("payment-success", event.getPaymentId() ,event);
    }

}

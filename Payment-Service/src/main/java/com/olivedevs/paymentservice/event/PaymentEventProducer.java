package com.olivedevs.paymentservice.event;

import com.olivedevs.paymentservice.dtos.PaymentEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PaymentEventProducer {

    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentEvent(String topic,String id,PaymentEvent event){
        log.info("Sending payment event: {}", event);
        kafkaTemplate.send(topic, id ,event);
    }

}

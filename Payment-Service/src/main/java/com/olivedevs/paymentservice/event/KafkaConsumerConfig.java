package com.olivedevs.paymentservice.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
@EnableKafka
@Slf4j
public class KafkaConsumerConfig {

    public ConsumerFactory<String,OrderEventConsumer> consumerFactory(){
        return null;

    }

}

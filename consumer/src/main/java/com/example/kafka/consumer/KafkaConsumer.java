package com.example.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "wikiTopic", groupId = "myGroup")
    public void consumeMsg(String msg) {
        log.info(String.format("consume msg from wikiTopic: %s", msg));
    }
}
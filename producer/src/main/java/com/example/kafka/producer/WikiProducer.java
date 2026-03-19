package com.example.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WikiProducer {
    private static final Logger log = LoggerFactory.getLogger(WikiProducer.class);
    private final KafkaTemplate<String,String> kafkaTemplate;

    public WikiProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg){
        log.info(String.format("Send message: %s",msg));
        kafkaTemplate.send("wikiTopic",msg);
    }

}

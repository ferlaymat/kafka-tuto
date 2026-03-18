package com.example.kakfa.consumer;

import com.example.kakfa.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    private final String TOPIC_NAME = "topicObj";

    
    @KafkaListener(topics = TOPIC_NAME, groupId = "kafkaGroupId")
    public void consumeEvent(ObjEvent event, Acknowledgment ack){
        System.out.println("##################################");
        System.out.println(event);
    }
}

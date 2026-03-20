package com.example.kafka.consumer;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    /**
     * We use Kafkalistener here to subscribe to a specific topic.
     * We voluntarily do not set the acknowledgment to provoke an exception
     */
    
    @KafkaListener(topics = TopicConstant.TOPIC_NAME, groupId = "kafkaGroupId")
    public void consumeEvent(ObjEvent event, Acknowledgment ack){
        System.out.println("##################################");
        System.out.println("Throw exception");

    }
}

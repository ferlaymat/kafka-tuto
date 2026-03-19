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
     */
    
    @KafkaListener(topics = TopicConstant.TOPIC_NAME, groupId = "kafkaGroupId")
    public void consumeEvent(ObjEvent event, Acknowledgment ack){
        System.out.println("##################################");

        //event is treated before the acknowledgment -> case at-least-once
        System.out.println(event);
        //manual acknowledgment
        ack.acknowledge();

        //event is treated after the acknowledgment -> case at-most-once
        //in this case, if it fails, the message is definitively lost
        //System.out.println(event);
    }
}

package com.example.kakfa.consumer;

import com.example.kakfa.common.TopicConstant;
import com.example.kakfa.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

    /**
     * We use Kafkalistener here to subscribe a specific topic.
     */
    
    @KafkaListener(topics = TopicConstant.TOPIC_NAME, groupId = "kafkaGroupId")
    public void consumeEvent(ObjEvent event, Acknowledgment ack){

        //event is treat before the acknowledgment -> case at-least-once
        System.out.println(event);
        //manual acknowledge
        ack.acknowledge();

        //event is treat after the acknowledgment -> case at-most-once
        //in this case, if it fails, the message is definitively lost
        //System.out.println(event);
    }
}

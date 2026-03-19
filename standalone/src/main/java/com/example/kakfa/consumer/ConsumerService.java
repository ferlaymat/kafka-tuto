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
     * A manual acknowledge is done here because global autocommit is disabled due to KStream.
     */
    
    @KafkaListener(topics = TopicConstant.TOPIC_NAME, groupId = "kafkaGroupId")
    public void consumeEvent(ObjEvent event, Acknowledgment ack){
        System.out.println("##################################");
        System.out.println(event);
        //manual acknowledge
        ack.acknowledge();
    }
}

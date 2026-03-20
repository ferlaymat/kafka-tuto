package com.example.kafka.producer;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProducerService {
    private KafkaTemplate<String, ObjEvent> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, ObjEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void senObjMessage() {
        UUID id = UUID.randomUUID();
        ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
        kafkaTemplate.send(TopicConstant.TOPIC_NAME, event);

    }

    public void senObjMessageMulti() {
        for(int i = 0; i< 10; i++) {
            UUID id = UUID.randomUUID();
            ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
            kafkaTemplate.send(TopicConstant.TOPIC_NAME, event);
            kafkaTemplate.send(TopicConstant.TOPIC_NAME_RESET, event);
        }
    }


}

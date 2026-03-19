package com.example.kafka.producer;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@Slf4j
public class ProducerService {
    private KafkaTemplate<String, ObjEvent> kafkaTemplate;
    private final KStream<String, ObjEvent> kStream;

    public ProducerService(KafkaTemplate<String, ObjEvent> kafkaTemplate,KStream<String, ObjEvent> kStream) {
        this.kafkaTemplate = kafkaTemplate;
        this.kStream= kStream;
    }

    public void senObjMessage() {
        UUID id = UUID.randomUUID();
        ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
        //as we have multiple partitions, the order between them is not ensure. Only ensured inside a topic.
        //we add a key to ensure that all messages will be sent to the same partition.
        kafkaTemplate.send(TopicConstant.TOPIC_NAME, "key-user", event);
    }

    public void senObjMessageForStream() {
        for(int i =0; i <100; i++) {
            UUID id = UUID.randomUUID();
            ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
            kafkaTemplate.send(TopicConstant.TOPIC_NAME_STREAM, event);
        }
    }


}

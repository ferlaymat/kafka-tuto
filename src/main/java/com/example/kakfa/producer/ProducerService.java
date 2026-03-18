package com.example.kakfa.producer;

import com.example.kakfa.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.Properties;
import java.util.UUID;

@Service
@Slf4j
public class ProducerService {
    private KafkaTemplate<String, ObjEvent> kafkaTemplate;
    private final String TOPIC_NAME = "topicObj";
    private final String TOPIC_NAME_STREAM = "topicObjStream";
    private final KStream<String, ObjEvent> kStream;

    public ProducerService(KafkaTemplate<String, ObjEvent> kafkaTemplate,KStream<String, ObjEvent> kStream) {
        this.kafkaTemplate = kafkaTemplate;
        this.kStream= kStream;
    }

    public void senObjMessage() {
        UUID id = UUID.randomUUID();
        ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
        kafkaTemplate.send(TOPIC_NAME, event);
    }

    public void senObjMessageForStream() {
        for(int i =0; i <100; i++) {
            UUID id = UUID.randomUUID();
            ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
            kafkaTemplate.send(TOPIC_NAME_STREAM, event);
        }
    }


}

package com.example.kafka.consumer;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeadLetterService {

    @KafkaListener(topics = TopicConstant.DL_TOPIC_NAME, groupId = "kafkaGroupId")
    public void consumeDLT(
            ConsumerRecord<String, ObjEvent> record,
            @Header(KafkaHeaders.DLT_EXCEPTION_MESSAGE) String exceptionMessage,
            @Header(KafkaHeaders.DLT_ORIGINAL_TOPIC) String originalTopic,
            @Header(KafkaHeaders.DLT_ORIGINAL_OFFSET) long originalOffset) {

        log.error("DLT — topic={} offset={} error={}",
                originalTopic, originalOffset, exceptionMessage);

        // alerts, archiving, dashboard...
        System.out.println(String.format("Value:%s , ex:%s",record.value(), exceptionMessage));
    }
}

package com.example.kafka.producer;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ProducerService {
    private KafkaTemplate<String, ObjEvent> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, ObjEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendObjMessage()  {

        for(int i= 0; i<100; i++) {
            UUID id = UUID.randomUUID();
            ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
            kafkaTemplate.send(TopicConstant.TOPIC_NAME, event);
            System.out.println(String.format("send → %s",event));
        }

        System.out.println("#################################");
        System.out.println("######### without flush #########");
        System.out.println("#################################");

    }

    public void sendObjMessageWithFlush()  {

        for(int i= 0; i<100; i++) {
            UUID id = UUID.randomUUID();
            ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
            CompletableFuture<SendResult<String, ObjEvent>> future = kafkaTemplate.send(TopicConstant.TOPIC_NAME, event);
            // Excute action of the future when is complete
            future.whenComplete((result, ex) -> {
                if (ex != null) {
                    System.err.println("sending msg failed : " + ex.getMessage());
                } else {
                    RecordMetadata metadata = result.getRecordMetadata();
                    System.out.printf("send → partition=%d offset=%d%n",
                            metadata.partition(), metadata.offset());
                }
            });
        }

        System.out.println("#################################");
        System.out.println("######### before flush  #########");
        System.out.println("#################################");
        kafkaTemplate.flush(); //blocs all messages until all messages have been sent
        System.out.println("#################################");
        System.out.println("######### after flush  #########");
        System.out.println("#################################");
    }


}

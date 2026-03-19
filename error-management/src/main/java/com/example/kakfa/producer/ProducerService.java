package com.example.kakfa.producer;

import com.example.kakfa.common.TopicConstant;
import com.example.kakfa.event.ObjEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.RecordTooLargeException;
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

    /**
     * In the following method, we manage 2 error cases.
     * First:  it is a business or technical error, and we manage it as needed
     * Second: the number of possible retries has already been reached
     */
    public void senObjMessage() {
        UUID id = UUID.randomUUID();
        ObjEvent event = new ObjEvent(id, String.format("message: %s", id));
        kafkaTemplate.send(TopicConstant.TOPIC_NAME, event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        // succes — message was sent to the broker
                        log.info("Send offset={}", result.getRecordMetadata().offset());
                    } else {
                        // handle specific error
                        if (ex.getCause() instanceof RecordTooLargeException) {
                            // non-retriable — in this case, message is too big.
                            log.error("Message too big, ignore it : {}", event.getId());
                        } else {
                            // too many attempts. cannot retry anymore
                            log.error("Permanentely failed : {}", ex.getMessage());
                        }
                    }
                });
    }


}

package com.example.kafka.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@KafkaListener(topics = TopicConstant.TOPIC_NAME, groupId = "mon-groupe")
public class ObjEventConsumer implements ConsumerSeekAware {

    private ConsumerSeekCallback seekCallback;

    // appelé une seule fois après l'assignation des partitions
    @Override
    public void onPartitionsAssigned(
            Map<TopicPartition, Long> assignments,
            ConsumerSeekCallback callback) {
        this.seekCallback = callback;  // garder la référence pour seek ultérieur
    }

    @KafkaHandler
    public void consume(ObjEvent event) {
        System.out.println(event);
    }

    // --- méthodes de seek appelables depuis l'extérieur ---

    // rejouer depuis le début
    public void replayFromBeginning() {
        seekCallback.seekToBeginning(seekCallback.getAssignedPartitions());
    }

    // sauter tous les messages en attente
    public void skipToEnd() {
        seekCallback.seekToEnd(seekCallback.getAssignedPartitions());
    }

    // aller à un offset précis sur une partition précise
    public void seekToOffset(String topic, int partition, long offset) {
        seekCallback.seek(topic, partition, offset);
    }
}
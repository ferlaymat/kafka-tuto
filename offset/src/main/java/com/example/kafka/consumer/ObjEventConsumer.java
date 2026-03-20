package com.example.kafka.consumer;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
@KafkaListener(topics = TopicConstant.TOPIC_NAME_RESET, groupId = "kafkaGroupId")
public class ObjEventConsumer implements ConsumerSeekAware {

    private ConsumerSeekCallback seekCallback;
    private Set<TopicPartition> assignedPartitions = new HashSet<>();
    private volatile boolean seekToBeginningRequested = false; //volatile to be visible between threads

    @Override
    public void onPartitionsAssigned(
            Map<TopicPartition, Long> assignments,
            ConsumerSeekCallback callback) {
        this.seekCallback = callback;
        this.assignedPartitions = assignments.keySet(); // keep partitions in memory
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        assignedPartitions.removeAll(partitions); // clean all redistributions
    }

    @KafkaHandler
    public void consume(ObjEvent event, Acknowledgment ack) {
        //check if we need to replay
        if (seekToBeginningRequested) {
            replayFromBeginning();
            seekToBeginningRequested = false;
        }
        //consume the last sent event
        System.out.println(String.format("Replay Consume event:%s", event));
        ack.acknowledge();
    }


    //set the flag
    public void requestSeekToBeginning() {
        seekToBeginningRequested = true;
    }

    //replay all topics for each partitions
    public void replayFromBeginning() {
        assignedPartitions.forEach(tp ->
                seekCallback.seekToBeginning(tp.topic(), tp.partition())
        );
    }


}
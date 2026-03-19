package com.example.kakfa.configuration;

import com.example.kakfa.common.TopicConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    /**
     * We use TopicBuilder here to configure all topics separately.
     * They will be injected by their name.
     */


    /**
     * A topic is a logical for which one the physical representation is a partition.
     * It exists a relation between consumer and partition. Inside the same group,
     * the partition can be consumer at most by one consumer. that means, if you want
     * to increase the performance, you can parallelize the consumption by adding more
     * partitions and consumers.
     * Replicas correspond to the redundancy of your partition on other brokers to insure
     * the continuity in case of the main broker was not available.
     */
    @Bean
    public NewTopic objTopic(){
        return TopicBuilder.name(TopicConstant.TOPIC_NAME)
                .partitions(2)
               // .replicas(3)
                .build();
    }

    @Bean
    public NewTopic objTopicStream(){
        return TopicBuilder.name(TopicConstant.TOPIC_NAME_STREAM)
                .build();
    }
}

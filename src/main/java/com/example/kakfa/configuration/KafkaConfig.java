package com.example.kakfa.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic objTopic(){
        return TopicBuilder.name("topicObj")
                .partitions(2)
                .build();
    }

    @Bean
    public NewTopic objTopicStream(){
        return TopicBuilder.name("topicObjStream")
                .build();
    }
}

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


    @Bean
    public NewTopic objTopic() {
        return TopicBuilder.name(TopicConstant.TOPIC_NAME)
                //by default a topic is kept 7 days in Kafka. You can modify this
                //by specifying the delay of retention
                .config("retention.ms", "604800000" )
                .build();
    }


}

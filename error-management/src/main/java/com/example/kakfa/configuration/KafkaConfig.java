package com.example.kakfa.configuration;

import com.example.kakfa.common.TopicConstant;
import com.fasterxml.jackson.core.JsonParseException;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.ExponentialBackOffWithMaxRetries;
import org.springframework.kafka.support.serializer.DeserializationException;

@Configuration
public class KafkaConfig {
    /**
     * We use TopicBuilder here to configure all topics separately.
     * They will be injected by their name.
     */


    @Bean
    public NewTopic objTopic() {
        return TopicBuilder.name(TopicConstant.TOPIC_NAME)
                .build();
    }



    /**
     * Here, we declare a handler to manage errors from consumers.
     * Should not be present in producer's project configuration.
     * Firstly, we try 4 times to consume the message. If it is still failing,
     * we send it to a dead letter topic.
     * Secondly, for all non-retriable errors, we directly send them to a dead letter topic.
     */

    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<String, Object> kafkaTemplate) {

        // backoff exponentiel : 1s, 2s, 4s, 8s — max 4 attempts
        ExponentialBackOffWithMaxRetries backOff = new ExponentialBackOffWithMaxRetries(4);
        backOff.setInitialInterval(1_000);
        backOff.setMultiplier(2.0);
        backOff.setMaxInterval(10_000);

        // if still failing → we send to a Dead Letter Topic
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
                // naming convention : commandes → commandes.DLT
                (record, ex) -> new TopicPartition(record.topic() + ".DLT", record.partition())
        );

        DefaultErrorHandler handler = new DefaultErrorHandler(recoverer, backOff);

        //  non-retriable errors — directly send to DLT without retry
        handler.addNotRetryableExceptions(
                JsonParseException.class,          // type of handled exception
                DeserializationException.class     // other type of handled exception
        );

        return handler;
    }


    /**
     * Here, we connect the handler to kafkalisteners
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory,
            DefaultErrorHandler errorHandler) {

        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }
}

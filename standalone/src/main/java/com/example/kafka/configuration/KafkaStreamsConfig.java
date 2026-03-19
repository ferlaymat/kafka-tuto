package com.example.kafka.configuration;

import com.example.kafka.common.TopicConstant;
import com.example.kafka.event.ObjEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JacksonJsonSerde;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {
    /**
     * This bean automatically consumes the stream of events.
     * It is instantiated at application startup.
     * Here we specify which type of object the consumer must deserialize.
     */

    @Bean
    public KStream<String, ObjEvent> kStream(StreamsBuilder builder) {
        JacksonJsonSerde<ObjEvent> objEventSerde = new JacksonJsonSerde<>(ObjEvent.class);

        KStream<String, ObjEvent> stream = builder.stream(TopicConstant.TOPIC_NAME_STREAM,
                Consumed.with(Serdes.String(), objEventSerde));
        //'peek' let's you chain the stream. If you want to consume directly here, use 'foreach'
        stream.peek((s, objEvent) -> System.out.println("Event:"+objEvent))
        // if you want to forward the event to another topic, use the command 'to'
        //        .to("another-topic");
        ;
        return stream;
    }
}
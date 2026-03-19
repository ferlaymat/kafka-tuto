package com.example.kafka.producer.stream;

import com.example.kafka.producer.WikiProducer;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class WikiStreamConsumer {
    private static final Logger log = LoggerFactory.getLogger(WikiStreamConsumer.class);
    private final WebClient webClient;
    private final WikiProducer wikiProducer;

    public WikiStreamConsumer(WebClient.Builder webClientBuilder, WikiProducer wikiProducer) {
        this.webClient = webClientBuilder.baseUrl("https://stream.wikimedia.org/v2").build();
        this.wikiProducer = wikiProducer;
    }


    public void consumeStreamAndPublish(){
        webClient.get().uri("/stream/recentchange").retrieve().bodyToFlux(String.class).subscribe(wikiProducer::sendMessage);
    }
}

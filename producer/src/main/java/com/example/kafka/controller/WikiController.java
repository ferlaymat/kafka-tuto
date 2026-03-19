package com.example.kafka.controller;

import com.example.kafka.producer.stream.WikiStreamConsumer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wiki")
public class WikiController {

    private final WikiStreamConsumer wikiStreamConsumer;

    public WikiController(WikiStreamConsumer wikiStreamConsumer) {
        this.wikiStreamConsumer = wikiStreamConsumer;
    }

    @GetMapping
    public void startPublish(){
        wikiStreamConsumer.consumeStreamAndPublish();
    }
}

package com.example.kafka.controller;

import com.example.kafka.producer.ProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
@Tag(name = "Producer API management")
public class ProducerController {

    private final ProducerService producerService;

    @Operation(summary = "Send a message by Kafka", description = "Produce and send a unique message")
    @GetMapping
    public void sendMessage() {
        producerService.senObjMessage();
    }

    @Operation(summary = "Send multiple messages by Kafka", description = "Produce and send messages on stream topic")
    @GetMapping("/stream")
    public void sendStreamMessage() {
        producerService.senObjMessageForStream();
    }


}

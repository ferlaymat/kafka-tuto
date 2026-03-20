package com.example.kafka.controller;

import com.example.kafka.consumer.ConsumerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consumer")
@RequiredArgsConstructor
@Tag(name = "Consumer API management")
public class ConsumerController {
    private final ConsumerService consumerService;

    @GetMapping
    @Operation(description = "Allow to send a message to force to ignore the offset. Topics will be re consumed at the next sent message")
    public void reset(){
        consumerService.reset();
    }
}

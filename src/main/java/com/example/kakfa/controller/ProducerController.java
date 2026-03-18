package com.example.kakfa.controller;

import com.example.kakfa.producer.ProducerService;
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

    @GetMapping
    public void sendMessage() {
        producerService.senObjMessage();
    }

    @GetMapping("/stream")
    public void sendStreamMessage() {
        producerService.senObjMessageForStream();
    }


}

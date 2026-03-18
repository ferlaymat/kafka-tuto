package com.example.kakfa.controller;

import com.example.kakfa.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ApiController {

    private final ProducerService producerService;

    @GetMapping
    public void sendMessage(){
        producerService.senObjMessage();
    }

    @GetMapping("/stream")
    public void sendStreamMessage(){
        producerService.senObjMessageForStream();
    }


}

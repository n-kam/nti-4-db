package ru.mpei.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpei.domain.Measurement;
import ru.mpei.producer.TopicProducer;


@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
@RestController
@Slf4j
public class KafkaController {

    private final TopicProducer topicProducer;

    @GetMapping(value = "/send")
    public void send(@RequestBody Measurement m) {
        log.info("Got measurement from rest: {}", m);
        topicProducer.send(m);
    }
}

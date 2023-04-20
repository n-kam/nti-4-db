package ru.mpei.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.mpei.domain.Measurement;
import ru.mpei.producer.TopicProducer;

import java.time.Instant;


@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
@RestController
@Slf4j
public class KafkaController {

    private final TopicProducer topicProducer;

    @GetMapping(value = "/send")
    public void send(@RequestBody Measurement m) {
        log.info("got measurement with rest: {}", m);
        topicProducer.send(m);
    }

    @GetMapping(value = "/getDtoDebug")
    public Measurement getDtoDebug() {
        return new Measurement(Instant.now(), "trans 1242", 234.44);
//        return new Measurement("trans 1242", 234.44);
    }
}

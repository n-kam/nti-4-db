package ru.mpei.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.mpei.domain.Measurement;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProducer {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, Measurement> kafkaTemplate;

    public void send(Measurement m) {
        log.info("sending meas to kafka {}: {}", topicName, m);
        kafkaTemplate.send(topicName, m);
    }
}

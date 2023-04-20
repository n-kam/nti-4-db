package ru.mpei.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.mpei.domain.Measurement;
import ru.mpei.repository.MeasurementRepo;

@Slf4j
@RequiredArgsConstructor
@Service
public class TopicListener {

    @Autowired
    MeasurementRepo repo;

    @Value("${topic.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, Measurement> payload) {
        log.info("Got measurement from Kafka {}: {}", topicName, payload.value());
        repo.save(payload.value());
    }

}

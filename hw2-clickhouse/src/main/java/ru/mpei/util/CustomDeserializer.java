package ru.mpei.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import ru.mpei.domain.Measurement;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class CustomDeserializer implements Deserializer<Measurement> {
    @Override
    public Measurement deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (data == null) {
            return null;
        }
        try {
            Measurement m = objectMapper.readValue(data, Measurement.class);
//            log.info("Deserializing {}", m);
            return m;
        } catch (IOException exception) {
            String message = new String(data, StandardCharsets.UTF_8);
            log.error("Unable to deserialize measurement: {}", message, exception);
            return null;
        }
    }
}

package ru.mpei.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import ru.mpei.domain.Measurement;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.TimeZone;

@Slf4j
public class CustomDeserializer implements Deserializer<Measurement> {
    @Override
    public Measurement deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule())
                .setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        if (data == null | Objects.requireNonNull(data).length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(data, Measurement.class);
        } catch (IOException exception) {
            String message = new String(data, StandardCharsets.UTF_8);
            log.error("Unable to deserialize measurement: {}", message, exception);
            return null;
        }
    }
}

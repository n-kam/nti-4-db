package ru.mpei.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;
import ru.mpei.domain.Measurement;

import java.util.TimeZone;

@Slf4j
public class CustomSerializer implements Serializer<Measurement> {
    @Override
    public byte[] serialize(String s, Measurement commandDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule())
                .setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        if (commandDto != null) {
            try {
                return objectMapper.writeValueAsBytes(commandDto);
            } catch (JsonProcessingException e) {
                log.error("Unable to serialize measurement cause : {}", e.getMessage(), e);
                return new byte[0];
            }
        }
        return new byte[0];
    }
}

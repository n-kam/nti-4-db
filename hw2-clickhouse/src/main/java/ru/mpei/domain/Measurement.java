package ru.mpei.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {
    private Instant timestamp;
    private String source;
    private Double value;
}

//public record Measurement(Instant timestamp, String source, Double value){}

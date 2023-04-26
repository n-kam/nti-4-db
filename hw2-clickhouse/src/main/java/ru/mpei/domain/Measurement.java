package ru.mpei.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Measurement {
    private OffsetDateTime timestamp;
    private String source;
    private Double value;
}
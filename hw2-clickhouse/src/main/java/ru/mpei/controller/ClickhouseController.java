package ru.mpei.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mpei.domain.Measurement;
import ru.mpei.repository.MeasurementRepo;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping(value = "/clickhouse")
@RestController
@Slf4j
public class ClickhouseController {

    private final MeasurementRepo repo;

    @GetMapping(value = "/getAll")
    public List<Measurement> getAll() {
        return repo.findAll();
    }
}

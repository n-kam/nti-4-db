package ru.mpei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.mpei.domain.Measurement;
import ru.mpei.repository.MeasurementRepo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
//        Measurement measurement = new Measurement();
//        Instant ts = Instant.now();

        ConfigurableApplicationContext context = SpringApplication.run(Main.class);

        MeasurementRepo repo = context.getBean(MeasurementRepo.class);
        repo.save(new Measurement(Instant.now(), "src", 228.0));
        System.out.println("All: " + repo.findAll());

//        String str = "World";
//        String fileName = ""
//        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
//        writer.append(' ');
//        writer.append(str);
//
//        writer.close();
//
//
//        String str = "Hello";
//
//        Path path = Paths.get();
//        byte[] strToBytes = str.getBytes();
//
//        Files.write(path, strToBytes);
//
//        String read = Files.readAllLines(path).get(0);
//        assertEquals(str, read);
//
//        for (int i = 0; i < 100; i++) {
//
//        }
//        measurement.setTimestamp();
    }
}
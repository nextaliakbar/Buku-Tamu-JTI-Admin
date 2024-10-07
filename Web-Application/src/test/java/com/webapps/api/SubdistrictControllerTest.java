package com.webapps.api;

import com.webapps.api.entity.Regencie;
import com.webapps.api.entity.Subdistrict;
import com.webapps.api.repository.SubdistrictRepository;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@AutoConfigureMockMvc
public class SubdistrictControllerTest {

    @Autowired
    private SubdistrictRepository subdistrictRepository;

    @Test
    @SneakyThrows
    void insert() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();

        CSVParser parser = new CSVParser(Files.newBufferedReader(Path.of("data/districts.csv")), format);

        parser.forEach(record -> {
            Regencie regencie = new Regencie();
            regencie.setId(record.get("regency_id"));
            Subdistrict subdistrict = new Subdistrict();
            subdistrict.setId(record.get("id"));
            subdistrict.setName(record.get("name"));
            subdistrict.setRegencie(regencie);
            subdistrictRepository.save(subdistrict);
        });
    }
}

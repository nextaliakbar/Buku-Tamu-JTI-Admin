package com.webapps.api;

import com.webapps.api.entity.Province;
import com.webapps.api.repository.ProvinceRepository;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
@AutoConfigureMockMvc
public class ProvinceControllerTest {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Test
    @SneakyThrows
    void insert() {
        Path path = Path.of("data/provinces.csv");
        BufferedReader reader = Files.newBufferedReader(path);

        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();

        CSVParser parser = new CSVParser(reader, format);
        parser.forEach(record -> {
            Province province = new Province();
            province.setId(record.get("id"));
            province.setName(record.get("name"));
            provinceRepository.save(province);
        });
    }
}

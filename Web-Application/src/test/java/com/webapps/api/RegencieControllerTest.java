package com.webapps.api;

import com.webapps.api.entity.Province;
import com.webapps.api.entity.Regencie;
import com.webapps.api.repository.RegencieRepository;
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
public class RegencieControllerTest {

    @Autowired
    private RegencieRepository regencieRepository;

    @Test
    @SneakyThrows
    void insert() {
        CSVFormat format = CSVFormat.DEFAULT.builder().setHeader().build();

        CSVParser parser = new CSVParser(Files.newBufferedReader(Path.of("data/regencies.csv")), format);

        parser.forEach(record -> {
            Province province = new Province();
            province.setId(record.get("province_id"));
            Regencie regencie = new Regencie();
            regencie.setId(record.get("id"));
            regencie.setName(record.get("name"));
            regencie.setProvince(province);
            regencieRepository.save(regencie);

        });
    }
}

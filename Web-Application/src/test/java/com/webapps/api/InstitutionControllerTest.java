package com.webapps.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapps.api.entity.Guest;
import com.webapps.api.model.CreateInstitutionRequest;
import com.webapps.api.model.InstitutionResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.repository.GuestRepository;
import com.webapps.api.repository.InstitutionRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class InstitutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @BeforeEach
    void setUp() {
        institutionRepository.deleteAll();
        guestRepository.deleteAll();

        Guest guest = new Guest();
        guest.setId("Guest ID Test");
        guest.setGuestType("Lokal");
        guest.setName("Ali Akbar Rafsanjani");
        guest.setGender("Laki-laki");
        guest.setPlaceOfBirth("Jember");
        guest.setDateOfBirth("2003-07-02");
        guest.setNoHp("08123456789");
        guest.setNoTelp("");
        guest.setEmail("");
        guest.setPosition("");
        guestRepository.save(guest);
    }

    @Test
    @SneakyThrows
    void testCreateInstitutionWithGuestNotFound() {
        var institutionRequest = new CreateInstitutionRequest();
        institutionRequest.setName("Politeknik Negeri Jember");
        institutionRequest.setNoTelp("4345 556 778");
        institutionRequest.setNoFax("0331 0228 77 5");

        mockMvc.perform(
        post("/api/guests/Not Found/institutions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(institutionRequest))
        ).andExpectAll(status().isNotFound())
                .andDo(result -> {
                    WebResponse<String> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
                    log.info("Error {}",response.getErrors());
                });
    }

    @Test
    @SneakyThrows
    void testCreateInstitutionBadRequest() {
        var institutionRequest = new CreateInstitutionRequest();
        institutionRequest.setName("");
        institutionRequest.setNoTelp("4345 556 778");
        institutionRequest.setNoFax("0331 0228 77 5");

        mockMvc.perform(
                post("/api/guests/Guest ID Test/institutions")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(institutionRequest))
        ).andExpectAll(status().isBadRequest())
        .andDo(result -> {
            WebResponse<String> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            log.info("Error {}",response.getErrors());
        });
    }

    @Test
    @SneakyThrows
    void testCreateInstitutionSuccess() {
        var institutionRequest = new CreateInstitutionRequest();
        institutionRequest.setName("Politeknik Negeri Jember");
        institutionRequest.setNoTelp("4345 556 778");
        institutionRequest.setNoFax("0331 0228 77 5");

        mockMvc.perform(
        post("/api/guests/Guest ID Test/institutions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(institutionRequest))
        ).andExpectAll(status().isOk())
        .andDo(result -> {
            WebResponse<InstitutionResponse> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            Assertions.assertNotNull(response.getData());
            log.info("Data : {}", response.getData());
        });
    }
}

package com.webapps.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapps.api.model.CreateGuestRequest;
import com.webapps.api.model.GuestResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.repository.*;
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
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuestRepository guestRepository;

    @BeforeEach
    void setUp() {
        guestRepository.deleteAll();
    }

    @Test
    @SneakyThrows
    void testCreateWithoutAdminBadRequest() {
        var guestRequest = new CreateGuestRequest();
        guestRequest.setGuestType("Lokal");
        guestRequest.setName("Ali Akbar Rafsanjani");
        guestRequest.setGender("Laki-laki");
        guestRequest.setPlaceOfBirth("Jember");
        guestRequest.setDateOfBirth("2003-07-02");
        guestRequest.setNoHp("08123456789");
        guestRequest.setNoTelp("");
        guestRequest.setEmail("aliakbar");
        guestRequest.setPosition("");

        mockMvc.perform(
                post("/api/guests")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(guestRequest))
        ).andExpectAll(status().isBadRequest())
        .andDo(result -> {
            WebResponse<String> response = mapper.readValue(result.getResponse()
                    .getContentAsString(), new TypeReference<>() {});
            log.info("Error {}",response.getErrors());
        });
    }

    @Test
    @SneakyThrows
    void testCreateWithoutAdminSucces() {
        var guestRequest = new CreateGuestRequest();
        guestRequest.setGuestType("Lokal");
        guestRequest.setName("Ali Akbar Rafsanjani");
        guestRequest.setGender("Laki-laki");
        guestRequest.setPlaceOfBirth("Jember");
        guestRequest.setDateOfBirth("2003-07-02");
        guestRequest.setNoHp("08123456789");
        guestRequest.setNoTelp("");
        guestRequest.setEmail("");
        guestRequest.setPosition("");

        mockMvc.perform(
                post("/api/guests")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(guestRequest))
        ).andExpectAll(status().isOk())
        .andDo(result -> {
            WebResponse<GuestResponse> response = mapper.readValue(result.getResponse()
                    .getContentAsString(), new TypeReference<>() {});
            Assertions.assertNotNull(response.getData());
            log.info("Data : {}", response.getData());
        });
    }
}

package com.webapps.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapps.api.entity.Guest;
import com.webapps.api.model.CreateNeedRequest;
import com.webapps.api.model.NeedResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.repository.GuestRepository;
import com.webapps.api.repository.NeedRepository;
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
import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class NeedControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private NeedRepository needRepository;

    @BeforeEach
    void setUp() {
        needRepository.deleteAll();
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
    void testCreateNeedWithGuestNotFound() {
        var needRequest = new CreateNeedRequest();
        needRequest.setTitle("Konsultasi");
        needRequest.setDescription("Konsultasi Tugas Akhir");
        needRequest.setCreatedAt(LocalDateTime.now());
        mockMvc.perform(
        post("/api/guests/Not Found/needs")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(needRequest))
        ).andExpect(status().isNotFound())
                .andDo(result -> {
                    WebResponse<String> response = mapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<>() {});
                    log.info("Error {}", response.getErrors());
                });
    }

    @Test
    @SneakyThrows
    void testCreateNeedBadRequest() {
    var needRequest = new CreateNeedRequest();
    needRequest.setTitle("");
    needRequest.setDescription("Konsultasi Tugas Akhir");
    needRequest.setCreatedAt(LocalDateTime.now());
    mockMvc.perform(
    post("/api/guests/Guest ID Test/needs")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(needRequest))
    ).andExpect(status().isBadRequest())
        .andDo(result -> {
            WebResponse<String> response = mapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<>() {});
            log.info("Error {}", response.getErrors());
        });
    }

    @Test
    @SneakyThrows
    void testCreateNeedSucces() {
        var needRequest = new CreateNeedRequest();
        needRequest.setTitle("Konsultasi");
        needRequest.setDescription("Konsultasi Tugas Akhir");
        needRequest.setCreatedAt(LocalDateTime.now());
        mockMvc.perform(
        post("/api/guests/Guest ID Test/needs")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(needRequest))
        ).andExpect(status().isOk())
            .andDo(result -> {
                WebResponse<NeedResponse> response = mapper.readValue(result.getResponse().getContentAsString(),
                        new TypeReference<>() {});
                Assertions.assertNotNull(response.getData());
                log.info("Data {}", response.getData());
            });
    }
}

package com.webapps.apps;
import com.webapps.api.entity.Guest;
import com.webapps.api.entity.Need;
import com.webapps.api.repository.GuestRepository;
import com.webapps.model.ModelGuest;
import jakarta.servlet.http.Cookie;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class GuestAppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuestRepository guestRepository;

    @Test
    @SneakyThrows
    void testGetAll() {
        mockMvc.perform(
                get("/tamu")
        ).andExpectAll(status().isOk(), view().name("tamu/index"))
                .andDo(result -> {
                    List<ModelGuest> guests = (List<ModelGuest>) result.getModelAndView().getModel().get("guests");
                    guests.forEach((guest) -> {
                        log.info("ID {}", guest.getId());
                        log.info("Guest Type {}", guest.getGuestType());
                        log.info("Name {}", guest.getName());
                        log.info("Gender {}", guest.getGender());
                        log.info("=======================");
                    });
                });
    }

    @Test
    @SneakyThrows
    void testInsert() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("guestType", "Lokal");
        params.set("name", "Radit Septiar Isra Maulana");
        params.set("gender", "Laki-laki");
        params.set("placeOfBirth", "Jember");
        params.set("dateOfBirth", "2024-10-08");
        params.set("noHp", "08123456789");
        mockMvc.perform(
                post("/tamu/tambah")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .cookie(new Cookie("username", "USER-001"))
                        .params(params)
        ).andExpectAll(status().isFound());
    }

    @Test
    @SneakyThrows
    void testEdit() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("guestType", "Lokal");
        params.set("name", "Radit Septiar Isra Maulana");
        params.set("gender", "Laki-laki");
        params.set("placeOfBirth", "Jember");
        params.set("dateOfBirth", "2024-10-08");
        params.set("noHp", "08123344556");
        mockMvc.perform(
                post("/tamu/edit")
                        .accept(MediaType.APPLICATION_FORM_URLENCODED)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .cookie(new Cookie("username", "USER-001"))
                        .queryParam("id", "G-2410-4563-001")
                        .params(params)
        ).andExpectAll(status().isFound());
    }

    @Test
    @SneakyThrows
    void testDelete() {
        mockMvc.perform(
                get("/tamu/hapus")
                        .cookie(new Cookie("username", "USER-001"))
                        .queryParam("id", "G-2410-4563-001")
        ).andExpectAll(status().isFound());
    }
}

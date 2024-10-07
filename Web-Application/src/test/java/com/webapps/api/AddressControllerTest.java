package com.webapps.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapps.api.entity.Guest;
import com.webapps.api.model.AddressResponse;
import com.webapps.api.model.CreateAddressRequest;
import com.webapps.api.model.WebResponse;
import com.webapps.api.repository.AddressRepository;
import com.webapps.api.repository.GuestRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
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
    void testCreateAddressWithGuestNotFound() {
        var addressRequest = new CreateAddressRequest();
        addressRequest.setCountry("Indonesia");
        addressRequest.setProvince("Jawa Timur");
        addressRequest.setCity("Jember");
        addressRequest.setSubdistrict("Bangsalsari");
        addressRequest.setDescription("");

        mockMvc.perform(
        post("/api/guests/Not Found/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(addressRequest))
        ).andExpectAll(status().isNotFound())
        .andDo(result -> {
            WebResponse<String> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            Assertions.assertNotNull(response);
            log.info("Error {}", response.getErrors());
        });
    }

    @Test
    @SneakyThrows
    void testCreateAddressBadRequest() {
        var addressRequest = new CreateAddressRequest();
        addressRequest.setCountry("");
        addressRequest.setProvince("Jawa Timur");
        addressRequest.setCity("Jember");
        addressRequest.setSubdistrict("Bangsalsari");
        addressRequest.setDescription("");

        mockMvc.perform(
        post("/api/guests/Guest ID Test/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(addressRequest))
        ).andExpectAll(status().isBadRequest())
            .andDo(result -> {
                WebResponse<String> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
                log.info("Error {}", response.getErrors());
            });
    }

    @Test
    @SneakyThrows
    void testCreateAddressSuccess() {
        var addressRequest = new CreateAddressRequest();
        addressRequest.setCountry("Indonesia");
        addressRequest.setProvince("Jawa Timur");
        addressRequest.setCity("Jember");
        addressRequest.setSubdistrict("Bangsalsari");
        addressRequest.setDescription("");

        mockMvc.perform(
        post("/api/guests/Guest ID Test/addresses")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(addressRequest))
        ).andExpectAll(status().isOk())
        .andDo(result -> {
            WebResponse<AddressResponse> response = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});
            Assertions.assertNotNull(response.getData());
            log.info("Data : {}", response.getData());
        });
    }
}

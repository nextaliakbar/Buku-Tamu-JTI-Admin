package com.webapps.api.service;

import com.webapps.api.entity.Guest;
import com.webapps.api.model.CreateGuestRequest;
import com.webapps.api.model.GuestResponse;
import com.webapps.api.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private ValidationService validationService;

    private String generatedCustomId() {
        String prefix = "G-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))
                + "-" + new Random().nextInt(9999) + "-";

        String lastId = guestRepository.findLastGuestById(prefix);

        int lastIncrement = 0;

        if(lastId !=  null && !lastId.isEmpty()) {
            String[] parts = lastId.split("-");
            lastIncrement = Integer.parseInt(parts[3]);
        }

        return prefix + String.format("%03d", lastIncrement + 1);
    }

    private GuestResponse getGuestResponse(Guest guest) {
        return GuestResponse.builder()
                .id(guest.getId())
                .guestType(guest.getGuestType())
                .name(guest.getName())
                .gender(guest.getGender())
                .placeOfBirth(guest.getPlaceOfBirth())
                .dateOfBirth(guest.getDateOfBirth())
                .noHp(guest.getNoHp())
                .noTelp(guest.getNoTelp())
                .email(guest.getEmail())
                .position(guest.getPosition()).build();
    }

    @Transactional
    public GuestResponse createWithoutAdmin(CreateGuestRequest guestRequest) {
        validationService.validate(guestRequest);
        Guest guest = new Guest();
        guest.setId(generatedCustomId());
        guest.setGuestType(guestRequest.getGuestType());
        guest.setName(guestRequest.getName());
        guest.setGender(guestRequest.getGender());
        guest.setPlaceOfBirth(guestRequest.getPlaceOfBirth());
        guest.setDateOfBirth(guestRequest.getDateOfBirth());
        guest.setNoHp(guestRequest.getNoHp());
        guest.setNoTelp(guestRequest.getNoTelp());
        guest.setEmail(guestRequest.getEmail());
        guest.setPosition(guestRequest.getPosition());
        guestRepository.save(guest);

        return getGuestResponse(guest);
    }
}

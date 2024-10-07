package com.webapps.api.service;

import com.webapps.api.entity.Guest;
import com.webapps.api.entity.Institution;
import com.webapps.api.model.CreateInstitutionRequest;
import com.webapps.api.model.InstitutionResponse;
import com.webapps.api.repository.GuestRepository;
import com.webapps.api.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class InstitutionService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    private String generateCustomId() {
        String prefix = "I-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))
                + "-" + new Random().nextInt(9999) + "-";

        String lastId = institutionRepository.findLastInstitutionByd(prefix);

        int lastIncrement = 0;

        if(lastId !=  null && !lastId.isEmpty()) {
            String[] parts = lastId.split("-");
            lastIncrement = Integer.parseInt(parts[3]);
        }

        return prefix + String.format("%03d", lastIncrement + 1);
    }

    private InstitutionResponse getInstitutionResponse(Institution institution) {
        return InstitutionResponse.builder()
                .id(institution.getId()).name(institution.getName())
                .noTelp(institution.getNoTelp()).noFax(institution.getNoFax())
                .build();
    }

    @Transactional
    public InstitutionResponse createInstitutionWithOutAdmin(CreateInstitutionRequest institutionRequest) {
        validationService.validate(institutionRequest);

        Guest guest = guestRepository.findFirstById(institutionRequest.getGuestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest Not Found"));

        Institution institution = new Institution();
        institution.setId(generateCustomId());
        institution.setName(institutionRequest.getName());
        institution.setNoTelp(institutionRequest.getNoTelp());
        institution.setNoFax(institutionRequest.getNoFax());
        institution.setGuest(guest);
        institutionRepository.save(institution);
        return getInstitutionResponse(institution);
    }
}

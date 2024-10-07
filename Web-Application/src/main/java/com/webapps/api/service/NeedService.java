package com.webapps.api.service;

import com.webapps.api.entity.Guest;
import com.webapps.api.entity.Need;
import com.webapps.api.model.CreateNeedRequest;
import com.webapps.api.model.NeedResponse;
import com.webapps.api.repository.GuestRepository;
import com.webapps.api.repository.NeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class NeedService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private NeedRepository needRepository;


    private String generatedCustomId() {
        String prefix = "N-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM")) + "-" +
                new Random().nextInt(9999) + "-";
        String lastId = needRepository.findLastNeedById(prefix);

        int lastIncrement = 0;
        if(lastId != null && !lastId.isEmpty()) {
            String[] parts = lastId.split("-");
            lastIncrement = Integer.parseInt(parts[3]);
        }

        return prefix +  String.format("%03d", lastIncrement + 1);
    }

    private NeedResponse getNeedResponse(Need need) {
        return NeedResponse.builder()
                .id(need.getId()).title(need.getTitle())
                .createdAt(need.getCreatedAt())
                .description(need.getDescription()).build();
    }

    @Transactional
    public NeedResponse createNeedWithOutAdmin(CreateNeedRequest needRequest) {
        validationService.validate(needRequest);

        Guest guest = guestRepository.findFirstById(needRequest.getGuestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest Not Found"));

        Need need = new Need();
        need.setId(generatedCustomId());
        need.setTitle(needRequest.getTitle());
        need.setCreatedAt(needRequest.getCreatedAt());
        need.setDescription(needRequest.getDescription());
        need.setGuest(guest);
        needRepository.save(need);
        return getNeedResponse(need);
    }
}

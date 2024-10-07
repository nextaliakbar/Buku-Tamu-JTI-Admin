package com.webapps.api.service;

import com.webapps.api.entity.Address;
import com.webapps.api.entity.Guest;
import com.webapps.api.model.AddressResponse;
import com.webapps.api.model.CreateAddressRequest;
import com.webapps.api.repository.AddressRepository;
import com.webapps.api.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class AddressService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private AddressRepository addressRepository;

    private String generatedCustomId() {
        String prefix = "A-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyMM"))
                + "-" + new Random().nextInt(9999) + "-";

        String lastId = addressRepository.findLastAddressById(prefix);

        int lastIncrement = 0;

        if(lastId !=  null && !lastId.isEmpty()) {
            String[] parts = lastId.split("-");
            lastIncrement = Integer.parseInt(parts[3]);
        }

        return prefix + String.format("%03d", lastIncrement + 1);
    }

    private AddressResponse getAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId()).country(address.getCountry())
                .province(address.getProvince()).city(address.getCity())
                .subdistrict(address.getSubdistrict())
                .description(address.getDescription()).build();
    }

    @Transactional
    public AddressResponse createAddressWithOutAdmin(CreateAddressRequest addressRequest) {
        validationService.validate(addressRequest);

        Guest guest = guestRepository.findFirstById(addressRequest.getGuestId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Guest not found"));

        Address address = new Address();
        address.setId(generatedCustomId());
        address.setCountry(addressRequest.getCountry());
        address.setProvince(addressRequest.getProvince());
        address.setCity(addressRequest.getCity());
        address.setSubdistrict(addressRequest.getSubdistrict());
        address.setDescription(addressRequest.getDescription());
        address.setGuest(guest);
        addressRepository.save(address);

        return getAddressResponse(address);
    }

}

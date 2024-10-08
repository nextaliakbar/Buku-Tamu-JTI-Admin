package com.webapps.service;

import com.webapps.api.entity.Guest;
import com.webapps.api.entity.User;
import com.webapps.api.repository.GuestRepository;
import com.webapps.api.repository.UserRepository;
import com.webapps.model.ModelGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GuestAppService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuestRepository guestRepository;

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

    public List<ModelGuest> getAll() {
        List<Guest> guests = guestRepository.findAll();
        return guests.stream().map(this::toModelGuest).collect(Collectors.toList());
    }


    public ModelGuest getByUserAndId(User user, String id) {
        Guest guest = guestRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new RuntimeException("Tamu tidak tersedia"));
        return toModelGuest(guest);
    }

    @Transactional
    public ModelGuest insert(User user, ModelGuest modelGuest) {
        User usr = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Pengguna tidak ditemukan"));

        Guest guest = new Guest();
        guest.setId(generatedCustomId());
        guest.setGuestType(modelGuest.getGuestType());
        guest.setName(modelGuest.getName());
        guest.setGender(modelGuest.getGender());
        guest.setPlaceOfBirth(modelGuest.getPlaceOfBirth());
        guest.setDateOfBirth(modelGuest.getDateOfBirth());
        guest.setNoHp(modelGuest.getNoHp());
        guest.setNoTelp(modelGuest.getNoTelp());
        guest.setEmail(modelGuest.getEmail());
        guest.setPosition(modelGuest.getPosition());
        guest.setUser(usr);
        guestRepository.save(guest);
        return toModelGuest(guest);
    }

    @Transactional
    public ModelGuest update(User user, ModelGuest modelGuest) {
        User usr = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Pengguna tidak ditemukan"));

        Guest guest = new Guest();
        guest.setId(modelGuest.getId());
        guest.setGuestType(modelGuest.getGuestType());
        guest.setName(modelGuest.getName());
        guest.setGender(modelGuest.getGender());
        guest.setPlaceOfBirth(modelGuest.getPlaceOfBirth());
        guest.setDateOfBirth(modelGuest.getDateOfBirth());
        guest.setNoHp(modelGuest.getNoHp());
        guest.setNoTelp(modelGuest.getNoTelp());
        guest.setEmail(modelGuest.getEmail());
        guest.setPosition(modelGuest.getPosition());
        guest.setUser(usr);
        guestRepository.save(guest);
        return toModelGuest(guest);
    }

    @Transactional
    public void delete(User user, String id) {
        Guest guest = guestRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new RuntimeException("Tamu tidak tersedia"));
        guestRepository.delete(guest);
    }

    private ModelGuest toModelGuest(Guest guest) {
        return ModelGuest.builder()
                .id(guest.getId()).guestType(guest.getGuestType())
                .name(guest.getName()).gender(guest.getGender())
                .placeOfBirth(guest.getPlaceOfBirth())
                .dateOfBirth(guest.getDateOfBirth())
                .noHp(guest.getNoHp()).noTelp(guest.getNoTelp())
                .email(guest.getEmail()).position(guest.getPosition())
                .build();
    }
}

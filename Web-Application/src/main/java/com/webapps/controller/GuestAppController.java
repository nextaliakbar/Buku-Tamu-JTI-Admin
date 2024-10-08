package com.webapps.controller;

import com.webapps.api.entity.User;
import com.webapps.model.ModelGuest;
import com.webapps.service.GuestAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/tamu")
public class GuestAppController {

    @Autowired
    private GuestAppService guestService;

    @GetMapping
    public String list(Model model) {
        List<ModelGuest> guests = guestService.getAll();
        model.addAttribute("guests", guests);
        return "tamu/index";
    }

    @GetMapping(path = "/tambah")
    public String showInsert(Model model) {
        model.addAttribute("guest", new ModelGuest());
        return "tamu/input";
    }

    @GetMapping(path = "/edit")
    public String showEdit(Model model) {
        model.addAttribute("guest", new ModelGuest());
        return "tamu/edit";
    }

    @PostMapping(path = "/tambah", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String insert(Model model, @CookieValue("username") String username,
    @ModelAttribute("guest") ModelGuest modelGuest) {
        User user = new User();
        user.setUsername(username);
        guestService.insert(user, modelGuest);
        return "redirect:/tamu";
    }

    @PostMapping(path = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String edit(Model model, @CookieValue("username") String username,
    @RequestParam("id") String id, @ModelAttribute("guest") ModelGuest modelGuest) {
        User user = new User();
        user.setUsername(username);
        ModelGuest guest = guestService.getByUserAndId(user, id);

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

        guestService.update(user, guest);
        return "redirect:/tamu";
    }

    @GetMapping(path = "/hapus")
    public String delete(@CookieValue("username") String username, @RequestParam("id") String id) {
        User user = new User();
        user.setUsername(username);
        guestService.delete(user, id);
        return "redirect:/tamu";
    }
}

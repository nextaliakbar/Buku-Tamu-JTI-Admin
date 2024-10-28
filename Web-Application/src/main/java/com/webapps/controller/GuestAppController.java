package com.webapps.controller;
import com.webapps.api.entity.Need;
import com.webapps.api.entity.User;
import com.webapps.api.model.ProvinceResponse;
import com.webapps.api.service.GuestService;
import com.webapps.api.service.ProvinceService;
import com.webapps.api.service.RegencieService;
import com.webapps.api.service.SubdistrictService;
import com.webapps.model.ModelGuest;
import com.webapps.model.ModelNeed;
import com.webapps.service.GuestAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private RegencieService regencieService;

    @Autowired
    private SubdistrictService subdistrictService;

    @GetMapping
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
    @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<ModelNeed> guests = guestService.getAll(page, size);
        model.addAttribute("guests", guests.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", guests.getTotalPages());
        model.addAttribute("title", "Kunjungan");
        return "tamu/index";
    }

    @GetMapping(path = "/tambah")
    public String showInsert(Model model) {
        List<ProvinceResponse> provinces = provinceService.list();
        model.addAttribute("guest", new ModelGuest());
        model.addAttribute("provinces", provinces);
        model.addAttribute("title", "Tambah Kunjungan");
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

package com.webapps.api.controller;

import com.webapps.api.model.CreateGuestRequest;
import com.webapps.api.model.GuestResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping(path = "/api/guests", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<GuestResponse> createWithoutAdmin(@RequestBody CreateGuestRequest guestRequest) {
        GuestResponse response = guestService.createWithoutAdmin(guestRequest);
        return WebResponse.<GuestResponse>builder().data(response).build();
    }
}

package com.webapps.api.controller;

import com.webapps.api.model.CreateNeedRequest;
import com.webapps.api.model.NeedResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.NeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeedController {

    @Autowired
    private NeedService needService;

    @PostMapping(path = "/api/guests/{GuestId}/needs", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<NeedResponse> createNeedWithOutAdmin(@RequestBody CreateNeedRequest needRequest,
    @PathVariable("GuestId") String guestId) {
        needRequest.setGuestId(guestId);
        NeedResponse response = needService.createNeedWithOutAdmin(needRequest);
        return WebResponse.<NeedResponse>builder().data(response).build();
    }
}

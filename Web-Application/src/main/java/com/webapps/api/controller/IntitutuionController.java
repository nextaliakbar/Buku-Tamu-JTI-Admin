package com.webapps.api.controller;

import com.webapps.api.model.CreateInstitutionRequest;
import com.webapps.api.model.InstitutionResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntitutuionController {

    @Autowired
    private InstitutionService institutionService;

    @PostMapping(path = "/api/guests/{GuestId}/institutions", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<InstitutionResponse> createInstitutionWithOutAdmin(@RequestBody CreateInstitutionRequest institutionRequest,
    @PathVariable("GuestId") String guestId) {
        institutionRequest.setGuestId(guestId);

        InstitutionResponse response = institutionService.createInstitutionWithOutAdmin(institutionRequest);
        return WebResponse.<InstitutionResponse>builder().data(response).build();
    }
}

package com.webapps.api.controller;

import com.webapps.api.model.SubdistrictResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.SubdistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8181")
public class SubdistrictController {

    @Autowired
    private SubdistrictService subdistrictService;

    @GetMapping(path = "/api/regencies/{regencyId}/subdistricts", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<SubdistrictResponse>> get(@PathVariable("regencyId") String regencyId) {
        List<SubdistrictResponse> responses = subdistrictService.getAllSubdistricts(regencyId);
        return WebResponse.<List<SubdistrictResponse>>builder().data(responses).build();
    }
}

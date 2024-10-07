package com.webapps.api.controller;

import com.webapps.api.model.RegencieResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.RegencieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8181")
public class RegencieController {

    @Autowired
    private RegencieService regencieService;

    @GetMapping(path = "/api/provinces/{provinceId}/regencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<RegencieResponse>> get(@PathVariable("provinceId") String provinceId) {
        List<RegencieResponse> responses = regencieService.list(provinceId);
        System.out.println(responses.size());
        return WebResponse.<List<RegencieResponse>>builder().data(responses).build();
    }

}

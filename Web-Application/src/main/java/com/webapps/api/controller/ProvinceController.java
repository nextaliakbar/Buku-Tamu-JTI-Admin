package com.webapps.api.controller;

import com.webapps.api.model.ProvinceResponse;
import com.webapps.api.model.WebResponse;
import com.webapps.api.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping(path = "/api/provinces", produces = MediaType.APPLICATION_JSON_VALUE)
    public WebResponse<List<ProvinceResponse>> get() {
        List<ProvinceResponse> provinces = provinceService.list();
        return WebResponse.<List<ProvinceResponse>>builder().data(provinces).build();
    }
}

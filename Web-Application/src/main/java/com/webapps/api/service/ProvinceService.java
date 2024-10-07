package com.webapps.api.service;

import com.webapps.api.entity.Province;
import com.webapps.api.model.ProvinceResponse;
import com.webapps.api.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Transactional(readOnly = true)
    public List<ProvinceResponse> list() {
        List<Province> provinces = provinceRepository.findAll();

        return provinces.stream().map(this::toProvinceResponse).collect(Collectors.toList());
    }

    private ProvinceResponse toProvinceResponse(Province province) {
        return ProvinceResponse.builder()
                .id(province.getId())
                .name(province.getName()).build();
    }

}

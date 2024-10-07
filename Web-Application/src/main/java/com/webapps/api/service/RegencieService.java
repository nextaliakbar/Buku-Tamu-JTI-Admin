package com.webapps.api.service;

import com.webapps.api.entity.Regencie;
import com.webapps.api.model.RegencieResponse;
import com.webapps.api.repository.RegencieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegencieService {

    @Autowired
    private RegencieRepository regencieRepository;

    public List<RegencieResponse> list(String provinceId) {
        List<Regencie> regencies = regencieRepository.getAllByProvinceId(provinceId);
        return regencies.stream().map(this::toRegencieResponse).collect(Collectors.toList());
    }

    private RegencieResponse toRegencieResponse(Regencie regencie) {
        return RegencieResponse.builder()
                .id(regencie.getId())
                .name(regencie.getName()).build();
    }
}

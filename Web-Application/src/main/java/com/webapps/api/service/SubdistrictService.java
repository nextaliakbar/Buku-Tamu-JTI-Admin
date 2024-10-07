package com.webapps.api.service;

import com.webapps.api.entity.Subdistrict;
import com.webapps.api.model.SubdistrictResponse;
import com.webapps.api.repository.SubdistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubdistrictService {

    @Autowired
    private SubdistrictRepository subdistrictRepository;

    public List<SubdistrictResponse> getAllSubdistricts(String regencyId) {
        List<Subdistrict> subdistricts = subdistrictRepository.getAllByRegencieId(regencyId);
        return subdistricts.stream().map(this::toSubdistrictResponse).collect(Collectors.toList());
    }

    private SubdistrictResponse toSubdistrictResponse(Subdistrict subdistrict) {
        return SubdistrictResponse.builder()
                .id(subdistrict.getId())
                .name(subdistrict.getName()).build();
    }
}

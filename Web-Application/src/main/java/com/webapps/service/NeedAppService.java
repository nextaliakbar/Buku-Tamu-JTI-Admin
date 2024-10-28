package com.webapps.service;

import com.webapps.api.entity.Need;
import com.webapps.api.repository.NeedRepository;
import com.webapps.model.ModelNeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeedAppService {

    @Autowired
    private NeedRepository needRepository;

    private ModelNeed toModelNeed(Need need) {
        return ModelNeed.builder()
                .id(need.getId())
                .title(need.getTitle())
                .description(need.getDescription())
                .createdAt(need.getCreatedAt())
                .updatedAt(need.getUpdatedAt())
                .build();
    }
}

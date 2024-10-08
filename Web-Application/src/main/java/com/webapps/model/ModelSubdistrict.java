package com.webapps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelSubdistrict {

    private String id;

    private String name;

    private ModelRegencie modelRegencie;
}

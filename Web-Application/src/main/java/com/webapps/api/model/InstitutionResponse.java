package com.webapps.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionResponse {

    private String id;

    private String name;

    private String noTelp;

    private String noFax;
}

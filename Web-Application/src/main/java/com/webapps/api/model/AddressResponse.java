package com.webapps.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private String id;

    private String country;

    private String province;

    private String city;

    private String subdistrict;

    private String description;
}

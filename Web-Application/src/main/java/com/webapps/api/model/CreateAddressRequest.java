package com.webapps.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {

    @JsonIgnore
    @NotBlank
    private String guestId;

    @NotBlank
    private String country;

    private String province;

    private String city;

    private String subdistrict;

    private String description;
}

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
public class CreateInstitutionRequest {

    @JsonIgnore
    @NotBlank
    private String guestId;

    @NotBlank
    private String name;

    private String noTelp;

    private String noFax;
}

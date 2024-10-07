package com.webapps.api.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGuestRequest {

    private String guestType;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String gender;

    @NotBlank
    private String placeOfBirth;

    @NotBlank
    private String dateOfBirth;

    @NotBlank
    @Size(max = 20)
    private String noHp;

    private String noTelp;

    @Email
    private String email;

    private String position;
}

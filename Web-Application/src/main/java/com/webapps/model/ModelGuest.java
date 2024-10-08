package com.webapps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelGuest {

    private String id;

    private String guestType;

    private String name;

    private String gender;

    private String placeOfBirth;

    private String dateOfBirth;

    private String noHp;

    private String noTelp;

    private String email;

    private String position;

}

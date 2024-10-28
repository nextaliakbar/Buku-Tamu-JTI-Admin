package com.webapps.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelGuestDetail {

    private ModelGuest modelGuest;

    private ModelAddress modelAddress;

    private ModelNeed modelNeed;

    private Modellnstitution modellnstitution;
}

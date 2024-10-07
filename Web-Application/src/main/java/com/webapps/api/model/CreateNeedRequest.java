package com.webapps.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNeedRequest {

    @JsonIgnore
    @NotBlank
    private String guestId;

    @NotBlank
    private String title;

    private LocalDateTime createdAt;

    private String description;
}

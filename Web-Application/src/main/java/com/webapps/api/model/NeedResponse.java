package com.webapps.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NeedResponse {

    private String id;

    private String title;

    private LocalDateTime createdAt;

    private String description;
}

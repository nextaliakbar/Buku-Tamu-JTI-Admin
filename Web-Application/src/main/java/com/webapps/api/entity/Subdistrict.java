package com.webapps.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subdistricts")
public class Subdistrict {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "regencie_id", referencedColumnName = "id")
    private Regencie regencie;
}

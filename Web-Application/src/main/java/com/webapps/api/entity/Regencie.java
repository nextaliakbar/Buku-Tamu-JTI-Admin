package com.webapps.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "regencies")
public class Regencie {

    @Id
    private String id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

    @OneToMany(mappedBy = "regencie")
    private List<Subdistrict> subdistricts;
}

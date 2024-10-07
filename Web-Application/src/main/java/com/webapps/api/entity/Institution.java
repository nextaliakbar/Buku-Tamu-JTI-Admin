package com.webapps.api.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "institutions")
public class Institution {

    @Id
    private String id;

    private String name;

    @Column(name = "no_telp")
    private String noTelp;

    @Column(name = "no_fax")
    private String noFax;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;
}

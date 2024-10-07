package com.webapps.api.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guests")
public class Guest {

    @Id
    private String id;

    @Column(name = "guest_type")
    private String guestType;

    private String name;

    private String gender;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "no_hp")
    private String noHp;

    @Column(name = "no_telp")
    private String noTelp;

    private String email;

    private String position;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @OneToMany(mappedBy = "guest")
    private List<Address> addresses;

    @OneToMany(mappedBy = "guest")
    private List<Institution> institutions;

    @OneToMany(mappedBy = "guest")
    private List<Need> needs;
}

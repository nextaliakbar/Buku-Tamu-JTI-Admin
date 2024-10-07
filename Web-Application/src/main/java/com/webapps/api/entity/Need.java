package com.webapps.api.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "needs")
public class Need {

    @Id
    private String id;

    private String title;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String description;

    @ManyToOne
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;

}

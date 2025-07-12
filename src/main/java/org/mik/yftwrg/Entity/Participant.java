package org.mik.yftwrg.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participant extends BaseEntity{

    //Base Entity has
    //id, name, createdAt, updatedAt

    @NotBlank(message = "participantId is required")
    @Column(nullable = false, unique = true)
    private String participantId;

    @Email(message = "Email should be valid")
    private String email;

    @ManyToMany(mappedBy = "competitors") //Partici@ant - Event
    private Set<Event> events;

}

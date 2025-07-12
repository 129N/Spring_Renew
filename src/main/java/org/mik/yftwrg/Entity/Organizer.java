package org.mik.yftwrg.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organizer extends BaseEntity {

    //Base Entity has
    //id, name, createdAt, updatedAt

    @NotBlank(message = "Contact is required")
    private String contact;

    @OneToMany(mappedBy = "organizer")
    @JsonManagedReference
    private Set<Event> events = new HashSet<>();
}

//    @OneToOne(mappedBy = "organizer")
//    private Event event;

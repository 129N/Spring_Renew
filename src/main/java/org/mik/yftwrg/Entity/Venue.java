package org.mik.yftwrg.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venue extends BaseEntity{

    //Base Entity has
    //id, name, createdAt, updatedAt

    @NotBlank(message = "Address is required")
    private String address;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Event>  events = new HashSet<>();
}

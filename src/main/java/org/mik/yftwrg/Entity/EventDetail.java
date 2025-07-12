package org.mik.yftwrg.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDetail extends BaseEntity{
    //Base Entity has
    //id, name, createdAt, updatedAt

    private String description;
    private String locationNote;

    @OneToOne
    @JoinColumn(name = "event_id", unique = true)
    @JsonBackReference
    private Event event;

}

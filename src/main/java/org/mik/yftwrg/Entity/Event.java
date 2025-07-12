package org.mik.yftwrg.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity{


// relationship with Venue, Organizer, Participant
    //Base Entity has
    //id, name, createdAt, updatedAt

    @NotNull(message = "Date is required")
    private LocalDateTime eventDate;

    @NotNull(message = "Event type is required")
    private String type;

    @NotNull(message = "Venue is required")
    @ManyToOne  // Event(Many) - Venue(One)
    @JoinColumn(name = "venue_id") //denotes the column as venue_id
    @JsonBackReference
    private Venue venue;

    @NotNull(message = "Organizer is required")
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    @JsonBackReference
    private Organizer organizer;

    @ManyToMany  //Event - Paricipant
    @JoinTable(
            name = "event_participant",
            joinColumns =  @JoinColumn(name = "event_id"),
            inverseJoinColumns =  @JoinColumn(name = "participant_id")
    )
    private Set<Participant> competitors;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonManagedReference
    private EventDetail eventDetail;

}

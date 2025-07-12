package org.mik.yftwrg.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mik.yftwrg.Entity.EventDetail;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO extends AbstractDTO<Long> {

    private LocalDateTime date; //auto
    private String type; //manual writing
    private VenueDTO venuedto;
    private OrganizerDTO organizerdto; // name of Organizer
    private Set<ParticipantDTO> competitors; // particpant
    private EventDetailDTO eventDetailDTO;

}

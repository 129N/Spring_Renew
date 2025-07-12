package org.mik.yftwrg.Service;

import org.mik.yftwrg.Entity.EventDetail;
import java.util.List;

public interface EventDetailService {

    List<EventDetail> getAllEventDetails();

    EventDetail getEventDetailById(Long id); // this id is Extended by BaseEntity
    EventDetail saveEventDetail(EventDetail eventDetail); // Event in the Entity7
    EventDetail updateEventDetail(Long id, EventDetail eventDetail); //update it
    void deleteEventDetail(Long id); // this id is Extended by BaseEntity
}

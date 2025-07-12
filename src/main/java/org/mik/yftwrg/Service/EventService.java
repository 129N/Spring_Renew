package org.mik.yftwrg.Service;

import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.EventDetail;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();
    Event  getEventById(Long id); // this id is Extended by BaseEntity
    Event saveEvent(Event event); // Event in the Entity

    Event updateEvent(Long id, Event event); //
    void deleteEvent(Long id); // this id is Extended by BaseEntity

}

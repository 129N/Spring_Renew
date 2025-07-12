package org.mik.yftwrg.Service.Implement;

import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.Organizer;
import org.mik.yftwrg.Entity.Participant;
import org.mik.yftwrg.Entity.Venue;
import org.mik.yftwrg.Exceptions.ResourceNotFoundException;
import org.mik.yftwrg.Repositories.*;
import org.mik.yftwrg.Service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;
    private final ParticipantRepository participantRepository;
    private final EventDetailRepository  eventDetailRepository;


    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }


    @Override
    public Event getEventById(Long id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id" + id));
    }

    @Override
    public Event saveEvent(Event event){


        // Organizer
        if (event.getOrganizer() != null && event.getOrganizer().getId() != null) {
            Organizer managedOrganizer = organizerRepository.findById(event.getOrganizer().getId())
                    .orElseThrow(() -> new RuntimeException("Organizer not found"));
            event.setOrganizer(managedOrganizer);
        }

        // Venue
        if (event.getVenue() != null && event.getVenue().getId() != null) {
            Venue managedVenue = venueRepository.findById(event.getVenue().getId())
                            .orElseThrow( () -> new RuntimeException("Venue not found") );
                event.setVenue(managedVenue);
//            event.setVenue(venueRepository.findById(event.getVenue().getId())
//                    .orElseThrow(() -> new RuntimeException("Venue not found")));
        }

        // Participants (if using ManyToMany or Set<Participant>)
        if (event.getCompetitors() != null) {
            Set<Participant> managedParticipants = event.getCompetitors().stream()
                    .map(p -> participantRepository.findById(p.getId())
                            .orElseThrow(() -> new RuntimeException("Participant not found")))
                    .collect(Collectors.toSet());
            event.setCompetitors(managedParticipants);
        }

        // EventDetail (if OneToOne and cascade not enabled)
        if (event.getEventDetail() != null && event.getEventDetail().getId() != null) {
            event.setEventDetail(eventDetailRepository.findById(event.getEventDetail().getId())
                    .orElseThrow(() -> new RuntimeException("EventDetail not found")));
        }

        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id, Event ev){
        Event existingevent = eventRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("EventServiceImpl Event Not found"));
        // Update simple fields
        existingevent.setName(ev.getName());
        existingevent.setEventDate(ev.getEventDate());
        existingevent.setType(ev.getType());

//        //Update relationship
//        existingevent.setVenue(ev.getVenue());
//        existingevent.setOrganizer(ev.getOrganizer());
//
//        // For competitors (Set<Participant>), you may want to fully replace or selectively update
//        existingevent.setCompetitors(ev.getCompetitors());
//
//        //OneToOne Relationship update
//        existingevent.setEventDetail(ev.getEventDetail());


        // Update Organizer
        if (ev.getOrganizer() != null && ev.getOrganizer().getId() != null) {
            Organizer managedOrganizer = organizerRepository.findById(ev.getOrganizer().getId())
                    .orElseThrow(() -> new RuntimeException("Organizer not found"));
            existingevent.setOrganizer(managedOrganizer);
        }

        // Update Venue
        if (ev.getVenue() != null && ev.getVenue().getId() != null) {
            Venue managedVenue = venueRepository.findById(ev.getVenue().getId())
                    .orElseThrow(() -> new RuntimeException("Venue not found"));
            existingevent.setVenue(managedVenue);
        }

        // Update Competitors (Set<Participant>)
        if (ev.getCompetitors() != null) {
            Set<Participant> managedParticipants = ev.getCompetitors().stream()
                    .map(p -> participantRepository.findById(p.getId())
                            .orElseThrow(() -> new RuntimeException("Participant not found")))
                    .collect(Collectors.toSet());
            existingevent.setCompetitors(managedParticipants);
        }

        // Update EventDetail
        if (ev.getEventDetail() != null && ev.getEventDetail().getId() != null) {
            existingevent.setEventDetail(eventDetailRepository.findById(ev.getEventDetail().getId())
                    .orElseThrow(() -> new RuntimeException("EventDetail not found")));
        }


        return eventRepository.save(existingevent);
    }

    @Override
    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }




}

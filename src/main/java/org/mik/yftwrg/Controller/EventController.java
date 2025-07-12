package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8000/api/events
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    //GET ALL API
    @GetMapping("")
    public ResponseEntity<List<Event>> getAllEvents(){
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // GET ID  Event getEventById(Long id) from implement
    //@PathVariable : to extract values from the URL path
    @GetMapping("/{id}")
    public ResponseEntity <Event> getEventById(@PathVariable Long id){
        Event savedEvent = eventService.getEventById(id);
        return ResponseEntity.ok(savedEvent);
    }

    // Save Event -> POST Event saveEvent(Event event){ return eventRepository.save(event); }
    //@RequestBody : to bind the request body JSON to a Java object.
    @PostMapping("")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event){
        Event savedEvent = eventService.saveEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,
                                             @RequestBody @Valid Event event){
        Event updated = eventService.updateEvent(id, event);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Delete an event by ID deleteEvent(Long id){eventRepository.deleteById(id);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}

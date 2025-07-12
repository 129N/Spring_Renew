package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.DTO.EventDetailDTO;
import org.mik.yftwrg.Entity.EventDetail;
import org.mik.yftwrg.Mapper.EventDetailMapper;
import org.mik.yftwrg.Service.EventDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventDetail")
@RequiredArgsConstructor
public class EventDetailController {
    private final EventDetailService eventDetailService;
    private final EventDetailMapper eventDetailMapper;

    //GET ALL API
    @GetMapping("")
    public ResponseEntity<List<EventDetail>> getAllEvents(){return ResponseEntity.ok(eventDetailService.getAllEventDetails());}

    @GetMapping("/{id}")
    public ResponseEntity <EventDetail> getEventById(@PathVariable Long id){
        EventDetail savedEvent = eventDetailService.getEventDetailById(id);
        return ResponseEntity.ok(savedEvent);
    }

//    @PostMapping("")
//    public ResponseEntity<EventDetail> saveEvent(@Valid @RequestBody EventDetail EV){
//        EventDetail savedEvent = eventDetailService.saveEvent(EV);
//        return ResponseEntity.ok(savedEvent);
//    }


    //EventDetailDTO in controller methods and map to/from
    // entities in service or controller layer with your EventDetailMapper.
    @PostMapping("")
    public ResponseEntity<EventDetailDTO> saveEvent(@Valid @RequestBody EventDetailDTO dto){
        EventDetail entity = eventDetailMapper.toEntity(dto);
        EventDetail saved = eventDetailService.saveEventDetail(entity);
        return ResponseEntity.ok(eventDetailMapper.toDto(saved));
    }

    // DELETE: Delete an event by ID deleteEvent(Long id){eventRepository.deleteById(id);}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id){
        eventDetailService.deleteEventDetail(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDetail> updateEventDetail(@PathVariable Long id,
                                                            @RequestBody @Valid EventDetail eventDetail){
        EventDetail updated = eventDetailService.updateEventDetail(id,eventDetail);
        return ResponseEntity.ok(updated);
    }
}

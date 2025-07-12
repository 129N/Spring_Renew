package org.mik.yftwrg.Controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Organizer;
import org.mik.yftwrg.Service.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
public class OrganizerController {

    private final OrganizerService organizerService;

    //GET ALL API
    @GetMapping("")
    public List<Organizer> getAllOrganizers(){
        return organizerService.getAllOrganizers();
    }

    //GET ID API
    @GetMapping("/{id}")
    public ResponseEntity<Organizer> getOrganizerById(@PathVariable  Long id){
        Organizer organizer = organizerService.getOrganizerById(id);
        return ResponseEntity.ok(organizer);
    }

    //POST API -> save
    @PostMapping("")
    public ResponseEntity<Organizer>  saveOrganizer(@Valid @RequestBody Organizer savedOrganizer){
        Organizer organizer = organizerService.saveOrganizer(savedOrganizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizer);
    }


    //UPDATE API
    @PutMapping("/{id}")
    public ResponseEntity<Organizer> updateOrganizer(@PathVariable Long id,
                                                     @Valid @RequestBody Organizer updated) {
        Organizer organizer = organizerService.updateOrganizer(id, updated);

        return ResponseEntity.ok(organizer);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable Long id) {
        organizerService.deleteOrganizer(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}

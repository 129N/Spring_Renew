package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Venue;
import org.mik.yftwrg.Service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    //GET All API
    @GetMapping("")
    public List<Venue> getAllVenues(){

        return venueService.getAllVenues();
    }

    //GET ID API
    @GetMapping("/{id}")
    public ResponseEntity<Venue> getVenueById(@PathVariable  Long id){
        Venue venue = venueService.getVenueById(id);
        return ResponseEntity.ok(venue);  // 200 OK
    }

    // POST API: save
    @PostMapping("")
    public ResponseEntity<Venue> saveVenue(@Valid @RequestBody Venue venue){
    Venue saved = venueService.saveVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);  // 201 Created
    }

    //PUT API: update the Venue
    @PutMapping("/{id}")
    public ResponseEntity<Venue> updateVenue(@PathVariable Long id,
                                             @RequestBody @Valid Venue updatedVenue){
        Venue updated = venueService.updateVenue(id, updatedVenue);
        return ResponseEntity.ok(updated); // 200 OK
    }
    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable  Long id){
        venueService.deleteVenue(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}

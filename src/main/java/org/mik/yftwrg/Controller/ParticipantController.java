package org.mik.yftwrg.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Participant;
import org.mik.yftwrg.Service.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/api/participants
@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class ParticipantController {

    private final ParticipantService participantService;

    // GET ALL API
    @GetMapping("")
    public  List<Participant> getAllParticipants(){
        return  participantService.getAllParticipants();
    }

    //GET ID API
    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id){
        Participant participant = participantService.getParticipantById(id);
        return ResponseEntity.ok(participant);
    }

    //POST API -> save
    @PostMapping("")
    public ResponseEntity<Participant> saveParticpant(@Valid  @RequestBody  Participant savedparticipant){
        Participant participant_instar = participantService.saveParticpant(savedparticipant);
        return ResponseEntity.status(HttpStatus.CREATED).body(participant_instar);  // 201 Created
    }

    //Update API
    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(
            @PathVariable Long id,
            @Valid @RequestBody Participant UpdatedParticipant){
        Participant participant = participantService.updateParticipant(id,UpdatedParticipant);
        return ResponseEntity.ok(participant); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}


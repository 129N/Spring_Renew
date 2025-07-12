package org.mik.yftwrg.Service;

import org.mik.yftwrg.Entity.Participant;

import java.util.List;

public interface ParticipantService {

    List<Participant> getAllParticipants(); // First get all information

    Participant getParticipantById(Long id); // Get by ID

    Participant saveParticpant(Participant participant); // Save new and

    Participant updateParticipant(Long id, Participant participantDetails); // baseEntity, Participant Entity

    void deleteParticipant(Long id); // Extended by BaseEntity
}

package org.mik.yftwrg.Service.Implement;

import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Participant;
import org.mik.yftwrg.Repositories.ParticipantRepository;
import org.mik.yftwrg.Service.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepository participantRepository;

    @Override
    public List<Participant> getAllParticipants(){
        return participantRepository.findAll();
    }

    @Override
    public Participant getParticipantById(Long id){
        return participantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("getParticipantById : Participant not found"));
    }

    @Override
    public Participant saveParticpant(Participant participant){
        return participantRepository.save(participant);
    }

    @Override
    public Participant updateParticipant(Long id, Participant participantDetails){
        Participant existing_participant = participantRepository.findById(id)
                .orElseThrow(  () -> new RuntimeException("updateParticipant: Participant is not found"));
        existing_participant.setName(participantDetails.getName()); //update name
        existing_participant.setEmail(participantDetails.getEmail()); //update email
    return  participantRepository.save(existing_participant);
    }
    @Override
    public void  deleteParticipant(Long id){
        participantRepository.deleteById(id);
    }

}

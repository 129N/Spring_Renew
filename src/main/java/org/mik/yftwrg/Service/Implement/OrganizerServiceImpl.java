package org.mik.yftwrg.Service.Implement;

import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Organizer;
import org.mik.yftwrg.Repositories.OrganizerRepository;
import org.mik.yftwrg.Service.OrganizerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Override
    public Organizer saveOrganizer(Organizer organizer){
        return organizerRepository.save(organizer);
    }

    @Override
    public List<Organizer> getAllOrganizers(){
        return organizerRepository.findAll();
    }

    @Override
    public Organizer getOrganizerById(Long id){
        return organizerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Organizer not found") );
    }

    @Override
    public Organizer updateOrganizer(Long id, Organizer organizerDetails){
        Organizer exisitng_organizer = organizerRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Organizer not found") );

        exisitng_organizer.setName(organizerDetails.getName());
        exisitng_organizer.setContact(organizerDetails.getContact());

        return organizerRepository.save(exisitng_organizer);
    }
    @Override
    public void deleteOrganizer(Long id){
        organizerRepository.deleteById(id);
    }


}

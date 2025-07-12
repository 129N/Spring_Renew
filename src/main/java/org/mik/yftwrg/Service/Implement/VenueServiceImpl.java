package org.mik.yftwrg.Service.Implement;

import lombok.RequiredArgsConstructor;
import org.mik.yftwrg.Entity.Venue;
import org.mik.yftwrg.Repositories.VenueRepository;
import org.mik.yftwrg.Service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService{

    private final VenueRepository venueRepository;

    @Override
    public List<Venue> getAllVenues(){
        return venueRepository.findAll();
    }

    @Override
    public Venue getVenueById(Long id){
        return venueRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("getParticipantById : Venue is not found") );
    }

    @Override
    public Venue saveVenue(Venue venue){
        return venueRepository.save(venue);
    }

    @Override
    public Venue updateVenue(Long id, Venue venueDetails){
        Venue existingVenue = venueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venue not found"));

        existingVenue.setName(venueDetails.getName());
        existingVenue.setAddress(venueDetails.getAddress());

        return venueRepository.save(existingVenue);
    }

    @Override
    public void deleteVenue(Long id){
         venueRepository.deleteById(id);
    }

}

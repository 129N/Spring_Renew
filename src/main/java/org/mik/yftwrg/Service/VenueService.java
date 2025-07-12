package org.mik.yftwrg.Service;

import org.mik.yftwrg.Entity.Venue;

import java.util.List;

public  interface VenueService {

    List<Venue> getAllVenues(); // fetch all venue
    Venue getVenueById(Long id); // get the venue id and base abstract
    Venue saveVenue(Venue venue); // save the Venue
    Venue updateVenue(Long id, Venue venueDetails); // update it and base abstract
    void deleteVenue(Long id); // delete

}

package org.mik.yftwrg.Service;

import org.mik.yftwrg.Entity.Organizer;

import java.util.List;

public interface OrganizerService {

    Organizer saveOrganizer(Organizer organizer);

    List<Organizer>getAllOrganizers();

    Organizer getOrganizerById(Long id); // Extended by BaseEntity

    Organizer updateOrganizer(Long id, Organizer organizerDetails); // Organizer in the Entity
    void deleteOrganizer(Long id); // Extended by BaseEntity


}

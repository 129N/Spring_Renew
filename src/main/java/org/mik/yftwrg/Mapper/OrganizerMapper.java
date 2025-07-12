package org.mik.yftwrg.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mik.yftwrg.DTO.OrganizerDTO;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.Organizer;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class OrganizerMapper implements EntityMapper<Organizer, OrganizerDTO> {

    @Override
    public Organizer toEntity(OrganizerDTO dto){
        if(dto == null) return null;

        Organizer entity = new Organizer();
        //Org has eventId and contact with id, create and update
        entity.setId(dto.getId());
        entity.setCreatedAt(dto.getCreated());
        entity.setUpdatedAt(dto.getUpdated());
        entity.setContact(dto.getContact());

        //set event referenced by id
        if(dto.getEventId() != null){
            Set<Event> events = dto.getEventIds().stream()
                            .map(id -> {
                                        Event event = new Event();
                                        event.setId(id);
                                        return event;
                                    }).collect(Collectors.toSet());
            entity.setEvents(events);
        }
        return entity;
    }

    @Override
    public abstract OrganizerDTO toDto(Organizer entity);

    @Override
    public abstract void  updateFromDto(OrganizerDTO dto, @MappingTarget  Organizer entity);


    //final line
}

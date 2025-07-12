package org.mik.yftwrg.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mik.yftwrg.DTO.EventDTO;

import org.mik.yftwrg.Entity.Event;

import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", uses = {OrganizerMapper.class, VenueMapper.class, ParticipantMapper.class})
public abstract class EventMapper implements EntityMapper<Event, EventDTO>{

    @Autowired
    protected OrganizerMapper organizerMapper;

    @Autowired
    protected VenueMapper venueMapper;

    @Autowired
    protected ParticipantMapper participantMapper;

    @Override
    public abstract Event toEntity(EventDTO dto);

    @Override
    public abstract EventDTO toDto(Event entity);

    @Override
    public abstract void updateFromDto(EventDTO dto, @MappingTarget Event entity);



    //final linr
        }
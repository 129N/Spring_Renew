package org.mik.yftwrg.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mik.yftwrg.DTO.ParticipantDTO;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.Participant;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ParticipantMapper implements EntityMapper<Participant, ParticipantDTO> {

    //toDto and toEntity

    @Named("mapIdsToEvents")
    protected Set<Event> mapIdsToEvents(Set<Long> eventIds) {
        if (eventIds == null) return null;
        return eventIds.stream()
                .map(id -> {
                    Event event = new Event();
                    event.setId(id);
                    return event;
                })
                .collect(Collectors.toSet());
    }

    @Named("mapEventsToIds")
    protected Set<Long> mapEventsToIds(Set<Event> events) {
        if (events == null) return null;
        return events.stream()
                .map(Event::getId)
                .collect(Collectors.toSet());
    }
    @Override
    @Mapping(target = "events", source = "eventIds", qualifiedByName = "mapIdsToEvents")
    public abstract Participant toEntity(ParticipantDTO dto);

    @Override
    @Mapping(target = "eventIds", source = "events", qualifiedByName = "mapEventsToIds")
    public abstract ParticipantDTO toDto(Participant entity);

    @Override
    @Mapping(target = "events", source = "eventIds", qualifiedByName = "mapIdsToEvents")
    public abstract void updateFromDto(ParticipantDTO dto, @MappingTarget Participant entity);
}

package org.mik.yftwrg.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mik.yftwrg.DTO.VenueDTO;
import org.mik.yftwrg.Entity.Event;
import org.mik.yftwrg.Entity.Venue;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class VenueMapper implements EntityMapper<Venue, VenueDTO>{
    @Named("mapIdsToEvents") //to convert List<Long> to List<Event>
    public Set<Event> mapIdsToEvents(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    Event event = new Event();
                    event.setId(id);
                    return event;
                })
                .collect(Collectors.toSet());
    }

    @Named("mapEventsToIds")
    public  List<Long> mapEventsToIds(Set<Event> events) {
        if (events == null) return null;
        return events.stream()
                .map(Event::getId)
                .collect(Collectors.toList());
    }

    @Override
    @Mapping(target = "events", source = "eventIds", qualifiedByName = "mapIdsToEvents")
    public abstract Venue toEntity(VenueDTO dto);

    @Override
    @Mapping(target = "eventIds", source = "events", qualifiedByName = "mapEventsToIds")
    public  abstract VenueDTO toDto(Venue venue);

    @Override
    @Mapping(target = "events", source = "eventIds", qualifiedByName = "mapIdsToEvents")
    public abstract void updateFromDto(VenueDTO dto,@MappingTarget Venue entity);

}

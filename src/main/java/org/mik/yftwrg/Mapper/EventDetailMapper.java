package org.mik.yftwrg.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mik.yftwrg.DTO.EventDetailDTO;
import org.mik.yftwrg.Entity.EventDetail;

@Mapper(componentModel = "spring")
public abstract class EventDetailMapper implements EntityMapper<EventDetail, EventDetailDTO>{

    @Override
    public abstract EventDetail toEntity(EventDetailDTO dto);

    @Override
    public abstract  EventDetailDTO toDto(EventDetail entity);

    @Override
    public abstract void updateFromDto(EventDetailDTO dto, @MappingTarget EventDetail entity);

}

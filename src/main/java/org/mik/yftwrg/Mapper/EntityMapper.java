package org.mik.yftwrg.Mapper;


import org.mapstruct.MappingTarget;
import org.mik.yftwrg.DTO.AbstractDTO;
import org.mik.yftwrg.Entity.BaseEntity;



public interface EntityMapper< E extends BaseEntity,  D extends AbstractDTO<Long> > {
    E toEntity(D dto);
    D toDto(E entity);
    void updateFromDto(D dto,@MappingTarget E entity);
}

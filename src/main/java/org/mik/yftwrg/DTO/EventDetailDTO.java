package org.mik.yftwrg.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDetailDTO extends AbstractDTO<Long>{
    private String description;
    private String locationNote;
}

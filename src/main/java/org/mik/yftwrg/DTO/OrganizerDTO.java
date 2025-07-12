package org.mik.yftwrg.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrganizerDTO extends AbstractDTO<Long>{
    private String contact;
    private Long eventId;
    private Set<Long> eventIds;
}

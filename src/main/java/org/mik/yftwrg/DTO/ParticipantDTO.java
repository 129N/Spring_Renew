package org.mik.yftwrg.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO extends AbstractDTO<Long>{
    private Set<Long> eventIds;
    private String participantId;
    private String email;
}

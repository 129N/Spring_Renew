package org.mik.yftwrg.DTO;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenueDTO extends AbstractDTO<Long>{
    private List<Long> eventIds; // Avoid full circular reference
    private String address; //Venue address from Entity
}

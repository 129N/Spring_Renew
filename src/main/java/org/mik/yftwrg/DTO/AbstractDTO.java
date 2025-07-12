package org.mik.yftwrg.DTO;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public abstract class AbstractDTO <ID extends Serializable> implements Serializable{

    private ID id; //auto
    private LocalDateTime created; //auto
    private LocalDateTime updated; //auto
    private Integer version; //this is optional
}

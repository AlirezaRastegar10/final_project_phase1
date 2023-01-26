package ir.maktab.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Duty extends BaseEntity<Long> {

    String name;

    @OneToMany(mappedBy = "duty")
    List<UnderDuty> underDuties;

    public Duty(String name) {
        this.name = name;
    }
}

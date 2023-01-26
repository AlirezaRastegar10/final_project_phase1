package ir.maktab.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UnderDuty extends BaseEntity<Long> {

    String name;
    Long basePrice;
    String explanation;

    @ManyToMany(mappedBy = "underDutySet", fetch = FetchType.EAGER)
    Set<Expert> expertSet = new HashSet<>();

    @ManyToOne
    Duty duty;

    public UnderDuty(String name, Long basePrice, String explanation, Duty duty) {
        this.name = name;
        this.basePrice = basePrice;
        this.explanation = explanation;
        this.duty = duty;
    }
}

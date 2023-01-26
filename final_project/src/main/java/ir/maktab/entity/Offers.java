package ir.maktab.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Offers extends BaseEntity<Long> {

    @ManyToOne
    Expert expert;

    Long timeAndDate;
    Long proposedPrice;
    Long suggestedTime;
    String durationOfWork;

    @ManyToMany(mappedBy = "offers")
    List<Orders> orders;
}

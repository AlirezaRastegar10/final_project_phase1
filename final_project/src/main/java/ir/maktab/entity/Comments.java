package ir.maktab.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Comments extends BaseEntity<Long> {

    @ManyToOne
    Customer customer;

    @ManyToOne
    Expert expert;

    Integer score;
    String content;
}

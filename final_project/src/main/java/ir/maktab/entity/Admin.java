package ir.maktab.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Admin extends BaseEntity<Long> {

    String username;
    Integer age;

    @OneToOne(cascade = CascadeType.ALL)
    User user;
}

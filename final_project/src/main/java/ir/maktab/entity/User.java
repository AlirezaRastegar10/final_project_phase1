package ir.maktab.entity;


import ir.maktab.entity.enumeration.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Users")
public class User extends BaseEntity<Long> {

    String firstname;
    String lastname;
    String email;
    String password;
    Long registerDate;

    @Enumerated(EnumType.STRING)
    UserType userType;
}

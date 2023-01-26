package ir.maktab.entity;

import ir.maktab.entity.enumeration.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Orders extends BaseEntity<Long> {

    @ManyToOne
    Customer customer;

    Long proposedPrice;
    String description;
    Long dateAndTime;
    String address;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL)
    UnderDuty underDuty;

    @ManyToMany()
    @JoinTable(
            joinColumns = {@JoinColumn(name = "orders_id")},
            inverseJoinColumns = {@JoinColumn(name = "offers_id")}
    )
    List<Offers> offers;

    public Orders(Customer customer, Long proposedPrice, String description, Long dateAndTime, String address, OrderStatus orderStatus, UnderDuty underDuty) {
        this.customer = customer;
        this.proposedPrice = proposedPrice;
        this.description = description;
        this.dateAndTime = dateAndTime;
        this.address = address;
        this.orderStatus = orderStatus;
        this.underDuty = underDuty;
    }
}

package ir.maktab.repository;


import ir.maktab.entity.Orders;
import ir.maktab.entity.UnderDuty;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class OrdersRepository extends BaseRepository<Orders> {

    public OrdersRepository() {
        super("Orders", Orders.class);
    }

    public List<Orders> getOrdersByCustomerId(Long id) {
        Session session = sessionFactory.openSession();
        Query<Orders> query =
                session.createQuery("select o from Orders o where o.customer.id=:id", Orders.class)
                        .setParameter("id", id);
        List<Orders> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;
        else {
            return list;
        }
    }
}

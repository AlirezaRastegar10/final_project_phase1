package ir.maktab.repository;

import ir.maktab.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerRepository extends BaseRepository<Customer> {

    public CustomerRepository() {
        super("Customer", Customer.class);
    }

    public Customer getCustomerByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        Query<Customer> query =
                session.createQuery("select c from Customer c where c.user.id=:id", Customer.class)
                        .setParameter("id", userId);
        List<Customer> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}

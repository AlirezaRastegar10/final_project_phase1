package ir.maktab.repository;

import ir.maktab.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository extends BaseRepository<User> {

    public UserRepository() {
        super("User", User.class);
    }

    public User getUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        Query<User> query =
                session.createQuery("select u from User u where u.email=:email", User.class)
                        .setParameter("email", email);
        List<User> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    public User getUserByEmailAndId(Long id, String email) {
        Session session = sessionFactory.openSession();
        Query<User> query =
                session.createQuery("select u from User u where u.id=:id and u.email=:email", User.class)
                        .setParameter("id", id)
                        .setParameter("email", email);
        List<User> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}

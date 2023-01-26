package ir.maktab.repository;

import ir.maktab.entity.Duty;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DutyRepository extends BaseRepository<Duty> {

    public DutyRepository() {
        super("Duty", Duty.class);
    }

    public Duty getDutyByName(String name) {
        Session session = sessionFactory.openSession();
        Query<Duty> query =
                session.createQuery("select d from Duty d where d.name=:name", Duty.class)
                        .setParameter("name", name);
        List<Duty> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}

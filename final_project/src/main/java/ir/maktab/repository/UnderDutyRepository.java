package ir.maktab.repository;




import ir.maktab.entity.UnderDuty;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;

public class UnderDutyRepository extends BaseRepository<UnderDuty> {

    public UnderDutyRepository() {
        super("UnderDuty", UnderDuty.class);
    }

    public List<UnderDuty> getUnderDutiesByDutyId(Long id) {
        Session session = sessionFactory.openSession();
        Query<UnderDuty> query =
                session.createQuery("select u from UnderDuty u where u.duty.id=:id", UnderDuty.class)
                        .setParameter("id", id);
        List<UnderDuty> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;
        else {
            return list;
        }
    }

    public UnderDuty getUnderDutyByDutyIdAndName(Long id, String name) {
        Session session = sessionFactory.openSession();
        Query<UnderDuty> query =
                session.createQuery("select u from UnderDuty u where u.duty.id=:id and u.name=:name", UnderDuty.class)
                        .setParameter("id", id)
                        .setParameter("name", name);
        List<UnderDuty> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}

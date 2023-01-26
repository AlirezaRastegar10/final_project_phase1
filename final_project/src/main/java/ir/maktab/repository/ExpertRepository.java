package ir.maktab.repository;


import ir.maktab.entity.Expert;
import ir.maktab.entity.enumeration.ExpertStatus;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ExpertRepository extends BaseRepository<Expert> {

    public ExpertRepository() {
        super("ExpertService", Expert.class);
    }

    public List<Expert> getExpertsByStatus(ExpertStatus status) {
        Session session = sessionFactory.openSession();
        Query<Expert> query =
                session.createQuery("select e from Expert e where e.status=:status", Expert.class)
                        .setParameter("status", status);
        List<Expert> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;
        else {
            return list;
        }
    }

    public Expert getExpertsByStatusAndId(Long id, ExpertStatus status) {
        Session session = sessionFactory.openSession();
        Query<Expert> query =
                session.createQuery("select e from Expert e where e.status=:status and e.id=:id", Expert.class)
                        .setParameter("status", status)
                        .setParameter("id", id);
        List<Expert> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    public Expert getExpertByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        Query<Expert> query =
                session.createQuery("select e from Expert e where e.user.id=:id", Expert.class)
                        .setParameter("id", userId);
        List<Expert> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}

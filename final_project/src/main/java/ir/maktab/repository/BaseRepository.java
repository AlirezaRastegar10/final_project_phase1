package ir.maktab.repository;

import ir.maktab.configuration.SessionFactorySingleton;
import ir.maktab.entity.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public abstract class BaseRepository<E extends BaseEntity<Long>> {

    SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    String entityName;
    private final Class<E> type;

    public BaseRepository(String entityName, Class<E> type) {
        this.entityName = entityName;
        this.type = type;
    }

    public E create(E e) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                session.saveOrUpdate(e);
                transaction.commit();
                return e;
            } catch (Exception exception) {
                transaction.rollback();
                System.out.println(exception.getMessage());
                return null;
            }
        }
    }

    public E findById(Long id) {
        Session session = sessionFactory.openSession();
        E result = session.get(type, id);
        session.close();
        return result;
    }


    public List<E> findAll() {
        Session session = sessionFactory.openSession();
        Query<E> query = session.createQuery("select e from " + entityName + " e", type);
        List<E> list = query.list();
        session.close();
        return list;
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                E result = session.get(type, id);
                session.delete(result);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }
}

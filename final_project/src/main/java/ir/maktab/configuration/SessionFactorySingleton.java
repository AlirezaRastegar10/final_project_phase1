package ir.maktab.configuration;


import ir.maktab.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Expert.class)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Comments.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Duty.class)
                    .addAnnotatedClass(Offers.class)
                    .addAnnotatedClass(Orders.class)
                    .addAnnotatedClass(UnderDuty.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}

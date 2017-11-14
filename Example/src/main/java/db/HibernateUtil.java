
package db;

import db.Activity.DBActivity;
import db.Logs.DBLogs;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory object.
 * thx this code https://gist.github.com/yusufcakmak/215ede715bab0e1d6489
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("META-INF/hibernate.cfg.xml");
            configuration.addAnnotatedClass(DBLogs.class);
            configuration.addAnnotatedClass(DBActivity.class);

            System.out.println("Hibernate Configuration loaded");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            System.out.println("Hibernate serviceRegistry created");

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
           // sessionFactory.setAnnotatedClasses(new Class[] { Foo.class });
        } catch (Throwable ex) {
            // Log the exception.
            System.err.println("Exception stack Trace ************** begin");
            System.err.println("Hibernate : Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            System.err.println("Exception Stack Trace ********* END");
            throw new ExceptionInInitializerError(ex);


        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
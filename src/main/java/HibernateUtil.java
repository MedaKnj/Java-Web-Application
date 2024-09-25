import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/webshop?serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "G1u2r3g4u5s6o7v8a9c10");
                settings.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(entities.Buyer.class);
                configuration.addAnnotatedClass(entities.Product.class);
                configuration.addAnnotatedClass(entities.Account.class);
                configuration.addAnnotatedClass(entities.Sale.class);


                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(settings);
                sessionFactory = configuration.buildSessionFactory(builder.build());
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }


    public static void close() {
        sessionFactory.close();
    }
}

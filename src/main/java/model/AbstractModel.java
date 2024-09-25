package model;

import entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class AbstractModel<T> {
    private Class<T> entityClass;
    protected SessionFactory sessionFactory;
    protected Session session;

    public AbstractModel(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
        this.session = this.sessionFactory.openSession();
    }

    public List<T> findAll() {
        List<T> entities = (List<T>) session.createQuery("FROM " + entityClass.getName()).list();
        return (List<T>) entities;
    }

    // Ostale CRUD operacije...

    public void create(T entity) {
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(T entity) {
        try {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void edit(T entity) {
        try {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}


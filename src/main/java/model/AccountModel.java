package model;

import entities.Account;
import org.hibernate.query.Query;

public class AccountModel extends AbstractModel<Account> {

    public AccountModel() {
        super(Account.class);
    }

    public Account login(String username, String password) {

        try {
            if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
                sessionFactory.getCurrentSession().getTransaction().begin();
            }
            Query query = sessionFactory.getCurrentSession().createQuery("FROM Account acc WHERE acc.username=:username AND acc.password=:password");
            query.setParameter("username", username);
            query.setParameter("password", password);

            return (Account) query.uniqueResult();

        } catch (Exception e) {
            return null;
        }
    }
}

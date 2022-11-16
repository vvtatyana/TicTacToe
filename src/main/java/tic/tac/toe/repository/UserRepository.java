package tic.tac.toe.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tic.tac.toe.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<User> select() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("FROM User", User.class).getResultList();
        session.close();
        return users;
    }

    @Transactional
    public User select(String login) throws NullPointerException {
        Session session = sessionFactory.openSession();

        List<User> users =
                session.createQuery("FROM User WHERE login =: value", User.class)
                .setParameter("value", login).getResultList();

        session.close();

        if (users.isEmpty()) throw new NullPointerException("Not exist user");

        return users.get(0);
    }

    @Transactional
    public long insert(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println(user);
        long id = (long) session.save(user);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Transactional
    public void delete (long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(User.class, id));
        session.getTransaction().commit();
        session.close();
    }
}

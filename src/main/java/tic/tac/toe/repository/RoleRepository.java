package tic.tac.toe.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tic.tac.toe.entity.Role;

import java.util.List;

@Repository
public class RoleRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Role> select() {
        Session session = sessionFactory.openSession();
        List<Role> roles = session.createQuery("FROM Role", Role.class).getResultList();
        session.close();
        return roles;
    }

    public Role select(long id) {
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class, id);
        session.close();
        return role;
    }
}

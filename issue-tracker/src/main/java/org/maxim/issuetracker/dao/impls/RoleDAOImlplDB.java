package org.maxim.issuetracker.dao.impls;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.interfaces.RoleDAO;
import org.maxim.issuetracker.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImlplDB implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public RoleDAOImlplDB() { }

    public RoleDAOImlplDB(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(Role role) {
        currentSession().save(role);
    }

    @Override
    public void remove(int id) {
        Role role = (Role) currentSession().get(Role.class, id);
        if (role != null) {
            currentSession().delete(role);
        }
    }

    @Override
    public Role get(int id) {
        return (Role) currentSession().get(Role.class, id);
    }

    @Override
    public List<Role> list() {
        return currentSession().createQuery("from Role").list();
    }

}

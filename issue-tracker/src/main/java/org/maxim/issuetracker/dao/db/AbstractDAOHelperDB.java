package org.maxim.issuetracker.dao.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDAOHelperDB {

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDAOHelperDB() { }

    public AbstractDAOHelperDB(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

}

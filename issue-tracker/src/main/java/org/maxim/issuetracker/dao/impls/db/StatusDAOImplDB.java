package org.maxim.issuetracker.dao.impls.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.interfaces.StatusDAO;
import org.maxim.issuetracker.domain.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDAOImplDB extends AbstractDAOHelperDB implements StatusDAO {

    public StatusDAOImplDB() { }

    public StatusDAOImplDB(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Status status) {
        currentSession().save(status);
    }

    @Override
    public void delete(int id) {
        Status obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Status findById(int id) {
        return (Status) currentSession().get(Status.class, id);
    }

    @Override
    public List<Status> list() {
        return currentSession().createQuery("from Status").list();
    }

}
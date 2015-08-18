package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.StatusDAO;
import org.maxim.issuetracker.domain.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatusDAOImplDB extends AbstractDAOHelperDB implements StatusDAO {

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

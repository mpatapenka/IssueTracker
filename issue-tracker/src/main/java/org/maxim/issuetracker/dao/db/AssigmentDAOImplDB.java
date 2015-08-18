package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.AssigmentDAO;
import org.maxim.issuetracker.domain.Assigment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssigmentDAOImplDB extends AbstractDAOHelperDB implements AssigmentDAO {

    @Override
    public void save(Assigment assigment) {
        currentSession().save(assigment);
    }

    @Override
    public void delete(int id) {
        Assigment obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Assigment findById(int id) {
        return (Assigment) currentSession().get(Assigment.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Assigment> list() {
        return currentSession().createQuery("from Assigment").list();
    }

}

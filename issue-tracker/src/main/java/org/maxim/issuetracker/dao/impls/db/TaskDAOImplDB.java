package org.maxim.issuetracker.dao.impls.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.interfaces.TaskDAO;
import org.maxim.issuetracker.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImplDB extends AbstractDAOHelperDB implements TaskDAO {

    public TaskDAOImplDB() { }

    public TaskDAOImplDB(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Task task) {
        currentSession().save(task);
    }

    @Override
    public void delete(int id) {
        Task obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Task findById(int id) {
        return (Task) currentSession().get(Task.class, id);
    }

    @Override
    public List<Task> list() {
        return currentSession().createQuery("from Task").list();
    }

}

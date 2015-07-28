package org.maxim.issuetracker.dao.impls.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.interfaces.ActivityDAO;
import org.maxim.issuetracker.domain.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActivityDAOImplDB extends AbstractDAOHelperDB implements ActivityDAO {

    public ActivityDAOImplDB() { }

    public ActivityDAOImplDB(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Activity activity) {
        currentSession().save(activity);
    }

    @Override
    public void delete(int id) {
        Activity obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Activity findById(int id) {
        return (Activity) currentSession().get(Activity.class, id);
    }

    @Override
    public List<Activity> list() {
        return currentSession().createQuery("from Activity").list();
    }

}

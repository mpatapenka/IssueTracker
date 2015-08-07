package org.maxim.issuetracker.dao.impls.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.interfaces.ActivityDAO;
import org.maxim.issuetracker.domain.Activity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActivityDAOImplDB extends AbstractDAOHelperDB implements ActivityDAO {

    public ActivityDAOImplDB() {
    }

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

    @Override
    public List<Activity> listLast(int offset) {
        final int defaultBundleSize = 5;
        int startPos = defaultBundleSize * offset;
        int endPos = startPos + defaultBundleSize;

        List<Activity> activities = currentSession().createQuery("from Activity order by date desc").list();
        if (endPos > activities.size()) {
            endPos = activities.size();
        }
        if (startPos >= endPos) {
            return new ArrayList<>();
        }
        return activities.subList(startPos, endPos);
    }

}

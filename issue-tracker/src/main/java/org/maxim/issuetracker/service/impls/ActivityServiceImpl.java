package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.ActivityDAO;
import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    public ActivityServiceImpl() { }

    public ActivityServiceImpl(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    public void setActivityDAO(ActivityDAO activityDAO) {
        this.activityDAO = activityDAO;
    }

    @Override
    @Transactional
    public List<Activity> list() {
        return activityDAO.list();
    }

    @Override
    @Transactional
    public List<Activity> listLast(int offset) {
        return activityDAO.listLast(offset);
    }

}

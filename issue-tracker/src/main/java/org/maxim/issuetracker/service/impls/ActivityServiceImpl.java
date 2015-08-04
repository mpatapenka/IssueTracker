package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.interfaces.ActivityDAO;
import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.service.interfaces.ActivityService;
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

}

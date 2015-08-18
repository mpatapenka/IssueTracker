package org.maxim.issuetracker.service.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxim.issuetracker.dao.ActivityDAO;
import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityDAO activityDAO;

    @Override
    @Transactional
    public void add(Activity activity) {
        activityDAO.save(activity);
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

    @Override
    public String convertToJson(List<Activity> activities) {
        List<Object> activityData = new ArrayList<>();
        for (Activity activity : activities) {
            Employee employee = activity.getMember().getEmployee();
            Map<String, Object> activityInfo = new HashMap<>();

            activityInfo.put("name", employee.getFirstName() + " " + employee.getLastName());
            activityInfo.put("date", activity.getDate());
            activityInfo.put("comment", activity.getComment());
            activityInfo.put("duration", activity.getDuration());

            activityData.add(activityInfo);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(activityData);
        } catch (JsonProcessingException ignore) {
            return "";
        }
    }

}

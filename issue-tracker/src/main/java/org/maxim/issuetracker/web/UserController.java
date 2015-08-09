package org.maxim.issuetracker.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.ActivityService;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ActivityService activityService;

    @Secured(value = SecurityConstants.ROLE_USER)
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Employee user = employeeService.findByLogin(username);
        Set<Member> members = user.getMembers();
        List<Assigment> assignToMe = new ArrayList<>();
        for (Member member : members) {
            assignToMe.addAll(member.getAssigments());
        }
        int withoutOffset = 0;
        List<Activity> lastActivities = activityService.listLast(withoutOffset);

        model.addAttribute("userFullName", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("members", members);
        model.addAttribute("lastActivities", lastActivities);
        model.addAttribute("assignToMe", assignToMe);

        return "dashboard";
    }

    @Secured(value = SecurityConstants.ROLE_USER)
    @RequestMapping(value = "/dashboard/activity", method = RequestMethod.GET)
    public @ResponseBody String getActivities(@RequestParam int offset) {
        List<Activity> activities = activityService.listLast(offset);

        List<Object> activityData = new ArrayList<>();
        for (Activity activity : activities) {
            Employee employee = activity.getMember().getEmployee();
            Map<String, Object> activityInfo = new HashMap<>();

            activityInfo.put("name", employee.getFirstName() + " " + employee.getLastName());
            activityInfo.put("date", activity.getDate());
            activityInfo.put("comment", activity.getComment());

            activityData.add(activityInfo);
        }

        String jsonString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            jsonString = mapper.writeValueAsString(activityData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}

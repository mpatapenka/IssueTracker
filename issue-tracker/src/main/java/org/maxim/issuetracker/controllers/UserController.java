package org.maxim.issuetracker.controllers;

import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.interfaces.ActivityService;
import org.maxim.issuetracker.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
        List<Activity> lastActivities = activityService.list();

        model.addAttribute("userinfo", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("projects", user.getMembers());
        model.addAttribute("activities", lastActivities);

        return "dashboard";
    }

}

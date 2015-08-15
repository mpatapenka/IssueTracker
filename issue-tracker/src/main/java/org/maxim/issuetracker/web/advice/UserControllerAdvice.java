package org.maxim.issuetracker.web.advice;

import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.service.EmployeeService;
import org.maxim.issuetracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public void addUserDefaultInfoToModel(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee user = employeeService.findByLogin(username);
        if (user != null) {
            model.addAttribute("members", user.getMembers());
            model.addAttribute("user", user.getFirstName() + " " + user.getLastName());
        }
    }

    @ModelAttribute
    public void addFormData(Model model) {
        model.addAttribute("newAssign", new Assigment());
        model.addAttribute("allProjects", projectService.list());
    }

}

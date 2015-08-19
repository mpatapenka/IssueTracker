package org.maxim.issuetracker.web.advice;

import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.domain.Attachment;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.service.EmployeeService;
import org.maxim.issuetracker.service.ProjectService;
import org.maxim.issuetracker.web.constants.AttributeConstants;
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
            model.addAttribute(AttributeConstants.ATTR_MEMBERS, user.getMembers());
            model.addAttribute(AttributeConstants.ATTR_USER, user.getFirstName() + " " + user.getLastName());
            model.addAttribute(AttributeConstants.ATTR_USER_ID, user.getId());
        }
    }

    @ModelAttribute
    public void addFormData(Model model) {
        model.addAttribute(AttributeConstants.ATTR_NEW_ASSIGN, new Assigment());
        model.addAttribute(AttributeConstants.ATTR_NEW_REPORT, new Activity());
        model.addAttribute(AttributeConstants.ATTR_REASSIGN, new Assigment());
        model.addAttribute(AttributeConstants.ATTR_NEW_ATTACHMENT, new Attachment());
        model.addAttribute(AttributeConstants.ATTR_ALL_PROJECTS, projectService.list());
    }

}

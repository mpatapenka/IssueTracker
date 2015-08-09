package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Set;

@ControllerAdvice
public class UserControllerAdvice {

    @Autowired
    private EmployeeService employeeService;

    @Secured({SecurityConstants.ROLE_USER, SecurityConstants.ROLE_ADMIN})
    @ModelAttribute
    public void addUserDefaultInfoToModel(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee user = employeeService.findByLogin(username);
        if (user != null) {
            Set<Member> members = user.getMembers();

            model.addAttribute("members", members);
            model.addAttribute("userFullName", user.getFirstName() + " " + user.getLastName());
        }
    }

}

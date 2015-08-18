package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.domain.Task;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.EmployeeService;
import org.maxim.issuetracker.service.ProjectService;
import org.maxim.issuetracker.service.RoleService;
import org.maxim.issuetracker.web.constants.AttributeConstants;
import org.maxim.issuetracker.web.constants.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = MappingConstants.ROOT, method = RequestMethod.GET)
    public String showMain() {
        Collection auth = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities();

        boolean isUser = auth.contains(new SimpleGrantedAuthority(SecurityConstants.ROLE_USER));
        if (isUser) {
            return MappingConstants.REDIRECT + MappingConstants.DASHBOARD;
        }

        boolean isAdmin = auth.contains(new SimpleGrantedAuthority(SecurityConstants.ROLE_ADMIN));
        if (isAdmin) {
            return MappingConstants.REDIRECT + MappingConstants.ADMIN_PANEL;
        }

        return MappingConstants.PAGE_DASHBOARD;
    }

    @PreAuthorize(SecurityConstants.IS_ANONYMOUS)
    @RequestMapping(value = MappingConstants.LOGIN, method = RequestMethod.GET)
    public String showLoginPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute(AttributeConstants.ATTR_ERROR_MSG, "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute(AttributeConstants.ATTR_LOGOUT_MSG, "Logout successful.");
        }
        return MappingConstants.PAGE_LOGIN;
    }

    @PreAuthorize(SecurityConstants.IS_AUTHENTICATED)
    @RequestMapping(value = MappingConstants.PROJECTS, method = RequestMethod.GET, params = AttributeConstants.PARAM_ID)
    public String showProject(@RequestParam int id, Model model) {
        Project project = projectService.get(id);
        Set<Task> tasks = project.getTasks();
        List<Assigment> assigments = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getAssigments().isEmpty()) {
                assigments.add(task.getAssigments().iterator().next());
            }
        }

        model.addAttribute(AttributeConstants.ATTR_PROJECT, project);
        model.addAttribute(AttributeConstants.ATTR_ISSUES, assigments);
        model.addAttribute(AttributeConstants.ATTR_ADD_MEMBER, new Member());
        model.addAttribute(AttributeConstants.ATTR_EMPLOYEES, employeeService.listAllowed());
        model.addAttribute(AttributeConstants.ATTR_ROLES, roleService.list());

        return MappingConstants.PAGE_PROJECTS;
    }

}

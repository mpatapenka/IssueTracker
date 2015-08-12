package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.domain.Role;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.EmployeeService;
import org.maxim.issuetracker.service.MemberService;
import org.maxim.issuetracker.service.ProjectService;
import org.maxim.issuetracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String start() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee user = employeeService.findByLogin(username);
        if (user != null) {
            return "redirect:/user/dashboard";
        }
        return "dashboard";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Username or password is incorrect.");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logout successful.");
        }
        return "login";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = "new")
    public String showCreateProjectForm(Model model) {
        model.addAttribute(new Project());
        return "projectform";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = "/projects", method = RequestMethod.POST, params = "new")
    public String addNewProject(@Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "projectform";
        }
        projectService.add(project);
        return "redirect:/admin/panel";
    }

    @PreAuthorize(SecurityConstants.IS_AUTHENTICATED)
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = "id")
    public String showProject(@RequestParam int id, Model model) {
        Project project = projectService.get(id);
        model.addAttribute("project", project);
        model.addAttribute("addMember", new Member());
        model.addAttribute("employees", employeeService.list());
        model.addAttribute("roles", roleService.list());
        return "projects";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.POST, params = "id")
    public String addMember(@RequestParam int id, Member member) {
        Project project = projectService.get(id);
        Role role = roleService.get(member.getRole().getId());
        Employee employee = employeeService.get(member.getEmployee().getId());

        String errorMessage = "";
        if (project == null || role == null || employee == null) {
            errorMessage = "Check your input data. Some values was incorrect.";
        } else {
            member.setEmployee(employee);
            member.setRole(role);
            member.setProject(project);
            try {
                memberService.add(member);
            } catch (RuntimeException e) {
                errorMessage = e.getMessage();
            }
        }
        return errorMessage;
    }

}

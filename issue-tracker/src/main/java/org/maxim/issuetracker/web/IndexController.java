package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.EmployeeService;
import org.maxim.issuetracker.service.ProjectService;
import org.maxim.issuetracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class IndexController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showMain() {
        Collection<? extends GrantedAuthority> auth = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities();

        boolean isUser = auth.contains(new SimpleGrantedAuthority(SecurityConstants.ROLE_USER));
        boolean isAdmin = auth.contains(new SimpleGrantedAuthority(SecurityConstants.ROLE_ADMIN));
        if (isUser) {
            return "redirect:/dashboard";
        }
        if (isAdmin) {
            return "redirect:/admin-panel";
        }
        return "dashboard";
    }

    @PreAuthorize(SecurityConstants.IS_ANONYMOUS)
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

    @PreAuthorize(SecurityConstants.IS_AUTHENTICATED)
    @RequestMapping(value = "/projects", method = RequestMethod.GET, params = "id")
    public String showProject(@RequestParam int id, Model model) {
        Project project = projectService.get(id);
        model.addAttribute("project", project);
        model.addAttribute("addMember", new Member());
        model.addAttribute("employees", employeeService.listAllowed());
        model.addAttribute("roles", roleService.list());
        return "projects";
    }

}

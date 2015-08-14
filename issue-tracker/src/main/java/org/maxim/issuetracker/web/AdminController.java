package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.domain.Role;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MemberService memberService;

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = "/admin-panel", method = RequestMethod.GET)
    public String showAdminPage() {
        return "adminpage";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showUserRegisterPage(Model model) {
        model.addAttribute(new Employee());
        return "register";
    }

    @ModelAttribute
    public void setDefaultAttributesToModel(Model model) {
        model.addAttribute("positions", positionService.listAllowed());
        model.addAttribute("newProject", new Project());
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            employeeService.register(employee);
            return "redirect:/admin-panel";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @ResponseBody
    @RequestMapping(value = "/projects", method = RequestMethod.POST, params = "new")
    public String addNewProject(@Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                builder.append("Field '")
                        .append(error.getField())
                        .append("' has error: ")
                        .append(error.getDefaultMessage())
                        .append("\n");
            }
            return builder.toString();
        }
        projectService.add(project);
        return "";
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

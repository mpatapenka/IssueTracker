package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.domain.Role;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.*;
import org.maxim.issuetracker.web.constants.AttributeConstants;
import org.maxim.issuetracker.web.constants.MappingConstants;
import org.maxim.issuetracker.web.constants.MessageConstants;
import org.maxim.issuetracker.web.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    @RequestMapping(value = MappingConstants.ADMIN_PANEL, method = RequestMethod.GET)
    public String showAdminPage() {
        return MappingConstants.PAGE_ADMIN;
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = MappingConstants.REGISTER, method = RequestMethod.GET)
    public String showUserRegisterPage(Model model) {
        model.addAttribute(new Employee());
        return MappingConstants.PAGE_REGISTER;
    }

    @ModelAttribute
    public void setDefaultAttributesToModel(Model model) {
        model.addAttribute(AttributeConstants.ATTR_POSITIONS, positionService.listAllowed());
        model.addAttribute(AttributeConstants.ATTR_NEW_PROJECT, new Project());
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @RequestMapping(value = MappingConstants.REGISTER, method = RequestMethod.POST)
    public String registerNewUser(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return MappingConstants.PAGE_REGISTER;
        }
        try {
            employeeService.register(employee);
            return MappingConstants.REDIRECT + MappingConstants.ADMIN_PANEL;
        } catch (RuntimeException e) {
            model.addAttribute(AttributeConstants.ATTR_ERROR_MSG, e.getMessage());
            return MappingConstants.PAGE_REGISTER;
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @ResponseBody
    @RequestMapping(value = MappingConstants.PROJECTS, method = RequestMethod.POST, params = AttributeConstants.PARAM_NEW)
    public String addNewProject(@Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return MessageUtils.getMessageFromBindingResult(bindingResult);
        }
        projectService.add(project);
        return AttributeConstants.SUCCESS_RESPONSE_BODY;
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_ADMIN)
    @ResponseBody
    @RequestMapping(value = MappingConstants.PROJECTS, method = RequestMethod.POST, params = AttributeConstants.PARAM_ID)
    public String addMember(@RequestParam int id, Member member) {
        Project project = projectService.get(id);
        Role role = roleService.get(member.getRole().getId());
        Employee employee = employeeService.get(member.getEmployee().getId());

        String responseResult = AttributeConstants.SUCCESS_RESPONSE_BODY;
        if (project == null || role == null || employee == null) {
            responseResult = MessageConstants.INVALID_FORM_DATA;
        } else {
            member.setEmployee(employee);
            member.setRole(role);
            member.setProject(project);
            try {
                memberService.add(member);
            } catch (RuntimeException e) {
                responseResult = e.getMessage();
            }
        }
        return responseResult;
    }

}

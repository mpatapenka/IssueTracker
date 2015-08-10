package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.EmployeeService;
import org.maxim.issuetracker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    @Secured(SecurityConstants.ROLE_ADMIN)
    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String showAdmin(Model model) {
        return "adminpage";
    }

    @Secured(SecurityConstants.ROLE_ADMIN)
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        model.addAttribute(new Employee());
        return "register";
    }

    @ModelAttribute("positions")
    public List<Position> populatePositions() {
        return positionService.list();
    }

    @Secured(SecurityConstants.ROLE_ADMIN)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        boolean result;
        try {
            result = employeeService.register(employee);
        } catch (Exception ignore) {
            result = false;
        }
        if (!result) {
            model.addAttribute("errorMessage", "User with username '" + employee.getLogin() + "' already exist.");
            return "register";
        }
        return "redirect:/admin/panel";
    }

}

package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private EmployeeService employeeService;

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

}

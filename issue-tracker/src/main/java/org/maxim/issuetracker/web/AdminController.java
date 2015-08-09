package org.maxim.issuetracker.web;

import org.maxim.issuetracker.security.SecurityConstants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Secured(SecurityConstants.ROLE_ADMIN)
    @RequestMapping(value = "/panel", method = RequestMethod.GET)
    public String showAdmin(Model model) {
        return "adminpage";
    }

}

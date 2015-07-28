package org.maxim.issuetracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Maxim on 27.07.2015.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/start"})
    public String showStartPage(Map<String, Object> model) {
        return "login";
    }

}

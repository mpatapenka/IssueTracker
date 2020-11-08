package org.mpatapenka.issuetracker.api.web;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Hidden
@Controller
public class ApiController {

    @GetMapping({"/", "/swagger", "/swagger-ui"})
    public String getApiUi() {
        return "redirect:/swagger-ui/index.html";
    }
}
package org.mpatapenka.issuetracker.api.web;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import static org.mpatapenka.issuetracker.api.web.WebConstants.API_V1;

@Hidden
@Controller
public class ApiController {

  @Value("${springdoc.swagger-ui.path}")
  private String swaggerUiPath;


  @GetMapping({API_V1, API_V1 + "/swagger", API_V1 + "/swagger-ui"})
  public String redirectToApiV1Ui() {
    return UrlBasedViewResolver.REDIRECT_URL_PREFIX + swaggerUiPath;
  }
}
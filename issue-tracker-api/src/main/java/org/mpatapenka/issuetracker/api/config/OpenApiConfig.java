package org.mpatapenka.issuetracker.api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class OpenApiConfig {

  private static final String AUTHOR_NAME_BUILD_PROPERTY_NAME = "author.name";
  private static final String AUTHOR_EMAIL_BUILD_PROPERTY_NAME = "author.email";

  private final IssueTrackerApiProperties apiProperties;
  private final BuildProperties buildProperties;


  @Bean
  public OpenAPI issueTrackerOpenApi() {
    return new OpenAPI()
        .info(buildInfo())
        .externalDocs(buildExternalDocumentation());
  }


  private Info buildInfo() {
    return new Info()
        .title(apiProperties.getTitle())
        .description(apiProperties.getDescription())
        .version(buildProperties.getVersion())
        .contact(buildContact());
  }

  private Contact buildContact() {
    return new Contact()
        .name(buildProperties.get(AUTHOR_NAME_BUILD_PROPERTY_NAME))
        .email(buildProperties.get(AUTHOR_EMAIL_BUILD_PROPERTY_NAME));
  }

  private ExternalDocumentation buildExternalDocumentation() {
    return new ExternalDocumentation()
        .description(apiProperties.getExternalDocs().getDescription())
        .url(apiProperties.getExternalDocs().getUrl());
  }
}
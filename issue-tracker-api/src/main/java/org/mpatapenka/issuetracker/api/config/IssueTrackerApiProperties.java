package org.mpatapenka.issuetracker.api.config;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("issue-tracker.api")
@ConstructorBinding
@Getter
@Builder
@RequiredArgsConstructor
public final class IssueTrackerApiProperties {

  /**
   * API major version (v1, v2, v3, etc)
   */
  private final String version;

  /**
   * API/Application title
   */
  private final String title;

  /**
   * API/Application description
   */
  private final String description;

  @NestedConfigurationProperty
  private final ExternalDocs externalDocs;


  /**
   * External documentation definition
   */
  @ConstructorBinding
  @Getter
  @Builder
  @RequiredArgsConstructor
  public static class ExternalDocs {

    /**
     * External documentation description
     */
    private final String description;

    /**
     * External documentation location (link)
     */
    private final String url;
  }
}
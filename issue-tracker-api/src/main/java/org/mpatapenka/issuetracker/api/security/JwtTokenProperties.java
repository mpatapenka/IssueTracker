package org.mpatapenka.issuetracker.api.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.UUID;

@ConfigurationProperties("issue-tracker.auth-jwt-token")
@Getter
@Setter
public final class JwtTokenProperties {

    /**
     * Http header where token is located
     */
    @NotEmpty
    private String httpHeader = "Authorization";

    /**
     * Token validity period
     */
    @NotNull
    private Duration validity = Duration.ofMinutes(15);

    /**
     * Token secret, to be used for encryption/decryption
     */
    @NotEmpty
    private String secret = UUID.randomUUID().toString();

    @NestedConfigurationProperty
    private Payload payload = new Payload();


    @Getter
    @Setter
    public static class Payload {

        /**
         * Claim key name where authorities are stored
         */
        @NotEmpty
        private String authoritiesKey = "auth";
    }
}
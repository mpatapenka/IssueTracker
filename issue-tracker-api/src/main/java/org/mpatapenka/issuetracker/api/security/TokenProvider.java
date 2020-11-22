package org.mpatapenka.issuetracker.api.security;

import org.springframework.security.core.Authentication;

public interface TokenProvider {

    String createToken(Authentication authentication);

    boolean isValidToken(String token);

    Authentication getAuthentication(String token);
}
package org.mpatapenka.issuetracker.api.security;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final JwtTokenProperties tokenProperties;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (securityContext.getAuthentication() == null) {
            Optional.ofNullable(getJwtToken(request))
                    .filter(tokenProvider::isValidToken)
                    .map(tokenProvider::getAuthentication)
                    .map(auth -> enrichWithRequestDetails(auth, request))
                    .ifPresent(securityContext::setAuthentication);
        }

        chain.doFilter(request, response);
    }

    private String getJwtToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(tokenProperties.getHttpHeader()))
                .filter(StringUtils::isNotEmpty)
                .filter(token -> token.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private Authentication enrichWithRequestDetails(Authentication authentication, HttpServletRequest request) {
        if (authentication instanceof AbstractAuthenticationToken) {
            AbstractAuthenticationToken tokenAuth = (AbstractAuthenticationToken) authentication;
            tokenAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        }
        return authentication;
    }
}
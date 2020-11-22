package org.mpatapenka.issuetracker.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mpatapenka.issuetracker.api.service.ClockProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider implements TokenProvider {

    private static final String AUTHORITIES_SEPARATOR = ",";


    private final ClockProvider clockProvider;
    private final JwtTokenProperties tokenProperties;


    @Override
    public String createToken(@NonNull Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(tokenProperties.getPayload().getAuthoritiesKey(), packAuthorities(authentication.getAuthorities()));
        return generateToken(authentication.getName(), claims);
    }

    private String generateToken(String subject, Map<String, Object> claims) {
        Instant issuedAt = Instant.now(clockProvider.getClock());
        Instant expiration = issuedAt.plus(tokenProperties.getValidity());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, tokenProperties.getSecret())
                .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            return StringUtils.isNotEmpty(token) && parseToken(token) != null;
        } catch (JwtException e) {
            log.warn("Token '{}' is invalid, reason:", token, e);
        }
        return false;
    }

    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = parseToken(token);

        String username = claims.getSubject();
        String credentials = StringUtils.EMPTY;
        Collection<? extends GrantedAuthority> authorities =
                unPackAuthorities(claims.get(tokenProperties.getPayload().getAuthoritiesKey(), String.class));

        return new UsernamePasswordAuthenticationToken(
                new User(username, credentials, authorities),
                credentials,
                authorities);
    }

    private String packAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .filter(Objects::nonNull)
                .map(GrantedAuthority::getAuthority)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.joining(AUTHORITIES_SEPARATOR));
    }

    private Collection<? extends GrantedAuthority> unPackAuthorities(String authoritiesAsStringList) {
        return Arrays.stream(authoritiesAsStringList.split(AUTHORITIES_SEPARATOR))
                .filter(StringUtils::isNotEmpty)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(tokenProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }
}
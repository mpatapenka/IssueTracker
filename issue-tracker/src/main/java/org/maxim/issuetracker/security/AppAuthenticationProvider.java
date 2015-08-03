package org.maxim.issuetracker.security;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Employee user = employeeService.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username '" + username + "' not found.");
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Username or password is incorrect.");
        }

        Position userPosition = user.getPosition();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(extractServerRole(userPosition)));
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> authClass) {
        return authClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    private String extractServerRole(final Position position) {
        if ("Admin".equals(position.getName())) {
            return SecurityConstants.ROLE_ADMIN;
        }
        return SecurityConstants.ROLE_USER;
    }

}

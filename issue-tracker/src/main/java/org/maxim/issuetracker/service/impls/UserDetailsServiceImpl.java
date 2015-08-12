package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeService.findByLogin(username);

        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        if (user != null) {
            grantedAuths.addAll(extractServerRole(user.getPosition()));
            return new User(user.getLogin(), user.getPassword(), grantedAuths);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found.");
    }

    private List<GrantedAuthority> extractServerRole(final Position position) {
        List<GrantedAuthority> auth = new ArrayList<>();
        if (SecurityConstants.ADMIN_POSITION.equals(position.getName())) {
            auth.add(new SimpleGrantedAuthority(SecurityConstants.ROLE_ADMIN));
        } else {
            auth.add(new SimpleGrantedAuthority(SecurityConstants.ROLE_USER));
        }
        return auth;
    }

}

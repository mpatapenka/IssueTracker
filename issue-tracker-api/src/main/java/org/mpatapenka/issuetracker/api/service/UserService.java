package org.mpatapenka.issuetracker.api.service;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.issuetracker.api.entity.Employee;
import org.mpatapenka.issuetracker.api.entity.ProjectMembership;
import org.mpatapenka.issuetracker.api.repository.EmployeeRepository;
import org.mpatapenka.issuetracker.api.repository.ProjectMembershipRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final ProjectMembershipRepository projectMembershipRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username '%s' doesn't exist", username)));
        Collection<GrantedAuthority> authorities = projectMembershipRepository.findByEmployeeId(employee.getId()).stream()
                .map(this::convertMembershipToComplexProjectRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return new User(employee.getUsername(), employee.getPassword(), authorities);
    }

    private String convertMembershipToComplexProjectRole(ProjectMembership membership) {
        Integer projectId = membership.getProject().getId();
        String projectRole = membership.getRole().getName();
        return "ROLE_" + projectId + "_" + projectRole;
    }
}
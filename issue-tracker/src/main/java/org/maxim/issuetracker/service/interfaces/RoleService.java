package org.maxim.issuetracker.service.interfaces;

import org.maxim.issuetracker.domain.Role;

import java.util.List;

public interface RoleService {

    void add(Role role);

    void remove(int id);

    Role get(int id);

    List<Role> list();

}
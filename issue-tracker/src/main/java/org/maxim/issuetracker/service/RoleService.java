package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Role;

import java.util.List;

public interface RoleService {

    Role get(int id);

    List<Role> list();

}

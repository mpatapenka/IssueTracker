package org.maxim.issuetracker.dao;

import org.maxim.issuetracker.domain.Role;

import java.util.List;

public interface RoleDAO {

    void save(Role role);

    void delete(int id);

    Role findById(int id);

    List<Role> list();

}

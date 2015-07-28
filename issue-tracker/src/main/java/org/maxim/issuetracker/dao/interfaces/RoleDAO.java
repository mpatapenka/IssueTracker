package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Role;

import java.util.List;

public interface RoleDAO {

    void add(Role role);

    void remove(int id);

    Role get(int id);

    List<Role> list();

}

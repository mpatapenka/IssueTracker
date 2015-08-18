package org.maxim.issuetracker.dao.db;

import org.maxim.issuetracker.dao.RoleDAO;
import org.maxim.issuetracker.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImlplDB extends AbstractDAOHelperDB implements RoleDAO {

    @Override
    public void save(Role role) {
        currentSession().save(role);
    }

    @Override
    public void delete(int id) {
        Role obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Role findById(int id) {
        return (Role) currentSession().get(Role.class, id);
    }

    @Override
    public List<Role> list() {
        return currentSession().createQuery("from Role").list();
    }

}

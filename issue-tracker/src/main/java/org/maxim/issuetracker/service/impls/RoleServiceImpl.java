package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.RoleDAO;
import org.maxim.issuetracker.domain.Role;
import org.maxim.issuetracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    @Transactional
    public Role get(int id) {
        return roleDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Role> list() {
        return roleDAO.list();
    }

}

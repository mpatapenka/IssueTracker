package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.interfaces.RoleDAO;
import org.maxim.issuetracker.domain.Role;
import org.maxim.issuetracker.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO dao;

    public RoleServiceImpl() { }

    public RoleServiceImpl(RoleDAO dao) {
        this.dao = dao;
    }

    public void setDao(RoleDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public void add(Role role) {
        dao.save(role);
    }

    @Override
    @Transactional
    public void remove(int id) {
        dao.delete(id);
    }

    @Override
    @Transactional
    public Role get(int id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public List<Role> list() {
        return dao.list();
    }

}

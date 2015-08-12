package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.EmployeeDAO;
import org.maxim.issuetracker.dao.PositionDAO;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private PositionDAO positionDAO;

    @Override
    @Transactional
    public synchronized void register(Employee employee) {
        Employee check = findByLogin(employee.getLogin());
        if (check != null) {
            throw new IllegalArgumentException("User with username '" + employee.getLogin() + "' already exist.");
        }
        Position position = positionDAO.findById(employee.getPosition().getId());
        employee.setPosition(position);
        employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee get(int id) {
        return employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee findByLogin(String login) {
        return employeeDAO.findByLogin(login);
    }

    @Override
    @Transactional
    public List<Employee> list() {
        return employeeDAO.list();
    }

}

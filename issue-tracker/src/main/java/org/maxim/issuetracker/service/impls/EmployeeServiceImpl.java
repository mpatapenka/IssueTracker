package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.EmployeeDAO;
import org.maxim.issuetracker.dao.PositionDAO;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Position;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private PositionDAO positionDAO;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean register(Employee employee) {
        Employee check = findByLogin(employee.getLogin());
        if (check != null) {
            return false;
        }
        Position position = positionDAO.findById(employee.getPosition().getId());
        employee.setPosition(position);
        employeeDAO.save(employee);
        return true;
    }

    @Override
    @Transactional
    public Employee findByLogin(String login) {
        return employeeDAO.findByLogin(login);
    }

}

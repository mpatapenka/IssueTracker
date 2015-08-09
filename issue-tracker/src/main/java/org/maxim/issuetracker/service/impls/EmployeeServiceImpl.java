package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.EmployeeDAO;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() { }

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    @Transactional
    public Employee findByLogin(String login) {
        return employeeDAO.findByLogin(login);
    }

}

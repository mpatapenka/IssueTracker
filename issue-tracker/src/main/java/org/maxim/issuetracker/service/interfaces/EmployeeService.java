package org.maxim.issuetracker.service.interfaces;

import org.maxim.issuetracker.domain.Employee;

public interface EmployeeService {

    void add(Employee employee);

    Employee findByLogin(String login);

}

package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Employee;

public interface EmployeeService {

    void add(Employee employee);

    Employee findByLogin(String login);

}

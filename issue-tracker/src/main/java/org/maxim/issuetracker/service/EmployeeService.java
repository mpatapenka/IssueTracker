package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Employee;

public interface EmployeeService {

    boolean register(Employee employee);

    Employee findByLogin(String login);

}

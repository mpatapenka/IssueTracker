package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Employee;

import java.util.List;

public interface EmployeeService {

    void register(Employee employee);

    Employee get(int id);

    Employee findByLogin(String login);

    List<Employee> list();

}

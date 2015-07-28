package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Employee;

import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);

    void delete(int id);

    Employee findById(int id);

    List<Employee> list();

}

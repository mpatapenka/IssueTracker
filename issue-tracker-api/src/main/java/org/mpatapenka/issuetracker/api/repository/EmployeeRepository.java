package org.mpatapenka.issuetracker.api.repository;

import org.mpatapenka.issuetracker.api.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    Optional<Employee> findByUsername(String username);
}
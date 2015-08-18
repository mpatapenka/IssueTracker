package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.domain.Employee;

public interface UserService {

    Employee findEmployeeByUsername(String username);

    void addIssue(Assigment assigment);

    void reportIssue(int assigmentId, String username, Activity activity);

}

package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Assigment;

public interface AssigmentService {

    void add(Assigment assigment);

    Assigment get(int id);

}

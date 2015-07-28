package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Assigment;

import java.util.List;

public interface AssigmentDAO {

    void save(Assigment assigment);

    void delete(int id);

    Assigment findById(int id);

    List<Assigment> list();

}

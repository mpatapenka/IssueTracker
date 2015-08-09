package org.maxim.issuetracker.dao;

import org.maxim.issuetracker.domain.Status;

import java.util.List;

public interface StatusDAO {

    void save(Status status);

    void delete(int id);

    Status findById(int id);

    List<Status> list();

}

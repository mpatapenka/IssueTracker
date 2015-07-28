package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Status;

import java.util.List;

/**
 * Created by Maxim on 23.07.2015.
 */
public interface StatusDAO {

    void add(Status status);

    void remove(long id);

    Status get(long id);

    List<Status> list();

}

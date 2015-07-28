package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Task;

import java.util.List;

public interface TaskDAO {

    void save(Task task);

    void delete(int id);

    Task findById(int id);

    List<Task> list();

}

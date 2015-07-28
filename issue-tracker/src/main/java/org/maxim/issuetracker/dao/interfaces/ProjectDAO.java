package org.maxim.issuetracker.dao.interfaces;

import org.maxim.issuetracker.domain.Project;

import java.util.List;

public interface ProjectDAO {

    void save(Project project);

    void delete(int id);

    Project findById(int id);

    List<Project> list();

}

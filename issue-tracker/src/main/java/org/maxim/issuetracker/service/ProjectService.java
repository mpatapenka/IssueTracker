package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Project;

import java.util.List;

public interface ProjectService {

    Project get(int id);

    void add(Project project);

    List<Project> list();

    String getProjectMembersWithJson(Project project);

}

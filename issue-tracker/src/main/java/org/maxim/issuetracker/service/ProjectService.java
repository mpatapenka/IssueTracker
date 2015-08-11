package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Project;

public interface ProjectService {

    Project get(int id);

    void add(Project project);

}

package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.ProjectDAO;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    @Transactional
    public Project get(int id) {
        return projectDAO.findById(id);
    }

    @Override
    @Transactional
    public void add(Project project) {
        projectDAO.save(project);
    }

}

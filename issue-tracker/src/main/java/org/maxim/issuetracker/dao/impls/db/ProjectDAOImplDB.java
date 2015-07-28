package org.maxim.issuetracker.dao.impls.db;

import org.hibernate.SessionFactory;
import org.maxim.issuetracker.dao.interfaces.ProjectDAO;
import org.maxim.issuetracker.domain.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDAOImplDB extends AbstractDAOHelperDB implements ProjectDAO {

    public ProjectDAOImplDB() { }

    public ProjectDAOImplDB(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void save(Project project) {
        currentSession().save(project);
    }

    @Override
    public void delete(int id) {
        Project obj = findById(id);
        if (obj != null) {
            currentSession().delete(obj);
        }
    }

    @Override
    public Project findById(int id) {
        return (Project) currentSession().get(Project.class, id);
    }

    @Override
    public List<Project> list() {
        return currentSession().createQuery("from Project").list();
    }

}

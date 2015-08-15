package org.maxim.issuetracker.service.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxim.issuetracker.dao.ProjectDAO;
import org.maxim.issuetracker.domain.Employee;
import org.maxim.issuetracker.domain.Member;
import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    @Transactional
    public List<Project> list() {
        return projectDAO.list();
    }

    @Override
    public String getProjectMembersWithJson(Project project) {
        if (project == null) {
            return "";
        }

        Set<Member> members = project.getMembers();

        List<Object> employeeData = new ArrayList<>();
        for (Member member : members) {
            Employee employee = member.getEmployee();
            Map<String, Object> employeeInfo = new HashMap<>();

            employeeInfo.put("name", employee.getFirstName() + " " + employee.getLastName());
            employeeInfo.put("id", member.getId());

            employeeData.add(employeeInfo);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(employeeData);
        } catch (JsonProcessingException ignore) {
            return "";
        }
    }

}

package org.maxim.issuetracker.service.impls;

import org.maxim.issuetracker.dao.*;
import org.maxim.issuetracker.domain.*;
import org.maxim.issuetracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AssigmentDAO assigmentDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private ActivityDAO activityDAO;

    @Override
    @Transactional
    public Employee findEmployeeByUsername(String username) {
        return employeeDAO.findByLogin(username);
    }

    @Override
    @Transactional
    public void addIssue(Assigment assigment) {
        int memberId = assigment.getMember().getId();
        int projectId = assigment.getMember().getProject().getId();

        Project project = projectDAO.findById(projectId);
        if (project == null) {
            throw new IllegalArgumentException("You didn't choose a project.");
        }

        final int initialStatusPos = 0;
        Status initialStatus = statusDAO.list().get(initialStatusPos);

        Task task = assigment.getTask();
        task.setProject(project);
        task.setStatus(initialStatus);

        Member member = memberDAO.findById(memberId);
        assigment.setMember(member);

        assigmentDAO.save(assigment);
    }

    @Override
    @Transactional
    public void reportIssue(int assigmentId, String username, Activity activity) {
        Assigment assigment = assigmentDAO.findById(assigmentId);
        if (assigment == null) {
            throw new IllegalArgumentException("Your request was modified before send.");
        }

        Employee user = employeeDAO.findByLogin(username);
        Project project = assigment.getTask().getProject();

        Set<Member> userMembers = new HashSet<>(user.getMembers());
        Set<Member> projectMembers = new HashSet<>(project.getMembers());
        userMembers.retainAll(projectMembers);

        if (userMembers.isEmpty()) {
            throw new SecurityException("Sorry, you are not a member of the current project.");
        }

        Member member = userMembers.iterator().next();

        activity.setMember(member);
        activity.setAssigment(assigment);
        activity.setDate(new Date(Calendar.getInstance().getTime().getTime()));

        activityDAO.save(activity);
    }

    @Override
    @Transactional
    public void reassignIssue(Assigment assigment) {
        Assigment origin = assigmentDAO.findById(assigment.getId());
        Member assignee = memberDAO.findById(assigment.getMember().getId());
        if (assignee == null) {
            throw new IllegalArgumentException("Sorry, you didn't select a member of the project.");
        }

        origin.setDescription(assigment.getDescription());
        origin.setMember(assignee);
    }

}

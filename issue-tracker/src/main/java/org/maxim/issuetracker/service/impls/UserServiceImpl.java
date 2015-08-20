package org.maxim.issuetracker.service.impls;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.maxim.issuetracker.dao.*;
import org.maxim.issuetracker.domain.*;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.UserService;
import org.maxim.issuetracker.web.constants.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
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

    @Autowired
    private AttachmentDAO attachmentDAO;

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
            throw new IllegalArgumentException(MessageConstants.PROJECT_NOT_CHOSEN);
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
            throw new IllegalArgumentException(MessageConstants.REQUEST_WAS_MODIFIED);
        }

        Employee user = employeeDAO.findByLogin(username);
        Project project = assigment.getTask().getProject();

        Set<Member> userMembers = new HashSet<>(user.getMembers());
        Set<Member> projectMembers = new HashSet<>(project.getMembers());
        userMembers.retainAll(projectMembers);

        if (userMembers.isEmpty()) {
            throw new SecurityException(MessageConstants.USER_NOT_PROJECT_MEMBER);
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
            throw new IllegalArgumentException(MessageConstants.MEMBER_NOT_CHOSEN);
        }

        origin.setDescription(assigment.getDescription());
        origin.setMember(assignee);
    }

    @Override
    @Transactional
    public void transferIssue(int assigmentId, String username, String statusParam) {
        Employee employee = employeeDAO.findByLogin(username);
        Assigment assigment = assigmentDAO.findById(assigmentId);
        List<Status> statuses = statusDAO.list();

        if (statuses.indexOf(assigment.getTask().getStatus()) == statuses.size() - 1) {
            return;
        }

        String currentPos = employee.getPosition().getName();
        boolean isLead = SecurityConstants.TEAM_LEAD_POSITION.equals(currentPos)
                || SecurityConstants.PROJECT_MANAGER_POSITION.equals(currentPos);

        boolean isAssign = employee.getMembers().contains(assigment.getMember());

        Set<Member> userMembers = new HashSet<>(employee.getMembers());
        Set<Member> projectMembers = new HashSet<>(assigment.getTask().getProject().getMembers());
        userMembers.retainAll(projectMembers);
        boolean isProjectMember = !userMembers.isEmpty();

        final int startProgress = 1;
        final int delivery = 2;
        final int done = 3;

        Task task = assigment.getTask();

        int prevPos = statuses.indexOf(task.getStatus());

        switch (statusParam) {
            case "start-progress":
                if (isAssign && prevPos == startProgress - 1) {
                    task.setStatus(statuses.get(startProgress));
                    task.setActionStartDate(new Date(Calendar.getInstance().getTime().getTime()));
                }
                break;
            case "delivery":
                if (isAssign && prevPos == delivery - 1) {
                    task.setStatus(statuses.get(delivery));
                }
                break;
            case "done":
                if (isProjectMember && isLead && prevPos == done - 1) {
                    task.setStatus(statuses.get(done));
                    task.setActionEndDate(new Date(Calendar.getInstance().getTime().getTime()));
                }
                break;
            case "reject":
                if (isProjectMember && isLead && prevPos == delivery) {
                    task.setStatus(statuses.get(startProgress));
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported option '" + statusParam + "'.");
        }
    }

    @Override
    @Transactional
    public void attachFile(int assigmentId, Attachment attachment, MultipartFile file) {
        Assigment assigment = assigmentDAO.findById(assigmentId);

        String filename = file.getOriginalFilename();
        String sysFilename = Calendar.getInstance().getTime().getTime() + filename;

        attachment.setTask(assigment.getTask());
        attachment.setProject(assigment.getTask().getProject());
        attachment.setName(filename);
        attachment.setSize(file.getSize());
        attachment.setFileSystemName(sysFilename);

        attachmentDAO.save(attachment);

        try {
            File saveFile = new File(EnvironmentConstants.FILES_PATH + sysFilename);
            FileUtils.writeByteArrayToFile(saveFile, file.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(MessageConstants.CANT_UPLOAD_FILE, e);
        }
    }

    @Override
    @Transactional
    public void downloadFile(int attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentDAO.findById(attachmentId);
        File file = new File(EnvironmentConstants.FILES_PATH + attachment.getFileSystemName());

        response.setContentType(EnvironmentConstants.RESPONSE_CONTENT_TYPE);
        response.setHeader(EnvironmentConstants.RESPONSE_HEADER_NAME,
                EnvironmentConstants.RESPONSE_HEADER_SUFFIX + attachment.getName() + "\"");

        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            IOUtils.copy(is, os);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

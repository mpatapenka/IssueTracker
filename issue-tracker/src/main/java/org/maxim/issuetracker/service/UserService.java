package org.maxim.issuetracker.service;

import org.maxim.issuetracker.domain.Activity;
import org.maxim.issuetracker.domain.Assigment;
import org.maxim.issuetracker.domain.Attachment;
import org.maxim.issuetracker.domain.Employee;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    Employee findEmployeeByUsername(String username);

    void addIssue(Assigment assigment);

    void reportIssue(int assigmentId, String username, Activity activity);

    void reassignIssue(Assigment assigment);

    void transferIssue(int assigmentId, String username, String statusParam);

    void attachFile(int assigmentId, Attachment attachment, MultipartFile file);

    void downloadFile(int attachmentId, HttpServletResponse response);

}

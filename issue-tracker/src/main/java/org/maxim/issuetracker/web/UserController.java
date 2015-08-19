package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.*;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.*;
import org.maxim.issuetracker.web.constants.AttributeConstants;
import org.maxim.issuetracker.web.constants.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AssigmentService assigmentService;

    @Autowired
    private UserService userService;

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @RequestMapping(value = MappingConstants.DASHBOARD, method = RequestMethod.GET)
    public String showDashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee user = userService.findEmployeeByUsername(username);

        Set<Member> members = user.getMembers();
        List<Assigment> assignToMe = new ArrayList<>();
        for (Member member : members) {
            assignToMe.addAll(member.getAssigments());
        }
        final int withoutOffset = 0;
        List<Activity> lastActivities = activityService.listLast(withoutOffset);

        model.addAttribute(AttributeConstants.ATTR_LAST_ACTIVITIES, lastActivities);
        model.addAttribute(AttributeConstants.ATTR_ASSIGN_TO_ME, assignToMe);

        return MappingConstants.PAGE_DASHBOARD;
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = MappingConstants.DASHBOARD + MappingConstants.ACTIVITY, method = RequestMethod.GET)
    public String getActivities(@RequestParam int offset) {
        List<Activity> activities = activityService.listLast(offset);
        return activityService.convertToJson(activities);
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_LEAD)
    @ResponseBody
    @RequestMapping(value = MappingConstants.EMPLOYEES, method = RequestMethod.POST, params = AttributeConstants.PARAM_PROJECT)
    public String getProjectEmployees(@RequestParam int project) {
        Project projectObj = projectService.get(project);
        return projectService.getProjectMembersWithJson(projectObj);
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_LEAD)
    @ResponseBody
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.POST, params = AttributeConstants.PARAM_CREATE)
    public String createIssue(@Valid Assigment assigment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Check your input data and try again.";
        }
        try {
            userService.addIssue(assigment);
            return AttributeConstants.SUCCESS_RESPONSE_BODY;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.GET, params = AttributeConstants.PARAM_ID)
    public String showIssue(@RequestParam int id, Model model) {
        model.addAttribute(AttributeConstants.ATTR_ASSIGN, assigmentService.get(id));
        return MappingConstants.PAGE_ISSUES;
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.GET,
            params = {AttributeConstants.PARAM_ID, AttributeConstants.PARAM_EXPORT})
    public Assigment exportIssueToXml(@RequestParam int id) {
        return assigmentService.get(id);
    }

    @PreAuthorize(SecurityConstants.IS_AUTHENTICATED)
    @ResponseBody
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.POST,
            params = {AttributeConstants.PARAM_ID, AttributeConstants.PARAM_REPORT})
    public String reportIssue(@RequestParam int id, @Valid Activity activity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Check your input data and try again.";
        }
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            userService.reportIssue(id, username, activity);
            return AttributeConstants.SUCCESS_RESPONSE_BODY;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_LEAD)
    @ResponseBody
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.POST,
            params = {AttributeConstants.PARAM_ID, AttributeConstants.PARAM_REASSIGN})
    public String reassignToIssue(Assigment assigment) {
        try {
            userService.reassignIssue(assigment);
            return AttributeConstants.SUCCESS_RESPONSE_BODY;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.POST,
            params = {AttributeConstants.PARAM_ID, AttributeConstants.PARAM_TO})
    public String issueStartProgress(@RequestParam int id, @RequestParam String to) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            userService.transferIssue(id, username, to);
            return AttributeConstants.SUCCESS_RESPONSE_BODY;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.POST,
            params = {AttributeConstants.PARAM_ID, AttributeConstants.PARAM_ATTACH})
    public String uploadFile(@RequestParam int id, Attachment attachment,
                             @RequestParam(value = AttributeConstants.ATTR_FILE, required = false) MultipartFile file) {
        try {
            userService.attachFile(id, attachment, file);
            return AttributeConstants.SUCCESS_RESPONSE_BODY;
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @RequestMapping(value = MappingConstants.ISSUES, method = RequestMethod.GET,
            params = {AttributeConstants.PARAM_ID, AttributeConstants.PARAM_DOWNLOAD_FILE})
    public void downloadFile(@RequestParam int id, HttpServletResponse response) {
        userService.downloadFile(id, response);
    }

}

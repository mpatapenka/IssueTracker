package org.maxim.issuetracker.web;

import org.maxim.issuetracker.domain.*;
import org.maxim.issuetracker.security.SecurityConstants;
import org.maxim.issuetracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AssigmentService assigmentService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StatusService statusService;

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String showDashboard(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee user = employeeService.findByLogin(username);

        Set<Member> members = user.getMembers();
        List<Assigment> assignToMe = new ArrayList<>();
        for (Member member : members) {
            assignToMe.addAll(member.getAssigments());
        }
        int withoutOffset = 0;
        List<Activity> lastActivities = activityService.listLast(withoutOffset);

        model.addAttribute("lastActivities", lastActivities);
        model.addAttribute("assignToMe", assignToMe);

        return "dashboard";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = "/dashboard/activity", method = RequestMethod.GET)
    public String getActivities(@RequestParam int offset) {
        List<Activity> activities = activityService.listLast(offset);
        return activityService.convertToJson(activities);
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_LEAD)
    @ResponseBody
    @RequestMapping(value = "/employees", method = RequestMethod.POST, params = "project")
    public String getProjectEmployees(@RequestParam(value = "project") int id) {
        Project project = projectService.get(id);
        return projectService.getProjectMembersWithJson(project);
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_LEAD)
    @ResponseBody
    @RequestMapping(value = "/issues", method = RequestMethod.POST, params = "create")
    public String createIssue(Assigment assigment) {
        int memberId = assigment.getMember().getId();
        int projectId = assigment.getMember().getProject().getId();

        Project project = projectService.get(projectId);
        if (project == null) {
            return "Check your input data and try again.";
        }

        Task task = assigment.getTask();
        task.setProject(project);

        Status initialStatus = statusService.getInitial();
        task.setStatus(initialStatus);

        Member member = memberService.get(memberId);
        if (member != null) {
            assigment.setMember(member);
            assigment.setTask(task);
            assigmentService.add(assigment);
            System.out.println("member");
        } else {
            taskService.add(task);
            System.out.println("task");
        }
        return "";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @RequestMapping(value = "/issues", method = RequestMethod.GET, params = "id")
    public String showIssue(@RequestParam int id, Model model) {
        Assigment assigment = assigmentService.get(id);
        model.addAttribute("assign", assigment);
        return "issues";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = "/issues", method = RequestMethod.GET, params = {"id", "export"})
    public Assigment exportIssueToXml(@RequestParam int id) {
        return assigmentService.get(id);
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @ResponseBody
    @RequestMapping(value = "/issues", method = RequestMethod.POST, params = {"id", "report"})
    public String reportIssue(@RequestParam int id, @Valid Activity activity, BindingResult bindingResult) {
        Assigment assigment = assigmentService.get(id);
        Member member = assigment.getMember();

        activity.setAssigment(assigment);
        activity.setMember(member);
        activity.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            for (FieldError error : bindingResult.getFieldErrors()) {
                builder.append("Field '")
                        .append(error.getField())
                        .append("' has error: ")
                        .append(error.getDefaultMessage())
                        .append("\n");
            }
            return builder.toString();
        }
        activityService.add(activity);
        return "";
    }

    @PreAuthorize(SecurityConstants.HAS_ROLE_USER)
    @RequestMapping(value = "/issues", method = RequestMethod.GET, params = "search")
    public String showSearchIssues() {
        return "issues";
    }

}

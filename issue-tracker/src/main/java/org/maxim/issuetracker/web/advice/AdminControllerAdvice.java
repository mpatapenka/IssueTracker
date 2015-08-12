package org.maxim.issuetracker.web.advice;

import org.maxim.issuetracker.domain.Project;
import org.maxim.issuetracker.service.ProjectService;
import org.maxim.issuetracker.web.AdminController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice(assignableTypes = AdminController.class)
public class AdminControllerAdvice {

    @Autowired
    private ProjectService projectService;

    @ModelAttribute
    public void addAllProjects(Model model) {
        List<Project> projects = projectService.list();
        model.addAttribute("allProjects", projects);
    }

}

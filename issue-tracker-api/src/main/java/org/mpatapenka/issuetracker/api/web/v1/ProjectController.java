package org.mpatapenka.issuetracker.api.web.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.mpatapenka.issuetracker.api.web.WebConstants.API_V1;

@RestController
@RequestMapping(ProjectController.BASE_URI)
public class ProjectController {

    public static final String BASE_URI = API_V1 + "/projects";


    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProject(@PathVariable("projectId") long projectId) {
        return ResponseEntity.noContent().build();
    }
}
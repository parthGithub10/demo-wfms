package com.wfms.configurationManagement.controller;

import java.util.List;

import com.wfms.configurationManagement.exceptions.CustomBadRequestException;
import com.wfms.configurationManagement.model.ProjectDetails;
import com.wfms.configurationManagement.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/wfms/projectManagement/v1")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    // build add projectDetails REST API
    @PostMapping("/projectDetails")
    public ResponseEntity<ProjectDetails> addProjectDetails(@Valid @RequestBody ProjectDetails projectDetails, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            logger.error("Validation errors: {}", bindingResult.getAllErrors());
            throw new CustomBadRequestException(bindingResult.getAllErrors().toString());
        }
        logger.info("Received request to add project details: {}", projectDetails);
        ProjectDetails newProject = projectService.addProjectDetails(projectDetails);
        logger.info("Added Project Details: {}", newProject);

        return new ResponseEntity<ProjectDetails>(newProject, HttpStatus.CREATED);

    }

    // build get all projectDetails REST API
    @GetMapping("/projectDetailsList")
    public List<String> getProjectDetailsList() {

        logger.info("Received request to get project details list");
        List<String> projectDetailsList = projectService.getProjectDetailsList();
        logger.info("Returning project details list: {}", projectDetailsList);

        return projectDetailsList;

    }

    // get project Details by id REST API
    @GetMapping("/projectDetails/{id}")
    public ResponseEntity<ProjectDetails> getSpecificProjectDetails(@PathVariable("id") int id) {

        logger.info("Received request to get project details with id: {}", id);
        ProjectDetails projectDetails = projectService.getSpecificProjectDetails(id);
        logger.info("Returning project details: {}", projectDetails);

        return new ResponseEntity<ProjectDetails>(projectDetails, HttpStatus.OK);

    }

    // delete project Details by id REST API
    @DeleteMapping("/projectDetails/{id}")
    public ResponseEntity<String> deleteProjectDetails(@PathVariable("id") int id) {

        logger.info("Received request to delete project details for project id: {}", id);
        projectService.deleteProjectDetails(id);
        logger.info("Successfully deleted project details for project id: {}", id);

        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);

    }
}
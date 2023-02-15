package com.wfms.configurationManagement.service;

import java.util.List;

import com.wfms.configurationManagement.model.ProjectDetails;

public interface ProjectService {

    ProjectDetails addProjectDetails(ProjectDetails projectDetails);

    List<String> getProjectDetailsList();

    ProjectDetails getSpecificProjectDetails(int id);

    void deleteProjectDetails(int id);

}
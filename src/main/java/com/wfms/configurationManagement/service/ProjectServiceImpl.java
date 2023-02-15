package com.wfms.configurationManagement.service;

import java.util.List;
import java.util.Optional;

import com.wfms.configurationManagement.dal.ProjectDal;
import com.wfms.configurationManagement.exceptions.CustomDataNotFoundException;
import com.wfms.configurationManagement.model.ProjectDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfms.configurationManagement.exceptions.ResourceNotFound;

import jakarta.transaction.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDal projectRepository;

    @Override
    public ProjectDetails addProjectDetails(ProjectDetails projectDetails) {
        //saving the record
        return projectRepository.save(projectDetails);
    }

    @Override
    public List<String> getProjectDetailsList() {

        List<String> projectList;
        try {
            projectList = projectRepository.getProjectDetailsList();
        } catch (HttpClientErrorException.NotFound e) {
            throw new CustomDataNotFoundException();
        }
        return projectList;
    }

    @Override
    public ProjectDetails getSpecificProjectDetails(int id) {

        Optional<ProjectDetails> projectDetails = Optional.ofNullable(projectRepository.getByProjectId(id));
        if (projectDetails.isPresent()) {
            return projectDetails.get();
        } else {
            throw new CustomDataNotFoundException();
        }
    }

    @Override
    public void deleteProjectDetails(int id) {

        Optional<ProjectDetails> projectDetails = Optional.ofNullable(projectRepository.getByProjectId(id));
        if (projectDetails.isPresent()) {
            projectRepository.deleteById(id);
        } else {
            throw new ResourceNotFound();
        }

    }

}
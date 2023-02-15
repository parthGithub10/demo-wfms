package com.wfms.configurationManagement.dal;

import com.wfms.configurationManagement.model.ConfigurationPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

import com.wfms.configurationManagement.model.ProjectDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectDal extends JpaRepository<ProjectDetails, ConfigurationPrimaryKey> {
    @Query(value="SELECT * FROM project_details WHERE in_use=true",nativeQuery = true)
    public List<String> getProjectDetailsList();

    @Query(value = "SELECT * FROM project_details WHERE project_id=?1 AND in_use=true",nativeQuery = true)
    public ProjectDetails getByProjectId(int projectId);

    @Modifying
    @Query(value = "DELETE FROM project_details WHERE project_id=?1",nativeQuery = true)
    public void deleteById(int projectId);
}
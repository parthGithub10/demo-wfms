 package com.wfms.configurationManagement.dal;

import java.util.List;

import com.wfms.configurationManagement.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wfms.configurationManagement.model.ConfigurationPrimaryKey;

public interface ConfigurationDal extends JpaRepository<Configuration, ConfigurationPrimaryKey> {
	

			public Configuration findByProjectIdAndVersionNumberAndResourceType(int projectId, int versionNumber, String resourceType);
			
			@Query(value = "SELECT * FROM configuration WHERE project_id=?1 AND in_use=true AND resource_type=?2",nativeQuery = true)
			public Configuration getByProjectId(int projectId,String resourceType);
			
			@Query(value="SELECT MAX(version_number) FROM configuration WHERE project_id=?1 AND resource_type=?2",nativeQuery = true)
			public int getLatestVersion(int projectId,String resourceType);

			@Modifying
			@Query(value = "UPDATE configuration SET in_use=?3 WHERE project_id=?1 AND version_number=?2 AND resource_type=?4",nativeQuery = true)
			public void updateInUseToFalse(int projectId, int versionNumber,boolean inUse, String resourceType);

			@Query(value="SELECT COUNT(*) FROM configuration WHERE project_id=?1 AND resource_type=?2",nativeQuery = true)
			public int countOfProjectConfiguration(int projectId,String resourceType);
			
			
			@Query(value="SELECT project_name FROM configuration WHERE in_use=true AND resource_type=?1",nativeQuery = true)
			public List<String> getProjectList(String resourceType);
			
			
			//delete
			@Modifying
			@Query(value = "DELETE FROM configuration WHERE project_id=?1 AND resource_type=?2",nativeQuery = true)
			public void deleteAllById(int projectId,String resourceType);

			@Modifying
			@Query(value = "DELETE FROM configuration WHERE project_id=?1 AND version_number=?2 AND resource_type=?3",nativeQuery = true)
			public void deleteSpecificProjectConfiguration(int id, int version,String resourceType);

			
			/*
			@Modifying
			@Query(value = "UPDATE project_configuration SET in_use=true WHERE project_id=?1 AND version_number=?2",nativeQuery = true)
			public void updateInUseToTrue(int id, int version);
			*/

			
	
}

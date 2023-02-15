package com.wfms.configurationManagement.service;

import java.util.List;

import com.wfms.configurationManagement.model.Configuration;

public interface ConfigurationService {
	
	Configuration addProjectConfiguration(Configuration configuration);
	
	Configuration getSpecificProjectConfiguration(int id, int version, String resourceType);
	
	Configuration getLatestProjectConfiguration(int id,String resourceType);
	
	void deleteAllProjectConfiguration(int id,String resourceType);
	
//	void deleteSpecificProjectConfiguration(int id,int version);
	
	List<String> getProjectList(String resourceType);




}

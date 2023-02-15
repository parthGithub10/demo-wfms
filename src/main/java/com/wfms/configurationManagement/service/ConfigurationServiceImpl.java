package com.wfms.configurationManagement.service;

import java.util.List;
import java.util.Optional;

import com.wfms.configurationManagement.exceptions.CustomDataNotFoundException;
import com.wfms.configurationManagement.model.Configuration;
import org.springframework.stereotype.Service;

import com.wfms.configurationManagement.dal.ConfigurationDal;
import com.wfms.configurationManagement.exceptions.ResourceNotFound;

import jakarta.transaction.Transactional;
import org.springframework.web.client.HttpClientErrorException;


@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService{

	private ConfigurationDal repository;
	

	public ConfigurationServiceImpl(ConfigurationDal repository) {
		super();
		this.repository = repository;
	}


	@Override
	public Configuration addProjectConfiguration(Configuration configuration) {
		// TODO Auto-generated method stub
		
		
		//getting the latest version and setting the value of version as one more than the current version 
		//only if at least one version exist else setting it as 1.

		int countOfRecords = repository.countOfProjectConfiguration(configuration.getProjectId(),configuration.getResourceType());
		if(countOfRecords > 0) {
			
			int latestVersionNumber = repository.getLatestVersion(configuration.getProjectId(),configuration.getResourceType());

			if(countOfRecords==10)
			{
				repository.deleteSpecificProjectConfiguration(configuration.getProjectId(),latestVersionNumber-9,configuration.getResourceType());
			}

			configuration.setVersionNumber(latestVersionNumber+1);
		}
		else {
			configuration.setVersionNumber(1);
		}

		configuration.setInUse(true);
		
		
		//this function updates the previous version (in Use ) field to false
		//it only executes if the current version is greater than 1
		if(configuration.getVersionNumber()>1) {
			repository.updateInUseToFalse(configuration.getProjectId(),configuration.getVersionNumber()-1,false,configuration.getResourceType());
		}
		
		
		//saving the record
		return repository.save(configuration);
	}

	@Override
	public List<String> getProjectList(String resourceType){

		List<String> projectList;
		try {
			projectList = repository.getProjectList(resourceType);
		}
		catch (HttpClientErrorException.NotFound e)
		{
			throw new CustomDataNotFoundException();
		}
		return projectList;
	}
	
	@Override
	public Configuration getSpecificProjectConfiguration(int id, int version, String resourceType) {

		Optional<Configuration> configuration = Optional.ofNullable(repository.findByProjectIdAndVersionNumberAndResourceType(id, version,resourceType));
		
		if(configuration.isPresent()) {
			return configuration.get();
		}
		else {
			throw new CustomDataNotFoundException();
		}
	}


	@Override
	public Configuration getLatestProjectConfiguration(int id,String resourceType) {
		
		Optional<Configuration> configuration = Optional.ofNullable(repository.getByProjectId(id,resourceType));
				
		if(configuration.isPresent()) {
			return configuration.get();
		}
		else {
			throw new ResourceNotFound();
		}
		
	}


	@Override
	public void deleteAllProjectConfiguration(int id, String resourceType) {
				
		Optional<Configuration> configuration = Optional.ofNullable(repository.getByProjectId(id,resourceType));
		
		if(configuration.isPresent()) {
			repository.deleteAllById(id,resourceType);
		}
		else {
			throw new ResourceNotFound();
		}
		
	}


}
  
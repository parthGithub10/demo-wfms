package com.wfms.configurationManagement.controller;

import com.wfms.configurationManagement.exceptions.CustomBadRequestException;
import com.wfms.configurationManagement.model.Configuration;
import com.wfms.configurationManagement.service.ConfigurationService;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("wfms/configurationManagement/v1")
public class ConfigurationController {
	

	@Autowired
	private ConfigurationService configurationService;
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationController.class);


	//build add project configuration REST API
	
	@PostMapping("/projectConfiguration")
	public ResponseEntity<Configuration> addProjectConfiguration(@Valid @RequestBody Configuration configuration, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
	        logger.error("Validation errors: {}", bindingResult.getAllErrors());
			throw new CustomBadRequestException(bindingResult.getAllErrors().toString());
	    }
		
	    logger.info("Received request to add project configuration: {}", configuration);
	    Configuration newConfiguration = configurationService.addProjectConfiguration(configuration);
	    logger.info("Added project configuration: {}", newConfiguration);
	    
	    return new ResponseEntity<Configuration>(newConfiguration, HttpStatus.CREATED);
	}
	
	//build get project configuration REST API
	@GetMapping("/projectConfiguration/{id}/{version}/{resourceType}")
	public ResponseEntity<Configuration> getSpecificProjectConfiguration(@PathVariable("id") int id, @PathVariable("version") int version,@PathVariable("resourceType") String resourceType)
	{

			logger.info("Received request to get specific project configuration with id: {}, version: {}", id, version);

			Configuration configuration = configurationService.getSpecificProjectConfiguration(id, version,resourceType);

			logger.info("Returning project configuration: {}", configuration);

			return new ResponseEntity<Configuration>(configuration, HttpStatus.OK);

	}
	
	//get latest project configuration API
	@GetMapping("/projectConfiguration/{id}/{resourceType}")
	public ResponseEntity<Configuration> getLatestProjectConfiguration(@PathVariable("id") int id,@PathVariable("resourceType") String resourceType){


		logger.info("Received request to get latest project configuration with id: {}", id);
		
        Configuration configuration = configurationService.getLatestProjectConfiguration(id,resourceType);
        
        logger.info("Returning project configuration: {}", configuration);

		return new ResponseEntity<Configuration>(configuration, HttpStatus.OK);
	}
	
	@GetMapping("/projectList/{resourceType}")
	public List<String> getProjectList(@PathVariable("resourceType") String resourceType)
	{
		logger.info("Received request to get project list");
		
	    List<String> projectList = configurationService.getProjectList(resourceType);
	    
	    logger.info("Returning project list: {}", projectList);
	    return projectList;
	}
	
	@DeleteMapping("/projectConfiguration/{id}/{resourceType}")
	public ResponseEntity<String> deleteAllProjectConfiguration(@PathVariable("id") int id,@PathVariable("resourceType") String resourceType){
		
		logger.info("Received request to delete all project configuration for project id: {}", id);
		
	    configurationService.deleteAllProjectConfiguration(id,resourceType);
	    
	    logger.info("Successfully deleted all project configuration for project id: {}", id);
	    
	    return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
	}
	

	
	
	
	
	
}

package com.wfms.configurationManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@Entity
@Getter
@Setter
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Table(name="Configuration")
@IdClass(ConfigurationPrimaryKey.class)
public class Configuration {


	@Id
	@NotNull(message = "projectId cannot be null")
	private int projectId;

	@Id
	private int versionNumber;
	

	@Column(name="project_Name",nullable = true)
	@NotNull
	private String projectName;
	
	
	
	@Column( name="configuration",nullable = true,columnDefinition = "json" )
	@NotNull
	private String configuration;

	@Column(name = "resource_type",nullable=true)
	private String resourceType;

	@Column(name="in_use",nullable=true)
	private boolean inUse;
	

	
}

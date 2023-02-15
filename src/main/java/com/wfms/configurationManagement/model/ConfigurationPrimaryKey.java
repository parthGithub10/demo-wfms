package com.wfms.configurationManagement.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ConfigurationPrimaryKey implements Serializable{


	
	private int projectId;
	private int versionNumber;


	public int getProjectId() {
		return projectId;
	}


	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}


	public int getVersion() {
		return versionNumber;
	}


	public void setVersion(int version) {
		this.versionNumber = version;
	}
	
	
}

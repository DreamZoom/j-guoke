package com.guoke.architecture.models;

import javax.persistence.Entity;

import com.guoke.model.BaseEntity;
import com.guoke.model.meta.Metadata;

@Entity
public class Project extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Metadata(name="项目名称")
	private String projectName;
	
	@Metadata(name="项目描述")
	private String projectDesc;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

}

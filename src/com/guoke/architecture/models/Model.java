package com.guoke.architecture.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.guoke.model.BaseEntity;
import com.guoke.model.meta.Metadata;

@Entity
public class Model extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Metadata(name="项目编号",mode="show")
	private int projectId;
	@Metadata(name="模型名称")
	private String name;
	
	@Metadata(name="模型",mode="none")
	
	private String body;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(columnDefinition="longtext default ''")
	public String getBody() {
		return body;
	}

	@Type(type="longtext")  
	public void setBody(String body) {
		this.body = body;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	
	

}

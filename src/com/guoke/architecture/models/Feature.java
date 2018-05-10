package com.guoke.architecture.models;

import javax.persistence.Entity;

import com.guoke.model.BaseEntity;
import com.guoke.model.meta.Metadata;

@Entity
public class Feature extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Metadata(name="功能名称")
	private String name;
	
	@Metadata(name="功能描述")
	private String descript;
	
	@Metadata(name="标签")
	private String tag;
	
	@Metadata(name="项目编号",mode="show")
	private int projectId;

	@Metadata(name="父ID",mode="show")
	private int parentId;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	

}

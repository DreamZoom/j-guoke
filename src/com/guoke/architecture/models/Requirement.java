package com.guoke.architecture.models;

import javax.persistence.Entity;

import com.guoke.model.BaseEntity;
import com.guoke.model.meta.Metadata;
@Entity
public class Requirement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 需求描述
	 */
	@Metadata(name="需求描述")
	private String RequirementDesc;
	
	/**
	 * 标签
	 */
	@Metadata(name="标签")
	private String RequirementTag;
	
	/**
	 * 优先级
	 */
	@Metadata(name="优先级")
	private int priority;
	
	@Metadata(name="项目编号",mode="show")
	private int projectId;

	public String getRequirementDesc() {
		return RequirementDesc;
	}

	public void setRequirementDesc(String requirementDesc) {
		RequirementDesc = requirementDesc;
	}

	public String getRequirementTag() {
		return RequirementTag;
	}

	public void setRequirementTag(String requirementTag) {
		RequirementTag = requirementTag;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

}

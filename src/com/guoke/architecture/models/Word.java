package com.guoke.architecture.models;

import javax.persistence.Entity;

import com.guoke.model.BaseEntity;
import com.guoke.model.meta.Metadata;

@Entity
public class Word extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Metadata(name="关键词")
	private String word;
	@Metadata(name="类型标签")
	private String tag;
	
	@Metadata(name="项目编号",mode="show")
	private int projectId;

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

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
	
	

}

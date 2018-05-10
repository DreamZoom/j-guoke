package com.guoke.architecture.models;

import java.sql.Timestamp;

import javax.persistence.Entity;

import com.guoke.model.BaseEntity;
import com.guoke.model.meta.Metadata;

@Entity
public class Bug extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Metadata(name="项目")
	private int projectId;
	
	@Metadata(name="功能")
	private String feature;
	
	@Metadata(name="bug描述")
	private String descript;
	
	@Metadata(name="图片",type="images")
	private String images;
	
	@Metadata(name="报告人")
	private String reporter;
	
	@Metadata(name="修复人")
	private String repair;
	
	@Metadata(name="报告时间",type="datetime",mode="show")
	private Timestamp reportTime;
	
	@Metadata(name="状态",type="enum")
	private int state;


	public int getProjectId() {
		return projectId;
	}



	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}



	public String getFeature() {
		return feature;
	}



	public void setFeature(String feature) {
		this.feature = feature;
	}



	public String getDescript() {
		return descript;
	}



	public void setDescript(String descript) {
		this.descript = descript;
	}



	public String getImages() {
		return images;
	}



	public void setImages(String images) {
		this.images = images;
	}



	public String getReporter() {
		return reporter;
	}



	public void setReporter(String reporter) {
		this.reporter = reporter;
	}



	public String getRepair() {
		return repair;
	}



	public void setRepair(String repair) {
		this.repair = repair;
	}



	public int getState() {
		return state;
	}



	public void setState(int state) {
		this.state = state;
	}



	public Timestamp getReportTime() {
		return reportTime;
	}



	public void setReportTime(Timestamp reportTime) {
		this.reportTime = reportTime;
	}

}

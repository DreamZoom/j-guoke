package com.guoke.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

import com.guoke.model.meta.Metadata;

@MappedSuperclass
public class BaseEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Metadata(name="编号",sort=0)
	private int Id;
	
	@Metadata(name="创建时间",sort=10001)
	private Timestamp createTime;	
	
	@Metadata(name="更新时间",sort=10002)
	private Timestamp updateTime;
	
	@Metadata(name="备注",sort=10003)
	private String remark;
	
	@Metadata(name="排序",sort=10004)
	private int sort;


	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Timestamp getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public int getSort() {
		return sort;
	}


	public void setSort(int sort) {
		this.sort = sort;
	}

}

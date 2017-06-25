package com.hangdaoju.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

public class BaseModel {
	@Field("create_time")
	private Date createTime;
	@Field("update_time")
	private Date updateTime;
	@Field("create_user")
	private String createUser;
	@Field("update_user")
	private String updateUser;
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	
}

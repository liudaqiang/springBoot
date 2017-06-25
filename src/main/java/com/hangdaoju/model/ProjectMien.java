package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
@Document(collection="project_mien")
public class ProjectMien extends BaseModel{
	@Id
	@QueryField
	private String id;
	@Field("project_mien_type")
	@QueryField(attribute="project_mien_type")
	private String projectMienType;// 类型1党员示范岗、2青年突击、3队立功竞赛、4每月之星
	@Field("project_id")
	@QueryField(attribute="project_id")
	private String projectId;// 所属项目
	@QueryField
	private String name;// 名称
	@QueryField
	private String picture;// 图片
	@QueryField
	private String description;// 描述

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getProjectMienType() {
		return projectMienType;
	}

	public void setProjectMienType(String projectMienType) {
		this.projectMienType = projectMienType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

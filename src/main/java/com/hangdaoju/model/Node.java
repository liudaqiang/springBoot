package com.hangdaoju.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;

/**
 * 工程节点
 * @author l.q
 *
 */
@Document
public class Node extends BaseModel{
	@Id
	@QueryField
	private String id;
	@Field("node_time")
	@QueryField(type=QueryType.EQUALS,attribute="node_time")
	private Date nodeTime;//节点时间
	@QueryField
	private String description;//文字描述
	@Field("project_id")
	@QueryField(type=QueryType.EQUALS,attribute="project_id")
	private String projectId;//所属工程
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getNodeTime() {
		return nodeTime;
	}
	public void setNodeTime(Date nodeTime) {
		this.nodeTime = nodeTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}

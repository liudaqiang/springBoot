package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;

public class Folder extends BaseModel{
	@Id
	@QueryField
	private String id;
	@QueryField
	@Field("project_id")
	private String projectId;// 所属项目
	@QueryField
	private String name;// 文件夹名称
//	@QueryField(attribute="folder_type",type = QueryType.IN)
//	@Field("folder_type")
//	private String folderType;// 分类类型（1视频2文档3图片4其他）
	@QueryField
	@Field("parent_id")
	private String parentId="1";// 文件夹编号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
//
//	public String getFolderType() {
//		return folderType;
//	}
//
//	public void setFolderType(String folderType) {
//		this.folderType = folderType;
//	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}

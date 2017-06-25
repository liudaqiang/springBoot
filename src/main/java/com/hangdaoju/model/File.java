package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;

@Document
public class File extends BaseModel {
	@Id
	@QueryField
	private String id;
	@QueryField
	@Field("project_id")
	private String projectId;// 所属项目
	@QueryField
	private String company;// 上传单位
	@QueryField
	@Field("upload_time")
	private String uploadTime;// 上传时间
	@QueryField
	private String reviewer;// 审核人
	@QueryField
	@Field("review_company")
	private String reviewCompany;// 审核单位
	@QueryField
	@Field("review_time")
	private String reviewTime;// 审核时间
	@QueryField
	private String major;// 专业1
	@QueryField
	private String keyWord;// 关键字
	@QueryField
	private String name;// 文件名
	@QueryField
	@Field("ext_name")
	private String extName;// 扩展名1
	@QueryField
	private String size;// 文件大小
	// private String logo;// 图标（即分类）
	@QueryField
	private String department;// 部门2
	@QueryField
	private String stage;// 工程阶段2
	@QueryField
	private String mark;// 标段1
	@QueryField
	@Field("folder_id")
	private String folderId;// 文件夹编号
	@QueryField
	private String path;// 存储位置

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getReviewCompany() {
		return reviewCompany;
	}

	public void setReviewCompany(String reviewCompany) {
		this.reviewCompany = reviewCompany;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
	}
}

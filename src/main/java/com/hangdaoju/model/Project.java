package com.hangdaoju.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;

/**
 * 项目实体类
 * 
 * @author l.q
 *
 */
@Document
public class Project extends BaseModel {
	@Id
	@QueryField
	private String id;
	@QueryField(type = QueryType.LIKE, attribute = "name")
	private String name;// 项目名称
	@QueryField
	private String picture;// 项目图片
	@Field("project_type")
	@QueryField(type = QueryType.EQUALS, attribute = "project_type")
	private String projectType;// 项目类型1.疏浚吹填类2.筑堤类3.市政类4.水工建筑类5.地基处理类
	@Field("company_id")
	@QueryField(type = QueryType.EQUALS, attribute = "company_id")
	private String companyId;// 管理单位Id
	@Transient
	private String companytName;
	@Field("father_project_id")
	@QueryField(type = QueryType.EQUALS, attribute = "father_project_id")
	private String fatherProjectId;// 上级项目
	@Transient
	private String fatherProjectName = "无";
	@QueryField
	private String address;// 施工地址
	@QueryField
	private String longitude;// 经度
	@QueryField
	private String latitude;// 纬度
	@Field("begin_time")
	@QueryField(attribute = "begin_time")
	private Date beginTime;// 开始时间
	@Field("end_time")
	@QueryField(attribute = "end_time")
	private Date endTime;// 结束时间
	@Field("manager_person")
	@QueryField(type = QueryType.EQUALS, attribute = "manager_person")
	private String managerPerson;// 项目负责人
	@Transient
	private String managerPersonName;
	@QueryField
	private String description;// 项目描述
	@QueryField(type = QueryType.LIKE, attribute = "scale")
	private String scale;// 项目规模 超特大型 特大型 大型 中小型
	@QueryField(type = QueryType.LIKE, attribute = "local")
	private String local;// 区域分布 东北地区 华北地区 华东地区 华中地区 华南地区 西北地区 西南地区 港澳台地区
	@QueryField
	@Field("scheme_ids")
	private List<String> schemeIds;// 文件筛选方案

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getFatherProjectId() {
		return fatherProjectId;
	}

	public void setFatherProjectId(String fatherProjectId) {
		this.fatherProjectId = fatherProjectId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getManagerPerson() {
		return managerPerson;
	}

	public void setManagerPerson(String managerPerson) {
		this.managerPerson = managerPerson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getFatherProjectName() {
		return fatherProjectName;
	}

	public void setFatherProjectName(String fatherProjectName) {
		this.fatherProjectName = fatherProjectName;
	}

	public String getManagerPersonName() {
		return managerPersonName;
	}

	public void setManagerPersonName(String managerPersonName) {
		this.managerPersonName = managerPersonName;
	}

	public String getCompanytName() {
		return companytName;
	}

	public void setCompanytName(String companytName) {
		this.companytName = companytName;
	}

	public List<String> getSchemeIds() {
		return schemeIds;
	}

	public void setSchemeIds(List<String> schemeIds) {
		this.schemeIds = schemeIds;
	}

}

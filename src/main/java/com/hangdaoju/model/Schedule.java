package com.hangdaoju.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;

/**
 * 进度计划
 * @author l.q
 *
 */
public class Schedule extends BaseModel{
	@Id
	@QueryField
	private String id;
	@QueryField
	private String name;//计划名称   必须
	@QueryField
	private String icon;//图片
	@QueryField(attribute="start_time")
	@Field("start_time")
	private Date startTime;//开始时间  必须
	@Field("end_time")
	@QueryField(attribute="end_time")
	private Date endTime;//结束时间  必须
	private Person leader;//责任人 必须
	@Field("equipment_list")
	private List<Dict> equipmentList;//设备列表
	@Field("material_list")
	private List<Dict> materialList;//材料列表
	@Field("type_word_list")
	private List<Dict> typeWordList;//工种列表
	@Field("company_id")
	@QueryField(attribute="company_id")
	private String companyId;//所属单位   必须
	
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Person getLeader() {
		return leader;
	}
	public void setLeader(Person leader) {
		this.leader = leader;
	}
	public List<Dict> getEquipmentList() {
		return equipmentList;
	}
	public void setEquipmentList(List<Dict> equipmentList) {
		this.equipmentList = equipmentList;
	}
	public List<Dict> getMaterialList() {
		return materialList;
	}
	public void setMaterialList(List<Dict> materialList) {
		this.materialList = materialList;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public List<Dict> getTypeWordList() {
		return typeWordList;
	}
	public void setTypeWordList(List<Dict> typeWordList) {
		this.typeWordList = typeWordList;
	}
	
}

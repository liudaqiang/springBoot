package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;
import com.hangdaoju.util.excel.annotation.ExcelField;

@Document(collection = "migrant_worker")
public class MigrantWorker extends BaseModel {
	@Id
	@QueryField
	private String id;
	@QueryField
	private String name;// 姓名
	@Field("id_card")
	@QueryField
	private String idCard;// 身份证号
	@Field("project_id")
	@QueryField
	private String project;// 所属工程
	@Field("company_id")
	@QueryField
	private String company;// 责任单位

	@QueryField(type = QueryType.LIKE)
	private String address;// 家庭住址

	@QueryField
	private String mobile;// 联系方式

	@Field("work_type")
	@QueryField
	private String workType;// 工种

	@Field("emergency_contact")
	@QueryField
	private String emergencyContact;// 紧急联系人

	@Field("emergency_mobile")
	@QueryField
	private String emergencyMobile;// 紧急联系方式

	@Field("special_number")
	@QueryField
	private String specialNumber;// 特种作业证号

	@Field("special_date_start")
	@QueryField
	private String specialDateStart;// 特种作业证号有效日期起始

	@Field("special_date_end")
	@QueryField
	private String specialDateEnd;// 特种作业证号有效日期結束

	@Field("wage_date")
	@QueryField
	private String wageDate;// 工资收讫（日期）

	@Field("wage_bool")
	@QueryField
	private String wageBool;// 工资收讫（是否）

	@Field("security_insurance")
	@QueryField
	private String securityInsurance;// 安全保险

	@Field("qr_code")
	@QueryField
	private String qrCode;// 二维码

	@Field("account_id")
	@QueryField
	private String accountId;// 关联账户

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ExcelField(title = "姓名")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@ExcelField(title = "所属工程")
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	@ExcelField(title = "责任单位")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}

	public String getSpecialNumber() {
		return specialNumber;
	}

	public void setSpecialNumber(String specialNumber) {
		this.specialNumber = specialNumber;
	}

	public String getSpecialDateStart() {
		return specialDateStart;
	}

	public void setSpecialDateStart(String specialDateStart) {
		this.specialDateStart = specialDateStart;
	}

	public String getSpecialDateEnd() {
		return specialDateEnd;
	}

	public void setSpecialDateEnd(String specialDateEnd) {
		this.specialDateEnd = specialDateEnd;
	}

	public String getWageDate() {
		return wageDate;
	}

	public void setWageDate(String wageDate) {
		this.wageDate = wageDate;
	}

	public String getWageBool() {
		return wageBool;
	}

	public void setWageBool(String wageBool) {
		this.wageBool = wageBool;
	}

	public String getSecurityInsurance() {
		return securityInsurance;
	}

	public void setSecurityInsurance(String securityInsurance) {
		this.securityInsurance = securityInsurance;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}

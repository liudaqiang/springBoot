package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;
/**
 * 用户类
 * @author l.q
 *
 */
@Document
public class Person extends BaseModel{
	@Id
    @QueryField
    private String id;
    @Field("user_name")
    @QueryField(type = QueryType.EQUALS, attribute = "user_name") 
    private String userName;//账号
    @QueryField
    private String password;//密码
    @QueryField
    private String name;//姓名
    @Field("job_code")
    @QueryField(type = QueryType.EQUALS, attribute = "job_code") 
    private String jobCode;//工号
    @QueryField
    private String sex;//性别1男2女
    @QueryField
    private String email;//邮箱
    @QueryField
    private String phone;//电话
    @QueryField
    private String qq;//qq号
    @QueryField
    private String companyId;//所在公司
    @QueryField
    private String projectId;
    @QueryField
    private String position;//职位
    @QueryField
    private String avatar;//头像
    @QueryField
    private String personType;//人员类型
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	
}

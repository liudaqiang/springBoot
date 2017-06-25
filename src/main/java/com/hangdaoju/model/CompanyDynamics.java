package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;

/**
 * 公司动态
 * @author l.q
 *
 */
@Document(collection="company_dynamics") 
public class CompanyDynamics extends BaseModel{
	@Id
	 @QueryField
	private String id;
	@QueryField(type = QueryType.LIKE) 
	private String title;//标题
	@Field("sub_title")
	@QueryField(type=QueryType.EQUALS,attribute="sub_title")
	private String subTitle;//副标题
	@QueryField(type=QueryType.LIKE)
	private String content;//文本
	@Field("company_id")
	@QueryField(type=QueryType.EQUALS,attribute="company_id")
	private String companyId;//所属公司
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	
}

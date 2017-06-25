package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;

@Document
public class Dict extends BaseModel{
	@Id
	@QueryField
	private String id;
	@Field("dict_type")
	@QueryField(type=QueryType.EQUALS,attribute="dict_type")
	private String dictType;//项类型 1设备21原材料22成品材料3工种4专业5工程阶段6扩展名7标段
	@QueryField
	private String name;//名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDictType() {
		return dictType;
	}
	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

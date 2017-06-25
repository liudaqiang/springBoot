package com.hangdaoju.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.hangdaoju.core.customAnnotation.QueryField;

@Document
public class Scheme extends BaseModel {
	@Id
	@QueryField
	private String id;
	@QueryField
	private String name;// 方案名称
	@QueryField
	private List<String> properties = new ArrayList<>();// 方案内的字段

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

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}
}

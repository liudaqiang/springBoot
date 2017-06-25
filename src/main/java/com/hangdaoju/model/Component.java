package com.hangdaoju.model;
/**
 * 构件
 * @author l.q
 *
 */
public class Component extends BaseModel{
	private String name;
	private String componentType;
	private Integer num;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComponentType() {
		return componentType;
	}
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
}

package com.hangdaoju.model;

import com.hangdaoju.core.customAnnotation.QueryField;

/**
 * 工种
 * @author l.q
 *
 */
public class TypeWord{
	@QueryField
	private Dict dict;
	private Integer num;
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	
}

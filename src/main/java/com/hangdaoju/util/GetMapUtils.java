package com.hangdaoju.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 此方法仅在本航道局项目中使用
 * @author l.q
 *
 */
public class GetMapUtils {
	//仅仅是为了代码上看着舒服，写的utils
	public static Map<String,Object> getMap(String key,Object obj){
		Map<String,Object> map = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(key)){
			map.put(key,obj);
			return map;
		}
		map.put("default", obj);
		return map;
		
	}
}

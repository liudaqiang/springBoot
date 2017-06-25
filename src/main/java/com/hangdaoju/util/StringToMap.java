package com.hangdaoju.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 将get格式请求转换成map形式
 * @author liuqiang
 *
 */
public class StringToMap {
	
	public static Map<String,String> stringToMap(String param){
		Map<String,String> stringMap = new HashMap<String,String>();
		String [] params = param.split("&");
		for(String oneParam:params){
			String [] oneMap = oneParam.split("=");
			if(oneMap.length != 2){
				return null;
			}
			stringMap.put(oneMap[0], oneMap[1]);
		}
		return stringMap;
	}
	
	public static void main(String [] args){
		Map<String, String> map = stringToMap("a=asd&b=as&c=sdl");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}
	}
}

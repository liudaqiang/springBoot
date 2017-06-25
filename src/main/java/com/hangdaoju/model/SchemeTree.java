package com.hangdaoju.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.hangdaoju.service.DictService;
import com.hangdaoju.service.SchemeService;

public class SchemeTree {
	private String propertyName;
	private String propertyValue;
	private List<SchemeTree> children = new ArrayList<>();

	/**
	 * 
	 * @param dictService
	 * @param schemeService
	 * @param properties
	 * @return
	 */
	public SchemeTree init(DictService dictService, SchemeService schemeService, Queue<String> queue) {
		if (null == propertyName) {
			propertyName = "root";
			propertyValue = "root";
		}
		Queue<String> properties = new LinkedList<>();
		properties.addAll(queue);
		String childPropertyName = properties.poll();
		if (null == childPropertyName) {
			return this;
		}
		List<String> values = new ArrayList<>();
		if (childPropertyName.equals("stage")) {
			values.add("前期阶段");
			values.add("施工阶段");
			values.add("竣工阶段");
		}
		if (childPropertyName.equals("department")) {
			values.add("工程部");
			values.add("技术部");
			values.add("安全部");
			values.add("设备部");
			values.add("材料部");
			values.add("综合部");
			values.add("商务部");
		} else {
			childPropertyName = schemeService.transferToDictType(childPropertyName);
			Dict dict = new Dict();
			dict.setDictType(childPropertyName);
			List<Dict> dicts = dictService.findByCondition(dict);
			Iterator<Dict> dictIterator = dicts.iterator();

			while (dictIterator.hasNext()) {
				values.add(dictIterator.next().getName());
			}
		}
		Iterator<String> valueIterator = values.iterator();
		while (valueIterator.hasNext()) {
			children.add(new SchemeTree().setPropertyName(childPropertyName).setPropertyValue(valueIterator.next())
					.init(dictService, schemeService, properties));
		}

		return this;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public SchemeTree setPropertyName(String propertyName) {
		this.propertyName = propertyName;
		return this;
	}

	public List<SchemeTree> getChild() {
		return children;
	}

	public SchemeTree setChild(List<SchemeTree> child) {
		this.children = child;
		return this;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public SchemeTree setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
		return this;
	}

}

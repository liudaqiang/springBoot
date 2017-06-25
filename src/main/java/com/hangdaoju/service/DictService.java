package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.core.Page;
import com.hangdaoju.model.Dict;

public interface DictService {
	//新增字典
	public boolean save(Dict dict);
	//修改字典
	public boolean updateById(Dict dict);
	//根据项类型查询列表
	public List<Dict> findByCondition(Dict dict);
	//删除字典
	public void delete(Dict dict);
	//根据id查询
	public Dict findById(String id);
	//分页条件查询
	public Page<Dict> findPageByCondition(Page<Dict> page,Dict dict);
}

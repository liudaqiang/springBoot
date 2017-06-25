package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.core.Page;
import com.hangdaoju.dao.DictDao;
import com.hangdaoju.model.Dict;
import com.hangdaoju.service.DictService;
/**
 * 字典Service层
 * @author l.q
 *
 */
@Service
public class DictServiceImpl implements DictService{
	@Autowired
	private DictDao dictDao;

	@Override
	public boolean save(Dict dict) {
		List<Dict> dictList = dictDao.findByCondition(dict);
		if(dictList.size() > 0){
			return false;
		}
		dictDao.save(dict);
		return true;
	}

	@Override
	public boolean updateById(Dict dict) {
		List<Dict> dictList = dictDao.findByCondition(dict);
		if(dictList.size() > 0){
			return false;
		}
		dictDao.updateById(dict.getId(), dict);
		return true;
	}

	@Override
	public List<Dict> findByCondition(Dict dict) {
		return dictDao.findByCondition(dict);
	}
	
	@Override
	public Page<Dict> findPageByCondition(Page<Dict> page,Dict dict){
		return dictDao.findPage(page, dict);
	}
	@Override
	public Dict findById(String id){
		return dictDao.findById(id);
	}
	@Override
	public void delete(Dict dict) {
		dictDao.delete(dict);
	}
}

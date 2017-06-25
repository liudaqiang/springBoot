package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.DictDao;
import com.hangdaoju.model.Dict;
/**
 * 字典实现类
 * @author l.q
 *
 */
@Repository
public class DictDaoImpl extends BaseMongoDaoImpl<Dict> implements DictDao{
}

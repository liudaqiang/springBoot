package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.SchemeDao;
import com.hangdaoju.model.Scheme;

@Repository
public class SchemeDaoImpl extends BaseMongoDaoImpl<Scheme>implements SchemeDao {

}

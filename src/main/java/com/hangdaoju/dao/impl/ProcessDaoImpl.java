package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.ProcessDao;
import com.hangdaoju.model.Process;

@Repository
public class ProcessDaoImpl extends BaseMongoDaoImpl<Process>implements ProcessDao {

}

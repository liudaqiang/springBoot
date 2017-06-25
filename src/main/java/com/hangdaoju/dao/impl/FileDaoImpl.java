package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.FileDao;
import com.hangdaoju.model.File;

@Repository
public class FileDaoImpl extends BaseMongoDaoImpl<File>implements FileDao {

}

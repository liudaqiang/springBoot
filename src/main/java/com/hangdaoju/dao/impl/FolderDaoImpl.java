package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.FolderDao;
import com.hangdaoju.model.Folder;

@Repository
public class FolderDaoImpl extends BaseMongoDaoImpl<Folder>implements FolderDao {
}

package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.ProjectDao;
import com.hangdaoju.model.Project;

@Repository
public class ProjectDaoImpl extends BaseMongoDaoImpl<Project> implements ProjectDao{
}
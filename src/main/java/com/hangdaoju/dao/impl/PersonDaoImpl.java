package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.PersonDao;
import com.hangdaoju.model.Person;
/**
 * 用户dao层实现类
 * @author l.q
 *
 */
@Repository
public class PersonDaoImpl extends BaseMongoDaoImpl<Person> implements PersonDao{
}

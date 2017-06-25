package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.CompanyDao;
import com.hangdaoju.model.Company;

/**
 * 公司dao层实现
 * @author l.q
 *
 */
@Repository
public class CompanyDaoImpl extends BaseMongoDaoImpl<Company> implements CompanyDao{
}

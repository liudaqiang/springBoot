package com.hangdaoju.dao.impl;

import org.springframework.stereotype.Repository;

import com.hangdaoju.core.mongo.dao.impl.BaseMongoDaoImpl;
import com.hangdaoju.dao.NodeDao;
import com.hangdaoju.model.Node;
/**
 * 工程节点dao层
 * @author l.q
 *
 */
@Repository
public class NodeDaoImpl  extends BaseMongoDaoImpl<Node> implements NodeDao{
}

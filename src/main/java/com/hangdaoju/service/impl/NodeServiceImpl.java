package com.hangdaoju.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangdaoju.dao.NodeDao;
import com.hangdaoju.model.Node;
import com.hangdaoju.service.NodeService;

/**
 * 工程节点service层
 * 
 * @author l.q
 *
 */
@Service
public class NodeServiceImpl implements NodeService {
	@Autowired
	private NodeDao nodeDao;

	@Override
	public void add(Node node) {
		nodeDao.save(node);
	}

	@Override
	public void update(Node node) {
		nodeDao.updateById(node.getId(), node);
	}

	@Override
	public void delete(Node node) {
		nodeDao.delete(node);
	}

	@Override
	public List<Node> findByCondition(Node node) {
		return nodeDao.findByCondition(node);
	}

	@Override
	public Node find(String id) {
		// TODO Auto-generated method stub
		return nodeDao.findById(id);
	}
}

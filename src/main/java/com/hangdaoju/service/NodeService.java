package com.hangdaoju.service;

import java.util.List;

import com.hangdaoju.model.Node;

public interface NodeService {
	// 新增工程节点
	public void add(Node node);

	// 修改工程节点
	public void update(Node node);

	// 删除工程节点
	public void delete(Node node);

	// 查询工程节点
	public List<Node> findByCondition(Node node);

	public Node find(String id);
}

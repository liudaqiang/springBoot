package com.hangdaoju.api;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hangdaoju.core.ResponseModel;
import com.hangdaoju.model.Node;
import com.hangdaoju.service.NodeService;
import com.hangdaoju.util.GetMapUtils;
/**
 * 工程节点Controller层
 * @author l.q
 *
 */
@Controller
@RequestMapping("/node")
public class NodeController {
	@Autowired
	private NodeService nodeService; 
	/**
	 * 根据工程查询工程节点列表
	 * 必须字段(projectId,currentUser)
	 * @return
	 */
	@PostMapping("/list")
	@ResponseBody
	public ResponseModel findByCondition(Node node){
		if(StringUtils.isNotBlank(node.getProjectId())){
			List<Node> nodeList = nodeService.findByCondition(node);
			return new ResponseModel(200,GetMapUtils.getMap("nodeList", nodeList),"查询成功");
		}
		return new ResponseModel(300,null,"工程id不能为空");
	}
}

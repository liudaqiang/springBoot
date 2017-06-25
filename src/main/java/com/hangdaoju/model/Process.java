package com.hangdaoju.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hangdaoju.core.customAnnotation.QueryField;
import com.hangdaoju.core.customEnum.QueryType;
import com.hangdaoju.util.excel.annotation.ExcelField;

/**
 * 流程
 */
public class Process extends BaseModel {
	@Id
	@QueryField
	private String id;
	@QueryField(type = QueryType.LIKE)
	private String name;// 流程名称
	@QueryField
	private String content;// 流程内容
	@QueryField
	private String description;// 流程描述
	@Field("time_consuming")
	@QueryField
	private String timeConsuming;// 流程总耗时
	@QueryField
	private String accountId;// 节点账户编号
	@Field("finish_time")
	@QueryField
	private String finishTime;// 节点任务完成时间
	@Field("approval_result")
	@QueryField
	private String approvalResult;// 节点审批结果
	@QueryField
	private String attachment;// 流程附件url

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@ExcelField(title = "流程名称")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@ExcelField(title = "流程内容")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@ExcelField(title = "流程描述")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(String timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String account) {
		this.accountId = account;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	@ExcelField(title = "流程审批结果")
	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
}

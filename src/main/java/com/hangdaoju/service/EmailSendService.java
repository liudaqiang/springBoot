package com.hangdaoju.service;

import com.hangdaoju.core.Global;
import com.hangdaoju.util.EmailUtil;
import com.hangdaoju.util.EncryptUtils;

/**
 * 多线程发送
 * @author lq
 *
 */
public class EmailSendService implements Runnable{
	private String getScheme;// 获取http
	private String getServerName;// 获取ip
	private int getServerPort;// 获取端口
	private String getProjectName;// 获取项目名称
	private String userName;//获取用户名
	private String email;// 获取发送email
	
	public EmailSendService(String getScheme,String getServerName,int getServerPort,String getProjectName,String userName,String email){
		this.getScheme=getScheme;
		this.getServerName=getServerName;
		this.getServerPort=getServerPort;
		this.getProjectName=getProjectName;
		this.userName=userName;
		this.email=email;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		EmailUtil mail = new EmailUtil();
		//拼接处一个我们可以访问的url
		String url = getContentString(userName, getScheme, getServerName, getServerPort,
				getProjectName);
		mail.sendEmail(email, Global.getCuntomConfig("emailPeople"), Global.getCuntomConfig("emailTheme"), url);
	}
	/**
	 * 邮件推送内容
	 * 
	 * @param userName 用户名称 
	 * @param getScheme 协议
	 * @param getServerName IP地址
	 * @param getServerPort 端口
	 * @param getProjectName 项目名称
	 * @return
	 */
	private String getContentString(String userName, String getScheme, String getServerName, int getServerPort,
			String getProjectName) {
		StringBuffer sb = new StringBuffer();
		sb.append("userName=");
		sb.append(userName);
		sb.append("&flag=");
		sb.append("backPassword");
		sb.append("&backTime=");
		sb.append(System.currentTimeMillis());
		String param = null;
		try {
			//对传入参数进行加盐加密
			param = EncryptUtils.Encrypt3DES(sb.toString(), Global.getCuntomConfig("saltKey"));
		} catch (Exception e) {
			e.printStackTrace();

		}
		// ***************
		String url = "<p>亲爱的" + userName + "请点击如下找回密码";
		url += getScheme + "://" + getServerName + ":" + getServerPort + "/" + getProjectName
				+ "/person/emailPassword?param=" + param;
		url += "<P>感谢对我们的支持！邮件有效时间10分钟</P>" + "<p>(这是一封自动产生的email，请勿回复。)</P>";
		return url;
	}
}

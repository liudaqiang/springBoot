<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!-- 屏蔽tomcat 自带的 EL表达式 -->
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	请求成功
	用户名 ${userName}
	${pageContext}1
	${pageContext.request.contextPath}2
	<form action="${pageContext.request.contextPath}/person/updatePasswordByEmal" method="POST">
		用户名：<input type="text" name="userName" value="${userName}">
		密码：  <input type="text" name="password" value="">
		<input type="submit" value="确定">
	</form>
</body>
</html>
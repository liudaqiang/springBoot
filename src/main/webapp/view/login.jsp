<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="layout/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录</title>
		<link rel="stylesheet" href="${ctxStatic}/adminCss/reset.css" />
		<link rel="stylesheet" href="${ctxStatic}/adminCss/public.css" />
		<style>	
			.head{
				width:100%;height:60px;
				position:fixed;
				top:0px;bottom:0px;
				left:0px;right:0px;
				z-index:200;
			}
			.container{
				width:100%;height:100%;
				position:fixed;
				top:60px;bottom:0px;
				left:0px;right:0px;
				background-image:url('${ctxStatic}/img/hdj2.jpg');
				background-repeat:no-repeat;
				background-size:cover;
			}
			.modal{
				width:100%;height:100%;
				position:fixed;
				top:0px;bottom:0px;
				left:0px;right:0px;
				background:rgba(77,77,77,.6);
			}
			.modal-content{
				width:610px;height:340px;
				margin:0px auto;
				padding:20px 75px;
				position:relative;
				top:50%;
				margin-top:-190px;
				background:#fff;
				border-radius:3px;
			}
			.modal-content h4{
				height:30px;
				font-size:24px;
				color:#333;
				letter-spacing:4px;
				font-weight:normal;
				text-align: center;
				margin:10px 0px;
			}
			#hdj-login input{
				width:563px;
				height:30px;
				font-size:16px;
				padding:4px 5px 4px 40px;
				border:1px solid #2BC3F5;
				margin:20px 0px;
				color:#2BC3F5;
				font-weight:normal;
			}
			#hdj-login .uname input{background:url(${ctxStatic}/img/hdj2.jpg) no-repeat 10px 8px;}
			#hdj-login .password input{background:url(${ctxStatic}/img/hdj2.jpg) no-repeat 10px 8px;}
			.hdj-btn{
				width:100%;
				height:46px;
				font-size:20px;
				padding:4px 0px;
				margin:15px 0px;
				color:#fff;
				background:#2BC3F5;
				outline:none;border:none;
				border-radius:3px;
				box-shadow:0px 0px 5px #2BC3F5;
			}
		</style>
	</head>
	<body>
		<div class="head" style="">
			<h4><img src="${ctxStatic}/img/hdj1.jpg" alt="" />项目管理系统</h4>
		</div>
		<div class="container">
			<div class="modal">
				<div class="modal-content">
					<h4>后台系统登录</h4>
					<form id="hdj-login" action="${ctx}/admin/person/login" method="POST">
						<div class="uname">
							<input type="text" name="userName" placeholder="请输入用户名"/>
						</div>
						<div class="password">
							<input type="password" name="password" placeholder="请输入密码" />
						</div>
						<button class="hdj-btn" type="submit">立即登录</button>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
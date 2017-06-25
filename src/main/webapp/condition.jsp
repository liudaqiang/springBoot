<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>500错误页面 - Bootstrap后台管理系统模版Ace下载</title>
		<meta name="keywords" content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
		<meta name="description" content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctxStatic}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-skins.min.css" />
		<script src="${ctxStatic}/assets/js/ace-extra.min.js"></script>
	</head>

	<body>
	<%@ include file="/view/layout/top.jsp"%>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<%@ include file="/view/layout/left.jsp"%>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">错误页面</a>
							</li>
							<li class="active">Error 400</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="error-container">
									<div class="well">
										<h1 class="grey lighter smaller">
											<span class="blue bigger-125">
												<i class="icon-random"></i>
												400
											</span>
											传入参数异常
										</h1>

										<hr />
										<h3 class="lighter smaller">
											我们正在火速
											<i class="icon-wrench icon-animated-wrench bigger-125"></i>
											维修!
										</h3>

										<div class="space"></div>

										<div>
											<h4 class="lighter smaller">错误原因如下:</h4>

											<ul class="list-unstyled spaced inline bigger-110 margin-15">
												<li>
													<i class="icon-hand-right blue"></i>
													${error}
												</li>
											</ul>
										</div>

										<hr />
										<div class="space"></div>

										<div class="center">
											<a href="#" class="btn btn-grey" onclick="window.history.go(-1)">
												<i class="icon-arrow-left"></i>
												返回上一页
											</a>
											<!-- <a href="#" class="btn btn-primary" onclick="window.history.go(-1)">
												<i class="icon-dashboard"></i>
												返回到首页
											</a> -->
										</div>
									</div>
								</div>

								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctxStatic}/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctxStatic}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctxStatic}/assets/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/assets/js/typeahead-bs2.min.js"></script>
		<script src="${ctxStatic}/assets/js/ace-elements.min.js"></script>
		<script src="${ctxStatic}/assets/js/ace.min.js"></script>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

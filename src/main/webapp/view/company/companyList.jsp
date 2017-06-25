<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>组织机构管理</title>
		<meta name="keywords" content="航道局组织机构管理" />
		<meta name="description" content="航道局组织机构管理" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="${ctxStatic}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-skins.min.css" />
		<script src="${ctxStatic}/assets/js/ace-extra.min.js"></script>
	</head>
	<body>
		<%@ include file="../layout/top.jsp"%>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<%@ include file="../layout/left.jsp"%>

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
								<a href="#">组织架构管理</a>
							</li>
							<li class="active">组织架构管理</li>
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
						<div class="page-header">
							<h1>
								组织架构管理
								<small>
									<i class="icon-double-angle-right"></i>
								</small>
								<button class="btn btn-primary btn-sm" style="float:right;margin-right:30px;" onclick="javascript:window.location.href='${ctx}/admin/company/edit'">新增公司</button>
							</h1>
							
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-sm-6">
										<div class="widget-box">
											<div class="widget-header header-color-blue2">
												<h4 class="lighter smaller">组织架构管理</h4>
											</div>

											
														<div class="widget-body">
											  <div class="widget-main padding-8">
												<div id="tree1" class="tree tree-selectable">
												  <div class="tree-folder" style="display:none;">
													<div class="tree-folder-header">
													  <i class="icon-plus"></i>
													  <div class="tree-folder-name"></div>
													</div>
													<div class="tree-folder-content"></div>
													<div class="tree-loader" style="display: none;"></div>
												  </div>
												  <div class="tree-item" style="display:none;">
													<i class="icon-remove"></i>
													<div class="tree-item-name"></div>
												  </div>
												  <c:if test="${company !=null}">
														 <c:set var="company" value="${company}" scope="request"/>
														 <jsp:include page="companyMenu.jsp"/>
												  </c:if>
												</div>
											  </div>
											</div>
										</div>
									</div>
										</div>
									</div>
								</div>
								<script type="text/javascript">
									var $assets = "assets";//this will be used in fuelux.tree-sampledata.js
								</script>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctxStatic}/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctxStatic}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${ctxStatic}/assets/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/assets/js/typeahead-bs2.min.js"></script>
		<script src="${ctxStatic}/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>
		<script src="${ctxStatic}/assets/js/fuelux/fuelux.tree.min.js"></script>
		<script src="${ctxStatic}/assets/js/ace-elements.min.js"></script>
		<script src="${ctxStatic}/assets/js/ace.min.js"></script>
		<script type="text/javascript">
			jQuery(function($){
			$('.icon-minus').click(function(){
				$(this).parent().siblings('.tree-folder-content').slideToggle();
				if($(this).parent().children('i').is('.icon-minus')){
					$(this).parent().children('i').attr('class','icon-plus');
				}else{
					$(this).parent().children('i').attr('class','icon-minus');
				}
			});

			$(".tree-item").mouseover(function(){
				$(this).children(".closeX").text('x');
			});
			$(".tree-item").mouseout(function(){
				$(this).children(".closeX").text('');
			});
});
		</script>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

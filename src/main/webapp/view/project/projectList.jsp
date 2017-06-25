<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目风采管理</title>
<meta name="keywords" content="航道局" />
<meta name="description" content="航道局" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${ctxStatic}/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="${ctxStatic}/adminCss/jquery-confirm.css" />
<script src="${ctxStatic}/assets/js/ace-extra.min.js"></script>
</head>

<body>
	<%@ include file="../layout/top.jsp"%>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> <span
				class="menu-text"></span>
			</a>

			<%@ include file="../layout/left.jsp"%>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">项目管理</li>
					</ul>
					<!-- .breadcrumb -->

					<div class="nav-search" id="nav-search">
						<form class="form-search"></form>
					</div>
					<!-- #nav-search -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1 style="display: inline-block;">
							项目管理 <small> <i class="icon-double-angle-right"></i> 项目列表
							</small>
						</h1>
						<button class="btn btn-primary btn-sm"
							style="float: right; margin-right: 30px;"
							onclick="javascript:window.location.href='${ctx}/admin/project/edit'">创建项目</button>
					</div>
					<!-- /.page-header -->
					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->

							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table id="sample-table-1"
											class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>id</th>
													<th>名称</th>
													<th class="hidden-480">项目大小</th>
													<th>项目地区</th>
													<th></th>
												</tr>
											</thead>

											<tbody>
												<c:forEach items="${projectList}"
													var="project">
													<tr>
														<td>${project.id}</td>
														<td>${project.name}</td>
														<td class="hidden-480">${project.scale}</td>
														<!-- 
															<c:set var="aaa" value="${project.id}"></c:set>
															<td>${companyMap.aaa.name}</td>
															-->
														<td>${project.local}</td>

														<td>
															<div
																class="visible-md visible-lg hidden-sm hidden-xs btn-group">

																<button class="btn btn-xs btn-info"
																	onclick="javascript:window.location.href='${ctx}/admin/project/edit?id=${project.id}'">
																	<i class="icon-edit bigger-120"></i>
																</button>

																<button class="btn btn-xs btn-danger"
																	onclick="deletePerson('${project.id}')">
																	<i class="icon-trash bigger-120"></i>
																</button>

															</div>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /span -->
							</div>
							<!-- /row -->
						</div>
						<!-- /.page-content -->
						<div class="container">
							<div class="center">
								<ul class="pagination">
									<li class="disabled"><a href="#"> <i
											class="icon-double-angle-left"></i>
									</a></li>
									<c:forEach begin="1" end="${pageCount}" var="page">
										<li class="active"><a
											href="${ctx}/admin/project/list?father=400&children=4001&page=${page}">${page}</a>
										</li>
									</c:forEach>
									<li><a href="#"> <i class="icon-double-angle-right"></i>
									</a></li>
								</ul>
							</div>

							<p></p>
							<ul class="pager">
								<li class="previous"><a href="#">← 后退</a></li>

								<li class="next"><a href="#">前进 →</a></li>
							</ul>

						</div>
					</div>
					<!-- /.main-content -->

				</div>
				<!-- /.main-container-inner -->

				<a href="#" id="btn-scroll-up"
					class="btn-scroll-up btn btn-sm btn-inverse"> <i
					class="icon-double-angle-up icon-only bigger-110"></i>
				</a>
			</div>
			<script type="text/javascript">
				window.jQuery
						|| document
								.write("<script src='${ctxStatic}/assets/js/jquery-2.0.3.min.js'>"
										+ "<"+"script>");
			</script>
			<script type="text/javascript">
				if ("ontouchend" in document)
					document
							.write("<script src='${ctxStatic}/assets/js/jquery.mobile.custom.min.js'>"
									+ "<"+"script>");
			</script>
			<%@ include file="../layout/common.jsp"%>
			<script type="text/javascript">
				function deletePerson(projectId) {
					$
							.confirm({
								title : '是否确定删除?',
								content : '删除后数据将无法恢复',
								autoClose : 'cancelAction|10000',
								escapeKey : 'cancelAction',
								buttons : {
									confirm : {
										btnClass : 'btn-redss',
										text : '删除',
										action : function() {
											window.location.href = '${ctx}'
													+ "/admin/project/delete?id="
													+ projectId;
										}
									},
									cancelAction : {
										text : '取消',
										action : function() {
											$.alert('取消操作!');
										}
									}
								}
							});
				}
			</script>
			<script src="${ctxStatic}/common/jquery-confirm.js"></script>
			<div style="display: none">
				<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
					language='JavaScript' charset='gb2312'></script>
			</div>
</body>
</html>


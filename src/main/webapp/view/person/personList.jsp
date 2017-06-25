<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
			<meta charset="utf-8" />
		<title>用户列表</title>
		<meta name="keywords" content="航道局" />
		<meta name="description" content="航道局" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="${ctxStatic}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctxStatic}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
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
							<li class="active">控制台</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1 style="display:inline-block;">
								用户管理
								<small>
									<i class="icon-double-angle-right"></i>
									用户列表
								</small>
							</h1>
							<button class="btn btn-primary btn-sm" style="float:right;margin-right:30px;" onclick="javascript:window.location.href='${ctx}/admin/person/edit'">创建用户</button>
						</div><!-- /.page-header -->
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table id="sample-table-1" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>id</th>
														<th>用户名</th>
														<th class="hidden-480">邮箱</th>
														<th>电话</th>
														<th>qq</th>
														<th></th>
													</tr>
												</thead>

												<tbody>
													<c:forEach items="${personList}" var="person">
														<tr>
															<td>${person.id}</td>
															<td>${person.userName}</td>
															<td class="hidden-480">${person.email}</td>
															<td>${person.phone}</td>
															<td>${person.qq}</td>
															<td>
															<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">

																<button class="btn btn-xs btn-info" onclick="javascript:window.location.href='${ctx}/admin/person/edit?id=${person.id}'">
																	<i class="icon-edit bigger-120"></i>
																</button>

																<button class="btn btn-xs btn-danger" onclick="deletePerson('${person.id}')">
																	<i class="icon-trash bigger-120"></i>
																</button>

															</div>
														</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div><!-- /.table-responsive -->
									</div><!-- /span -->
								</div><!-- /row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${ctxStatic}/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${ctxStatic}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<%@ include file="../layout/common.jsp"%>
		<script type="text/javascript">
			function deletePerson(personId){
				  $.confirm({
	                  title: '是否确定删除?',
	                  content: '删除后数据将无法恢复',
	                  autoClose: 'cancelAction|10000',
	                  escapeKey: 'cancelAction',
	                  buttons: {
	                      confirm: {
	                          btnClass: 'btn-redss',
	                          text: '删除',
	                          action: function () {
	                        	  window.location.href='${ctx}'+"/admin/person/delete?id="+personId;
	                          }
	                      },
	                      cancelAction: {
	                          text: '取消',
	                          action: function () {
	                              $.alert('取消操作!');
	                          }
	                      }
	                  }
	              });
			}
		</script>
		<script src="${ctxStatic}/adminJs/personAdmin.js"></script>
		<script src="${ctxStatic}/common/jquery-confirm.js"></script>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>


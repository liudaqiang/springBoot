<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Bootstrap表单组件 - Bootstrap后台管理系统模版Ace下载</title>
<meta name="keywords"
	content="Bootstrap模版,Bootstrap模版下载,Bootstrap教程,Bootstrap中文" />
<meta name="description"
	content="站长素材提供Bootstrap模版,Bootstrap教程,Bootstrap中文翻译等相关Bootstrap插件下载" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${ctxStatic}/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/chosen.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/datepicker.css" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/colorpicker.css" />
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-skins.min.css" />
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

						<li><a href="#">用户管理</a></li>
						<li class="active">用户修改</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							用户管理 <small> <i class="icon-double-angle-right"></i> 用户修改
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form id="formSubmit" class="form-horizontal" role="form"
								<c:if test="${person != null}">action="${ctx}/admin/person/update"</c:if>
								<c:if test="${person == null}">action="${ctx}/admin/person/add"</c:if>
								method="POST">
								<input type="hidden" value="${person.id}" name="id">

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 用户名* </label>

									<div class="col-sm-9">
										<input name="userName"
											<c:if test="${person != null}">disabled="disabled"</c:if>
											type="text" class="col-xs-10 col-sm-5"
											id="form-input-readonly" value="${person.userName}" />
									</div>
								</div>
								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="email"> email*</label>

									<div class="col-sm-9">
										<input type="text" id="email" placeholder="请输入email"
											name="email" value="${person.email}"
											class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 密码 *</label>

									<div class="col-sm-9">
										<input type="password" id="password" name="password"
											placeholder="请输入密码" value="${person.password}"
											class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 工号 *</label>

									<div class="col-sm-9">
										<input type="text" id="jobCode" name="jobCode"
											placeholder="请输入工号" value="${person.jobCode}"
											class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 头像 *</label>

									<div class="col-sm-9">
										<img id="avatar-img" alt="头像" src="${person.avatar}"
											style="width: 300px;"> <input type="file"
											id="picture-upload"> <input type="hidden" id="avatar"
											name="avatar" value="${person.avatar}">
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 性别*</label>

									<div class="col-sm-9">
										<select class="col-xs-10 col-sm-5" id="sex"
											data-placeholder="Choose a Country..." name="sex">
											<option value="1">男</option>
											<option value="2">女</option>
										</select>
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="phone"> 电话 *</label>

									<div class="col-sm-9">
										<input type="text" id="phone" name="phone"
											placeholder="请输入电话号码" value="${person.phone}"
											class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 所在公司*</label>

									<div class="col-sm-9">
										<select class="col-xs-10 col-sm-5" id="companyId"
											data-placeholder="Choose a Country..." name="companyId">
											<c:forEach items="${companyList}" var="oneCompany">
												<option
													<c:if test="${person.companyId == oneCompany.id}">selected="selected"</c:if>
													value="${oneCompany.id}">${oneCompany.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 职位* </label>

									<div class="col-sm-9">
										<input type="text" id="position" name="position"
											placeholder="请输入职位" value="${person.position}"
											class="col-xs-10 col-sm-5" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> qq号 </label>

									<div class="col-sm-9">
										<input type="text" id="qq" name="qq" placeholder="请输入qq号"
											value="${person.qq}" class="col-xs-10 col-sm-5" />
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-info" type="submit">
											<i class="icon-ok bigger-110"></i> 提交
										</button>

										&nbsp; &nbsp; &nbsp;
										<button class="btn" onclick="javascript:history.go(-1);">
											<i class="icon-undo bigger-110"></i> 返回
										</button>
									</div>
								</div>
								<div class="hr hr-24"></div>
							</form>
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${ctxStatic}/assets/js/jquery-2.0.3.min.js'>"
								+ "<"+"/script>");
	</script>
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${ctxStatic}/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<%@ include file="../layout/common.jsp"%>
	<script src="${ctxStatic}/assets/js/chosen.jquery.min.js"></script>
	<script src="${ctxStatic}/assets/js/jquery.validate.min.js"></script>
	<script src="${ctxStatic}/assets/js/fuelux/fuelux.spinner.min.js"></script>
	<script
		src="${ctxStatic}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script
		src="${ctxStatic}/assets/js/date-time/bootstrap-timepicker.min.js"></script>
	<script src="${ctxStatic}/assets/js/date-time/moment.min.js"></script>
	<script src="${ctxStatic}/assets/js/date-time/daterangepicker.min.js"></script>
	<script src="${ctxStatic}/assets/js/bootstrap-colorpicker.min.js"></script>
	<script src="${ctxStatic}/assets/js/jquery.knob.min.js"></script>
	<script src="${ctxStatic}/assets/js/jquery.autosize.min.js"></script>
	<script src="${ctxStatic}/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
	<script src="${ctxStatic}/assets/js/jquery.maskedinput.min.js"></script>
	<script src="${ctxStatic}/assets/js/bootstrap-tag.min.js"></script>
	<script type="text/javascript">
		jQuery(function($) {
			$().ready(function() {
				$("#formSubmit").validate();
			});
			jQuery.validator
					.addMethod(
							"phone",
							function(value, element) {
								return this.optional(element)
										|| /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1}))+\d{8})$/
												.test(value);
							}, "请输入正确的手机号.");

			$('#formSubmit')
					.validate(
							{
								errorElement : 'div',
								errorClass : 'help-block',
								focusInvalid : false,
								rules : {
									email : {
										required : true,
										email : true
									},
									password : {
										required : true,
										minlength : 6
									},
									userName : {
										required : true,
										maxlength : 20
									},
									phone : {
										required : true,
										phone : 'required'
									},
									jobCode : {
										required : true,
										maxlength : 20
									},
									position : {
										required : true,
										maxlength : 20
									},
									qq : {
										required : false,
										minlength : 5,
										maxlength : 15
									}

								},
								messages : {
									email : {
										required : "必须字段.",
										email : "请输入正确的邮箱."
									},
									password : {
										required : "必须字段.",
										minlength : "密码长度至少6位."
									},
									userName : {
										required : "必须字段",
										maxlength : "用户名长度不能超过20位"
									},
									phone : {
										required : "必须字段"
									},
									jobCode : {
										required : "必须字段",
										maxlength : "工号长度不能大于20"
									},
									position : {
										required : "必须字段",
										maxlength : "职位名称长度不能大于20"
									},
									qq : {
										minlength : "qq最小不能低于5",
										maxlength : "qq最大不能高于15"
									}
								},

								invalidHandler : function(event, validator) { //display error alert on form submit   
									$('.alert-danger', $('.login-form')).show();
								},

								highlight : function(e) {
									$(e).closest('.form-group').removeClass(
											'has-info').addClass('has-error');
								},

								success : function(e) {
									$(e).closest('.form-group').removeClass(
											'has-error').addClass('has-info');
									$(e).remove();
								},

								errorPlacement : function(error, element) {
									if (element.is(':checkbox')
											|| element.is(':radio')) {
										var controls = element
												.closest('div[class*="col-"]');
										if (controls.find(':checkbox,:radio').length > 1)
											controls.append(error);
										else
											error.insertAfter(element.nextAll(
													'.lbl:eq(0)').eq(0));
									} else if (element.is('.select2')) {
										error
												.insertAfter(element
														.siblings('[class*="select2-container"]:eq(0)'));
									} else if (element.is('.chosen-select')) {
										error
												.insertAfter(element
														.siblings('[class*="chosen-container"]:eq(0)'));
									} else
										error.insertAfter(element.parent());
								},

								submitHandler : function(form) {
									form.submit();
								},
								invalidHandler : function(form) {
								}
							});

		//图片上传  by Hervey Hall <mail@herveyhall.cf>
			(function(uploadEleId, paramEleId, shownEleId) {
				var picUpload = document.getElementById(uploadEleId);
				picUpload.onchange = function() {
					var pic = picUpload.cloneNode(true);
					pic.setAttribute("name", "file");
					var uploadForm = document.createElement("form");
					uploadForm.setAttribute("method", "POST");
					uploadForm.setAttribute("action", "/picture/upload");
					uploadForm.setAttribute("enctype", "multipart/form-data");
					uploadForm.appendChild(pic);
					$.ajax({
						type : 'POST',
						url : '/picture/upload',
						data : new FormData(uploadForm),
						processData : false,
						contentType : false,
						success : function(res) {
							document.getElementById(paramEleId).setAttribute(
									"value", res["data"]["src"]);
							document.getElementById(shownEleId).setAttribute(
									"src", res["data"]["src"]);
						}
					});
				}
			})('picture-upload', "avatar", "avatar-img");
		});
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>

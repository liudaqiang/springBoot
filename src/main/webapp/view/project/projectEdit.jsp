<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>修改项目</title>
<meta name="keywords" content="航道局后台管理系统" />
<meta name="description" content="航道局后台管理系统" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- basic styles -->

<link href="${ctxStatic}/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/font-awesome.min.css" />

<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

<!-- page specific plugin styles -->

<link rel="stylesheet"
	href="${ctxStatic}/assets/css/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/chosen.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/datepicker.css" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/bootstrap-timepicker.css" />
<link rel="stylesheet"
	href="${ctxStatic}/assets/css/daterangepicker.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/colorpicker.css" />

<!-- fonts -->

<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

<!-- ace styles -->

<link rel="stylesheet" href="${ctxStatic}/assets/css/ace.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctxStatic}/assets/css/ace-skins.min.css" />

<!--[if lte IE 8]>
		  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
		<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

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

						<li><a href="#">项目管理</a></li>
						<li class="active">项目修改</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							项目管理 <small> <i class="icon-double-angle-right"></i> 项目修改
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-horizontal" role="form"
								<c:if test="${project != null}">action="${ctx}/admin/project/update"</c:if>
								<c:if test="${project == null}">action="${ctx}/admin/project/add"</c:if>
								method="POST" id="formSubmit">
								<input type="hidden" value="${project.id}" name="id">

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 名称 </label>

									<div class="col-sm-9">
										<input name="name" type="text" class="col-xs-10 col-sm-5"
											id="form-input-readonly" value="${project.name}" />
									</div>
								</div>



								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 图片 *</label>

									<div class="col-sm-9">
										<img id="picture-img" alt="图片" src="${project.picture}"
											style="width: 300px;"> <input type="file"
											id="picture-upload"> <input type="hidden"
											id="picture" name="picture" value="${project.picture}">
									</div>
								</div>


								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 所属公司 *</label> <select
										class="col-xs-10 col-sm-5 chosen-select"
										id="form-field-select-3" data-placeholder="选择类型"
										name="companyId">
										<c:forEach items="${companyList}" var="company">
											<option value="${company.id}"
												<c:if test="${company.id == project.companyId}">selected="selected"</c:if>>${company.name}</option>
										</c:forEach>
									</select>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 父项目*</label> <select
										class="col-xs-10 col-sm-5 chosen-select"
										id="form-field-select-3" data-placeholder="选择父项目"
										name="fatherProjectId">
										<c:forEach items="${projectList}" var="oneProject">
											<option value="${oneProject.id}"
												<c:if test="${oneProject.id == project.fatherProjectId}">selected="selected"</c:if>>${oneProject.name}</option>
										</c:forEach>
									</select> <input type="hidden" name="fatherProjectName" value="无">
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 责任人*</label> <select
										class="col-xs-10 col-sm-5 chosen-select"
										id="form-field-select-3" data-placeholder="选择责任人"
										name="managerPerson">
										<c:forEach items="${personList}" var="person">
											<option value="${person.id}"
												<c:if test="${person.id == project.managerPerson}">selected="selected"</c:if>>${person.name}</option>
										</c:forEach>
									</select>
								</div>

								<div class="space-4"></div>


								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 项目规模*</label> <select
										class="col-xs-10 col-sm-5 chosen-select"
										id="form-field-select-3" data-placeholder="选择类型" name="scale">
										<option value="超特大型"
											<c:if test="${'超特大型' == project.scale}">selected="selected"</c:if>>超特大型</option>
										<option value="特大型"
											<c:if test="${'特大型' == project.scale}">selected="selected"</c:if>>特大型</option>
										<option value="大型"
											<c:if test="${'大型' == project.scale}">selected="selected"</c:if>>大型</option>
										<option value="中小型"
											<c:if test="${'中小型' == project.scale}">selected="selected"</c:if>>中小型</option>
									</select>
								</div>

								<div class="space-4"></div>


								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 项目类型*</label> <select
										class="col-xs-10 col-sm-5 chosen-select"
										id="form-field-select-3" data-placeholder="选择类型"
										name="projectType">
										<option value="1"
											<c:if test="${'1' == project.projectType}">selected="selected"</c:if>>疏浚吹填类</option>
										<option value="2"
											<c:if test="${'2' == project.projectType}">selected="selected"</c:if>>筑堤类</option>
										<option value="3"
											<c:if test="${'3' == project.projectType}">selected="selected"</c:if>>市政类</option>
										<option value="4"
											<c:if test="${'4' == project.projectType}">selected="selected"</c:if>>水工建筑类</option>
										<option value="5"
											<c:if test="${'5' == project.projectType}">selected="selected"</c:if>>地基处理类</option>
									</select>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 项目地区*</label> <select
										class="col-xs-10 col-sm-5 chosen-select"
										id="form-field-select-3" data-placeholder="选择类型" name="local">
										<option value="东北地区"
											<c:if test="${'东北地区' == project.local}">selected="selected"</c:if>>东北地区</option>
										<option value="华北地区"
											<c:if test="${'华北地区' == project.local}">selected="selected"</c:if>>华北地区</option>
										<option value="华东地区"
											<c:if test="${'华东地区' == project.local}">selected="selected"</c:if>>华东地区</option>
										<option value="华中地区"
											<c:if test="${'华中地区' == project.local}">selected="selected"</c:if>>华中地区</option>
										<option value="华南地区"
											<c:if test="${'华南地区' == project.local}">selected="selected"</c:if>>华南地区</option>
										<option value="西北地区"
											<c:if test="${'西北地区' == project.local}">selected="selected"</c:if>>西北地区</option>
										<option value="港澳台地区 "
											<c:if test="${'港澳台地区 ' == project.local}">selected="selected"</c:if>>港澳台地区
										</option>
									</select>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 地址* </label>

									<div class="col-sm-9">
										<input name="address" type="text" class="col-xs-10 col-sm-5"
											id="form-input-readonly" value="${project.address}" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 经度* </label>

									<div class="col-sm-9">
										<input name="longitude" type="text" class="col-xs-10 col-sm-5"
											id="form-input-readonly" value="${project.longitude}" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 纬度* </label>

									<div class="col-sm-9">
										<input name="latitude" type="text" class="col-xs-10 col-sm-5"
											id="form-input-readonly" value="${project.latitude}" />
									</div>
								</div>

								<div class="space-4"></div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-1"> 描述*</label>


									<div id="sample" class="col-sm-9">
										<script type="text/javascript"
											src="http://js.nicedit.com/nicEdit-latest.js"></script>
										<script src="${ctxStatic}/nicEdit/nicEdit.js"></script>
										<script type="text/javascript">
											bkLib.onDomLoaded(function() {
												nicEditors.allTextAreas()
											});
										</script>
										<textarea name="description"
											style="width: 500px; height: 100px; overflow: scroll;">${project.description}</textarea>
									</div>

								</div>

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 文件筛选方案* </label>
									<div class="col-sm-9">
										<c:forEach items="${schemeList}" var="scheme"
											varStatus="status">
											<input name="schemeIds" type="checkbox"
												id="scheme-${status.count}" value="${scheme.id}"
												<c:forEach items="${project.schemeIds}" var="schemeId"><c:if test="${schemeId==scheme.id}">checked="checked"</c:if></c:forEach> />
											<label for="scheme-${status.count}">${scheme.name}</label>
										</c:forEach>
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
	<script src="${ctxStatic}/assets/js/fuelux/fuelux.spinner.min.js"></script>
	<script
		src="${ctxStatic}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
	<script
		src="${ctxStatic}/assets/js/date-time/bootstrap-timepicker.min.js"></script>
	<script src="${ctxStatic}/assets/js/jquery.validate.min.js"></script>
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

			$('#formSubmit')
					.validate(
							{
								errorElement : 'div',
								errorClass : 'help-block',
								focusInvalid : false,
								rules : {
									name : {
										required : true,
										maxlength : 20
									},
									picture : {
										required : true
									},
									companyId : {
										required : true
									},
									fatherProjectId : {
										required : true
									},
									managerPerson : {
										required : true
									},
									scale : {
										required : true
									},
									local : {
										required : true
									},
									address : {
										required : true,
										maxlength : 50
									},
									longitude : {
										required : true
									},
									latitude : {
										required : true
									}

								},
								messages : {
									name : {
										required : "必须字段.",
										maxlength : "不能超过20"
									},
									picture : {
										required : "必须字段."
									},
									companyId : {
										required : "必须字段"
									},
									fatherProjectId : {
										required : "必须字段"
									},
									managerPerson : {
										required : "必须字段"
									},
									scale : {
										required : "必须字段"
									},
									local : {
										required : "必须字段"
									},
									address : {
										required : "必须字段"
									},
									longitude : {
										required : "必须字段"
									},
									latitude : {
										required : "必须字段"
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

			$('#id-disable-check').on('click', function() {
				var inp = $('#form-input-readonly').get(0);
				if (inp.hasAttribute('disabled')) {
					inp.setAttribute('readonly', 'true');
					inp.removeAttribute('disabled');
					inp.value = "This text field is readonly!";
				} else {
					inp.setAttribute('disabled', 'disabled');
					inp.removeAttribute('readonly');
					inp.value = "This text field is disabled!";
				}
			});

			$(".chosen-select").chosen();
			$('#chosen-multiple-style').on('click', function(e) {
				var target = $(e.target).find('input[type=radio]');
				var which = parseInt(target.val());
				if (which == 2)
					$('#form-field-select-4').addClass('tag-input-style');
				else
					$('#form-field-select-4').removeClass('tag-input-style');
			});

			$('[data-rel=tooltip]').tooltip({
				container : 'body'
			});
			$('[data-rel=popover]').popover({
				container : 'body'
			});

			$('textarea[class*=autosize]').autosize({
				append : "\n"
			});
			$('textarea.limited').inputlimiter({
				remText : '%n character%s remaining...',
				limitText : 'max allowed : %n.'
			});

			$.mask.definitions['~'] = '[+-]';
			$('.input-mask-date').mask('99/99/9999');
			$('.input-mask-phone').mask('(999) 999-9999');
			$('.input-mask-eyescript').mask('~9.99 ~9.99 999');
			$(".input-mask-product").mask("a*-999-a999", {
				placeholder : " ",
				completed : function() {
					alert("You typed the following: " + this.val());
				}
			});

			$("#input-size-slider").css('width', '200px').slider(
					{
						value : 1,
						range : "min",
						min : 1,
						max : 8,
						step : 1,
						slide : function(event, ui) {
							var sizing = [ '', 'input-sm', 'input-lg',
									'input-mini', 'input-small',
									'input-medium', 'input-large',
									'input-xlarge', 'input-xxlarge' ];
							var val = parseInt(ui.value);
							$('#form-field-4').attr('class', sizing[val]).val(
									'.' + sizing[val]);
						}
					});

			$("#input-span-slider").slider(
					{
						value : 1,
						range : "min",
						min : 1,
						max : 12,
						step : 1,
						slide : function(event, ui) {
							var val = parseInt(ui.value);
							$('#form-field-5').attr('class', 'col-xs-' + val)
									.val('.col-xs-' + val);
						}
					});

			$("#slider-range")
					.css('height', '200px')
					.slider(
							{
								orientation : "vertical",
								range : true,
								min : 0,
								max : 100,
								values : [ 17, 67 ],
								slide : function(event, ui) {
									var val = ui.values[$(ui.handle).index() - 1]
											+ "";

									if (!ui.handle.firstChild) {
										$(ui.handle)
												.append(
														"<div class='tooltip right in' style='display:none;left:16px;top:-6px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>");
									}
									$(ui.handle.firstChild).show().children()
											.eq(1).text(val);
								}
							}).find('a').on('blur', function() {
						$(this.firstChild).hide();
					});

			$("#slider-range-max").slider({
				range : "max",
				min : 1,
				max : 10,
				value : 2
			});

			$("#eq > span").css({
				width : '90%',
				'float' : 'left',
				margin : '15px'
			}).each(function() {
				// read initial values from markup and remove that
				var value = parseInt($(this).text(), 10);
				$(this).empty().slider({
					value : value,
					range : "min",
					animate : true

				});
			});

			$('#id-input-file-1 , #id-input-file-2').ace_file_input({
				no_file : 'No File ...',
				btn_choose : 'Choose',
				btn_change : 'Change',
				droppable : false,
				onchange : null,
				thumbnail : false
			//| true | large
			//whitelist:'gif|png|jpg|jpeg'
			//blacklist:'exe|php'
			//onchange:''
			//
			});

			$('#id-input-file-3').ace_file_input({
				style : 'well',
				btn_choose : 'Drop files here or click to choose',
				btn_change : null,
				no_icon : 'icon-cloud-upload',
				droppable : true,
				thumbnail : 'small'//large | fit
				//,icon_remove:null//set null, to hide remove/reset button
				/**,before_change:function(files, dropped) {
					//Check an example below
					//or examples/file-upload.html
					return true;
				}*/
				/**,before_remove : function() {
					return true;
				}*/
				,
				preview_error : function(filename, error_code) {
					//name of the file that failed
					//error_code values
					//1 = 'FILE_LOAD_FAILED',
					//2 = 'IMAGE_LOAD_FAILED',
					//3 = 'THUMBNAIL_FAILED'
					//alert(error_code);
				}

			}).on('change', function() {
				//console.log($(this).data('ace_input_files'));
				//console.log($(this).data('ace_input_method'));
			});

			//dynamically change allowed formats by changing before_change callback function
			$('#id-file-format')
					.removeAttr('checked')
					.on(
							'change',
							function() {
								var before_change
								var btn_choose
								var no_icon
								if (this.checked) {
									btn_choose = "Drop images here or click to choose";
									no_icon = "icon-picture";
									before_change = function(files, dropped) {
										var allowed_files = [];
										for (var i = 0; i < files.length; i++) {
											var file = files[i];
											if (typeof file === "string") {
												//IE8 and browsers that don't support File Object
												if (!(/\.(jpe?g|png|gif|bmp)$/i)
														.test(file))
													return false;
											} else {
												var type = $.trim(file.type);
												if ((type.length > 0 && !(/^image\/(jpe?g|png|gif|bmp)$/i)
														.test(type))
														|| (type.length == 0 && !(/\.(jpe?g|png|gif|bmp)$/i)
																.test(file.name))//for android's default browser which gives an empty string for file.type
												)
													continue;//not an image so don't keep this file
											}

											allowed_files.push(file);
										}
										if (allowed_files.length == 0)
											return false;

										return allowed_files;
									}
								} else {
									btn_choose = "Drop files here or click to choose";
									no_icon = "icon-cloud-upload";
									before_change = function(files, dropped) {
										return files;
									}
								}
								var file_input = $('#id-input-file-3');
								file_input.ace_file_input('update_settings', {
									'before_change' : before_change,
									'btn_choose' : btn_choose,
									'no_icon' : no_icon
								})
								file_input.ace_file_input('reset_input');
							});

			$('#spinner1').ace_spinner({
				value : 0,
				min : 0,
				max : 200,
				step : 10,
				btn_up_class : 'btn-info',
				btn_down_class : 'btn-info'
			}).on('change', function() {
				//alert(this.value)
			});
			$('#spinner2').ace_spinner({
				value : 0,
				min : 0,
				max : 10000,
				step : 100,
				touch_spinner : true,
				icon_up : 'icon-caret-up',
				icon_down : 'icon-caret-down'
			});
			$('#spinner3').ace_spinner({
				value : 0,
				min : -100,
				max : 100,
				step : 10,
				on_sides : true,
				icon_up : 'icon-plus smaller-75',
				icon_down : 'icon-minus smaller-75',
				btn_up_class : 'btn-success',
				btn_down_class : 'btn-danger'
			});

			$('.date-picker').datepicker({
				autoclose : true
			}).next().on(ace.click_event, function() {
				$(this).prev().focus();
			});
			$('input[name=date-range-picker]').daterangepicker().prev().on(
					ace.click_event, function() {
						$(this).next().focus();
					});

			$('#timepicker1').timepicker({
				minuteStep : 1,
				showSeconds : true,
				showMeridian : false
			}).next().on(ace.click_event, function() {
				$(this).prev().focus();
			});

			$('#colorpicker1').colorpicker();
			$('#simple-colorpicker-1').ace_colorpicker();

			$(".knob").knob();

			//we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
			var tag_input = $('#form-field-tags');
			if (!(/msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()))) {
				tag_input.tag({
					placeholder : tag_input.attr('placeholder'),
					//enable typeahead by specifying the source array
					source : ace.variable_US_STATES,//defined in ace.js >> ace.enable_search_ahead
				});
			} else {
				//display a textarea for old IE, because it doesn't support this plugin or another one I tried!
				tag_input.after(
						'<textarea id="' + tag_input.attr('id') + '" name="'
								+ tag_input.attr('name') + '" rows="3">'
								+ tag_input.val() + '</textarea>').remove();
				//$('#form-field-tags').autosize({append: "\n"});
			}

			/////////
			$('#modal-form input[type=file]').ace_file_input({
				style : 'well',
				btn_choose : 'Drop files here or click to choose',
				btn_change : null,
				no_icon : 'icon-cloud-upload',
				droppable : true,
				thumbnail : 'large'
			})

			$('#modal-form').on('shown.bs.modal', function() {
				$(this).find('.chosen-container').each(function() {
					$(this).find('a:first-child').css('width', '210px');
					$(this).find('.chosen-drop').css('width', '210px');
					$(this).find('.chosen-search input').css('width', '200px');
				});
			})
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
						document.getElementById(shownEleId).setAttribute("src",
								res["data"]["src"]);
					}
				});
			}
		})('picture-upload', "picture", "picture-img");
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>修改动态</title>
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

						<li><a href="#">筛选方案管理</a></li>
						<li class="active">方案修改</li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							筛选方案管理 <small> <i class="icon-double-angle-right"></i>
								方案修改
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->
							<form id="scheme-form" class="form-horizontal" role="form"
								<c:if test="${scheme != null}">action="${ctx}/admin/scheme/update"</c:if>
								<c:if test="${scheme == null}">action="${ctx}/admin/scheme/add"</c:if>
								method="POST">
								<input type="hidden" value="${scheme.id}" name="id">

								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-input-readonly"> 名称 </label>

									<div class="col-sm-9">
										<input name="name" type="text" class="col-xs-10 col-sm-5"
											id="form-input-readonly" value="${scheme.name}" />
									</div>
								</div>
								<div class="space-4"></div>

								<div id="property-form-1" class="form-group">
									<label class="col-sm-3 control-label no-padding-right"
										for="form-field-2"> 所有属性</label>

									<div class="col-sm-4">
										<select style="width: 100%" class="col-xs-10 col-sm-5"
											data-placeholder="选择属性" name="properties">
											<option value="major"
												<c:if test="${scheme.properties[0] == 'major'}">selected="selected"</c:if>>专业</option>
											<option value="extName"
												<c:if test="${scheme.properties[0] == 'extName'}">selected="selected"</c:if>>扩展名</option>
											<option value="mark"
												<c:if test="${scheme.properties[0] == 'mark'}">selected="selected"</c:if>>标段</option>
											<option value="department"
												<c:if test="${scheme.properties[0] == 'department'}">selected="selected"</c:if>>部门</option>
											<option value="stage"
												<c:if test="${scheme.properties[0] == 'stage'}">selected="selected"</c:if>>工程阶段</option>
										</select>
									</div>
								</div>
								<c:forEach items="${scheme.properties}" var="property" begin="1"
									varStatus="status">
									<div id="property-form-${status.count+1}" class="form-group">
										<label class="col-sm-3 control-label no-padding-right"
											for="form-field-2"> 所有属性</label>

										<div class="col-sm-4">
											<select style="width: 100%" class="col-xs-10 col-sm-5"
												data-placeholder="选择属性" name="properties">
												<option value="major"
													<c:if test="${scheme.properties[status.count] == 'major'}">selected="selected"</c:if>>专业</option>
												<option value="extName"
													<c:if test="${scheme.properties[status.count] == 'extName'}">selected="selected"</c:if>>扩展名</option>
												<option value="mark"
													<c:if test="${scheme.properties[status.count] == 'mark'}">selected="selected"</c:if>>标段</option>
												<option value="department"
													<c:if test="${scheme.properties[status.count] == 'department'}">selected="selected"</c:if>>部门</option>
												<option value="stage"
													<c:if test="${scheme.properties[status.count] == 'stage'}">selected="selected"</c:if>>工程阶段</option>
											</select>
										</div>
										<button type="button" class="btn"
											onclick="$('#property-form-${status.count+1}').remove()">移除属性</button></div>
								</c:forEach>
								<button class="btn btn-info" id="add-property" type="button">新增属性</button>

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
		//动态添加和移除下拉框  by Hervey Hall <mail@herveyhall.cf>
		(function() {
			var propertyIndex = <c:choose><c:when test="${fn:length(scheme.properties)>1}">${fn:length(scheme.properties)}</c:when><c:otherwise>1</c:otherwise></c:choose>;
			var propertyAdder = document.getElementById("add-property");
			var remover = document.createElement("button");
			remover.type = "button";
			remover.setAttribute('class', 'btn');
			remover.innerHTML = "移除属性";
			propertyAdder.onclick = function() {
				var propertyForm = document.getElementById("property-form-"
						+ propertyIndex);
				while (!propertyForm) {
					propertyForm = document.getElementById("property-form-"
							+ --propertyIndex);
				}
				var newproperty = propertyForm.cloneNode(true);
				newproperty
						.removeChild(newproperty.childNodes[newproperty.childNodes.length - 1]);
				newproperty.id = "property-form-" + ++propertyIndex;
				var removerButton = remover.cloneNode(true);
				removerButton.setAttribute('onclick',
						'$("#' + newproperty.id
								+ '").remove()');
				newproperty.insertAdjacentHTML('beforeend',
						removerButton.outerHTML);
				propertyForm.insertAdjacentHTML('afterend',
						newproperty.outerHTML);
			}
		})();
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>

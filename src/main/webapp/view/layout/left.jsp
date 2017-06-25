<%@ page contentType="text/html;charset=UTF-8"%>
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">


		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- #sidebar-shortcuts -->

	<ul class="nav nav-list">


		<li <c:if test="${father == 100}">class="active"</c:if>><a
			href="index.html"> <i class="icon-dashboard"></i> <span
				class="menu-text"> 首页 </span>
		</a></li>

		<li <c:if test="${father == 200}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text"> 用户管理 </span> <b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu"
				<c:if test="${children == 2001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 2001}">class="active"</c:if>><a
					href="${ctx}/admin/person/list?father=200&children=2001"> <i
						class="icon-double-angle-right"></i> 用户列表
				</a></li>
			</ul></li>

		<li <c:if test="${father == 300}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text"> 组织架构管理 </span> <b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu"
				<c:if test="${children == 3001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 3001}">class="active"</c:if>><a
					href="${ctx}/admin/company/getCompanyTree?father=300&children=3001">
						<i class="icon-double-angle-right"></i> 组织架构列表
				</a></li>
			</ul></li>
		<li <c:if test="${father == 700}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text"> 项目管理 </span> <b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu"
				<c:if test="${children ==7001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 7001}">class="active"</c:if>><a
					href="${ctx}/admin/project/list?father=700&children=7001">
						<i class="icon-double-angle-right"></i> 项目列表
				</a></li>
			</ul></li>
		<li <c:if test="${father == 400}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text"> 动态管理 </span> <b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu"
				<c:if test="${children ==4001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 4001}">class="active"</c:if>><a
					href="${ctx}/admin/companyDynamics/list?father=400&children=4001">
						<i class="icon-double-angle-right"></i> 动态列表
				</a></li>
			</ul></li>
		<li <c:if test="${father == 500}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">项目风采管理 </span> <b class="arrow icon-angle-down"></b>
		</a>

			<ul class="submenu"
				<c:if test="${children ==5001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 5001}">class="active"</c:if>><a
					href="${ctx}/admin/projectMien/list?father=500&children=5001">
						<i class="icon-double-angle-right"></i> 项目风采列表
				</a></li>
			</ul></li>

		<li <c:if test="${father == 600}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">字典管理 </span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu"
				<c:if test="${children ==6001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 6001}">class="active"</c:if>><a
					href="${ctx}/admin/dict/list?father=600&children=6001"> <i
						class="icon-double-angle-right"></i> 字典列表
				</a></li>
			</ul></li>
			
				<li <c:if test="${father == 800}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">专业管理 </span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu"
				<c:if test="${children ==8001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 8001}">class="active"</c:if>><a
					href="${ctx}/admin/major/list?father=800&children=8001"> <i
						class="icon-double-angle-right"></i> 专业列表
				</a></li>
			</ul></li>
			
		<li <c:if test="${father == 900}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">后缀管理 </span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu"
				<c:if test="${children ==9001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 9001}">class="active"</c:if>><a
					href="${ctx}/admin/suffix/list?father=900&children=9001"> <i
						class="icon-double-angle-right"></i> 后缀列表
				</a></li>
			</ul></li>
			
			<li <c:if test="${father == 900}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">标段管理 </span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu"
				<c:if test="${children ==10001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 10001}">class="active"</c:if>><a
					href="${ctx}/admin/section/list?father=1000&children=10001"> <i
						class="icon-double-angle-right"></i> 标段列表
				</a></li>
			</ul></li>
				<li <c:if test="${father == 1100}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">农名工管理</span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu"
				<c:if test="${children ==11001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 11001}">class="active"</c:if>><a
					href="${ctx}/admin/migrantWorker/find?father=1100&children=11001"> <i
						class="icon-double-angle-right"></i> 农民工列表
				</a></li>
			</ul></li>
			
			<li <c:if test="${father == 1200}">class="open"</c:if>><a href="#"
			class="dropdown-toggle"> <i class="icon-list"></i> <span
				class="menu-text">文件筛选方案管理</span> <b class="arrow icon-angle-down"></b>
		</a>
			<ul class="submenu"
				<c:if test="${children ==12001}">style="display:block;"</c:if>>
				<li <c:if test="${children == 12001}">class="active"</c:if>><a
					href="${ctx}/admin/scheme/list?father=1200&children=12001"> <i
						class="icon-double-angle-right"></i> 方案列表
				</a></li>
			</ul></li>
	</ul>
	<!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
			data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>
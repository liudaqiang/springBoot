<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>  
<%@ include file="../layout/taglib.jsp"%>
		
			 <div class="tree-folder" style="display: block;">
				<div class="tree-folder-header">
				  <i class="icon-minus"></i>
				  <div class="tree-folder-name" onclick="javascript:window.location.href='${ctx}/admin/company/edit?id=${company.id}'">${company.name}</div>
				 </div>
				 <c:forEach items="${company.companyList}" var="childCompany">
				 <c:if test="${fn:length(childCompany.companyList) > 0}">
					 <div class="tree-folder-content">
						<div class="tree-folder" style="display: block;">
						  <c:set var="company" value="${childCompany}" scope="request"/>
						  <jsp:include page="companyMenu.jsp"/>
					 	</div>
					 </div>
				 </c:if>
				 <c:if test="${fn:length(childCompany.companyList) == 0}">
					<div class="tree-folder-content">
					  <div class="tree-item" style="display: block;">
						<div class="tree-item-name" onclick="javascript:window.location.href='${ctx}/admin/company/edit?id=${childCompany.id}'">${childCompany.name}</div>
						<span class="closeX" style="float:right;" onclick="javascript:window.location.href='${ctx}/admin/company/delete?id=${childCompany.id}'"></span>
					</div>
					</div>
				 </c:if>
				 	</c:forEach>
			 </div>
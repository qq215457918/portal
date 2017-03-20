<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>编辑员工信息</title>
<base href="${basePath}">
<script type="text/javascript" src="resources/js/admin/compile_employee_info.js"></script>
<jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
<style>
    .icon-star:before {
        content: "\f005";
        color: red;
    }
</style>
</head>
<body>
<div class="modal-shiftfix">
    <div class="container-fluid main-content">
        <div class="page-title">
            <div class="row">
              <div class="col-md-12">
                <div class="widget-container">
                  <div class="widget-content padded">
                     <div class="row">
	                    <form action="admin/employeeManage/saveEmployeeInfo" id="form" method="post">
		                   	<input type="hidden" id="id" name="id" value="${employee.id}" />
		                   	<input type="hidden" id="photoPath" name="photoPath" value="${employee.photoPath}" />
		                   	<input type="hidden" id="receptionFlag" name="receptionFlag" value="${employee.receptionFlag}" />
		                   	<c:if test="${not empty employee}">
			                   	<input type="hidden" id="createDate" name="createDate" value="${employee.createDate}" />
		                   	</c:if>
		                   	<input type="hidden" id="deleteFlag" name="deleteFlag" value="${employee.deleteFlag}" />
                             <div class="col-md-6">
                                 <div class="form-group">
                                    <label>姓名  &nbsp;</label><i class="icon-star"></i>
                                    <input class="form-control" id="name" name="name" type="text" value="${employee.name}">
                                 </div>
                                 <div class="form-group">
                                   <label>性别  &nbsp;</label><i class="icon-star"></i>
                                   <select class="form-control" id="sex" name="sex">
                                       <option value="1" <c:if test="${employee.sex eq 1 }">selected="selected"</c:if>>男</option>
                                       <option value="0" <c:if test="${employee.sex eq 0 }">selected="selected"</c:if>>女</option>
                                   </select>
                                 </div>
                                 <div class="form-group">
                                    <label>登录名  &nbsp;</label><i class="icon-star"></i>
                                    <input class="form-control" id="loginName" name="loginName" type="text" value="${employee.loginName}">
                                 </div>
                                 <div class="form-group">
                                    <label>密码  &nbsp;</label><i class="icon-star"></i>
                                    <input class="form-control" id="password" name="password" type="password" value="${employee.password}">
                                 </div>
                                 <div class="form-group">
                                     <label>职位类型</label><i class="icon-star"></i>
                                     <div class="col-md-7" style="width: 100%;">
                                       <label class="radio-inline" style="width: 15%; margin-left: 10%;"><input name="positionType" type="radio" checked="checked" value="1"><span>客服</span></label>
                                       <label class="radio-inline" style="width: 15%;"><input name="positionType" type="radio" <c:if test="${employee.positionType eq 2 }">checked="checked"</c:if> value="2"><span>接待</span></label>
                                     </div>
                                 </div>
                                 <div class="form-group">
                                     <label>账号状态</label><i class="icon-star"></i>
                                     <div class="col-md-7" style="width: 100%;">
                                       <label class="radio-inline" style="width: 15%; margin-left: 10%;"><input name="status" checked="checked" type="radio" value="0"><span>未禁用</span></label>
                                       <label class="radio-inline" style="width: 15%;"><input name="status" type="radio" <c:if test="${employee.status eq 1 }">checked="checked"</c:if> value="1"><span>已禁用</span></label>
                                     </div>
                                 </div>
                             </div>
                             
                             <div class="col-md-6">
                                 <div class="form-group">
                                   <label >工号</label>
                                   <input class="form-control" id="staffNumber" name="staffNumber" type="text" value="${employee.staffNumber}" >
                                 </div>
                                 <div class="form-group">
                                     <label>所属机构  &nbsp;</label><i class="icon-star"></i>
                                     <select class="form-control" id="organizationId" name="organizationId">
                                         <option value="">请选择</option>
                                         <c:forEach items="${companyList}" var="company">
                                             <option value="${company.id}" <c:if test="${employee.organizationId eq company.id }">selected="selected"</c:if>>${company.name}</option>
                                         </c:forEach>
                                     </select>
                                 </div>
                                 <div class="form-group">
                                     <label>所属部门  &nbsp;</label>
                                     <select class="form-control" id="departmentId" name="departmentId">
                                         <option value="">请选择</option>
                                         <c:forEach items="${departmentList}" var="dept">
                                             <option value="${dept.id}" <c:if test="${employee.departmentId eq dept.id }">selected="selected"</c:if>>${dept.name}</option>
                                         </c:forEach>
                                     </select>
                                 </div>
                                 <div class="form-group">
                                     <label>所属小组  &nbsp;</label>
                                     <select class="form-control" id="groupId" name="groupId">
                                         <option value="">请选择</option>
                                         <c:forEach items="${groupList}" var="group">
                                             <option value="${group.id}" <c:if test="${employee.groupId eq group.id }">selected="selected"</c:if>>${group.name}</option>
                                         </c:forEach>
                                     </select>
                                 </div>
                                 <div class="form-group">
                                     <label>关联角色</label>
                                     <a class="add" href="javascript:;" style="margin-left: 10px;"><span>添&nbsp;&nbsp;加</span></a>
                                     <div class="roles">
	                                     <c:if test="${empty employee.roleIds}">
	                                     	 <div>
		                                     	 <select class="form-control" name="roleIds">
			                                         <option value="">请选择</option>
			                                         <c:forEach items="${roleList}" var="role">
			                                             <option value="${role.id}">${role.description}</option>
			                                         </c:forEach>
			                                     </select>
		                                     </div>
	                                     </c:if>
	                                     <c:if test="${not empty idsList}">
	                                     	<c:forEach items="${idsList }" var="ids" varStatus="index">
	                                     		 <div>
		                                     		 <select class="form-control" name="roleIds" style="width: 90%; float: left;">
				                                         <option value="">请选择</option>
				                                         <c:forEach items="${roleList}" var="role">
				                                             <option value="${role.id}" <c:if test="${ids eq role.id }">selected="selected"</c:if>>${role.description}</option>
				                                         </c:forEach>
				                                     </select>
				                                     <c:if test="${index.index > 0 }">
				                                     	<a onclick="deleteRoles(this);" href="javascript:;" style="float: left; margin-top: 6px; margin-left: 10px;"><span>删&nbsp;&nbsp;除</span></a>
				                                     </c:if>
			                                     </div>
	                                     	</c:forEach>
	                                     </c:if>
                                     </div>
                                 </div>
                              </div>
	                         <button class="btn btn-default-outline" id="back" style="float:right; margin-top: 20px;">返回</button>
	                         <button class="btn btn-primary" id="saveEmployee" style="float:right; margin-top: 20px;">保存</button>
	                      </form>
	                      <!-- 角色数据模版 -->
	                      <select class="model" style="display: none;">
	                      	<option value="">请选择</option>
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.description}</option>
                            </c:forEach>
	                      </select>
                       </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
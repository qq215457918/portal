<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>员工管理</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/admin/employee_manage_list.js"></script>
<jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
    <div class="row">
      <div class="col-lg-12">
        <div class="widget-container fluid-height clearfix">
          <div class="heading" style="color:#666666; font-weight: bold;">
            <i class="icon-reorder fl" style="margin-top: 0.8%; cursor: default;"></i>
            <select id="organizationId" class="form-control fl" style="width: 9%;">
                <option value="1" <c:if test="${organizationId == '1'}">selected="selected"</c:if> >大连</option>
                <option value="0" <c:if test="${organizationId == '0'}">selected="selected"</c:if> >沈阳</option>
            </select>
          </div>
          <div class="widget-content padded">
            <div class="form-group condition-group">
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">姓&nbsp;&nbsp;名</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="员工姓名" id="name" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">部&nbsp;&nbsp;门</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="departmentId" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>	
                            <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.id}">${dept.name}</option>	
                            </c:forEach>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">小&nbsp;&nbsp;组</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="groupId" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">性&nbsp;&nbsp;别</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="sex" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">创建日期</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-3" style="width: 100%;">
                    <div class="input-group date datepicker">
                      <input class="form-control" type="text" id="startCreateDate" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
                    </div>
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                    <label class="control-label col-md-2" style="width: 100%;">至</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-3" style="width: 100%;">
                    <div class="input-group date datepicker">
                      <input class="form-control" type="text" id="endCreateDate" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
                    </div>
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
                <button class="btn btn-primary" id="export" style="float: left;">导&nbsp;出</button>
                <button class="btn btn-primary" id="add" style="float: left;">新&nbsp;增</button>
              </div>
            </div>
            <div style="clear: both;"></div>
            
            <table class="table table-bordered" id="employee">
                <thead>
                    <th>序&nbsp;&nbsp;号</th>
                    <th>员工姓名</th>
                    <th>性&nbsp;&nbsp;别</th>
                    <th>登录名</th>
                    <th>部门名称</th>
                    <th>小组名称</th>
                    <th>职位类型</th>
                    <th>接待状态</th>
                    <th>创建日期</th>
                    <th>操&nbsp;&nbsp;作</th>
                </thead>
                <tbody>
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
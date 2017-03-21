<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>客户信息管理</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/admin/customer_manage_list.js"></script>
<jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
    <div class="row">
      <div class="col-lg-12">
        <div class="widget-container fluid-height clearfix">
          <div class="heading" style="color:#666666; font-weight: bold;">
            <i class="icon-reorder fl" style="margin-top: 0.8%; cursor: default;"></i>
            <select id="area" class="form-control fl" style="width: 9%;">
                <option value="1" <c:if test="${area == '1'}">selected="selected"</c:if> >大连</option>
                <option value="0" <c:if test="${area == '0'}">selected="selected"</c:if> >沈阳</option>
            </select>
          </div>
          <div class="widget-content padded">
            <div class="form-group condition-group">
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">姓名</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="客户姓名" id="name" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">客户会员卡号</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="客户会员卡号" id="vipCard" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">性别</label>
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
                      <label class="control-label col-md-2" style=" width: 100%;">客户分类</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="type" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>
                            <option value="0">空白客户</option>
                            <option value="1">重复登门</option>
                            <option value="2">说明会</option>
                            <option value="3">成单</option>
                            <option value="4">锁定</option>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
                <button class="btn btn-primary" id="export" style="float: left;">导&nbsp;出</button>
              </div>
            </div>
            <div style="clear: both;"></div>
            
            <table class="table table-striped" id="customer">
                <thead><tr>
                    <th>序号</th>
                    <th>客户姓名</th>
                    <th>地区</th>
                    <th>性别</th>
                    <th>客户分类</th>
                    <th>会员卡号</th>
                    <th>操作</th></tr>
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
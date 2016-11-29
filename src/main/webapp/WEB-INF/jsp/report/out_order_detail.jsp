<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>登门出单详情</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/report/out_order_detail.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="col-lg-12">
	    <div class="widget-container fluid-height clearfix">
	      <div class="widget-content padded">
	        <div class="form-group condition-group">
	          <input type="hidden" id="customerId" value="${customerId }" />
	          <input type="hidden" id="startDate" value="${startDate }" />
	          <input type="hidden" id="endDate" value="${endDate }" />
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">产品名称</label>
	          	</div>
	          	<div class="condition-control fl">
	          	  <div class="col-md-7" style="width: 100%;">
		            <input class="form-control" placeholder="产品名称" id="goodName" type="text" style="width: 100%;">
		          </div>
	          	</div>
	          </div>
	          <div class="condition fl">
		        <button class="btn btn-success fl" style="margin-left: 1%;">查&nbsp;询</button>
		        <button class="btn btn-default fl" id="back">返&nbsp;回</button>
		      </div>
			</div>
			<div style="clear: both;"></div>
			
			<table class="table table-bordered" id="outOrderDetail">
				<thead>
					<th>生成出单日期</th>
                   	<th>藏品名称</th>
                   	<th>藏品种类</th>
                   	<th>单&nbsp;&nbsp;价</th>
                   	<th>数&nbsp;&nbsp;量</th>
                   	<th>合&nbsp;&nbsp;计</th>
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
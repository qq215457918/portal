<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>登门出单统计</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/report/visit_out_order.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="col-lg-12">
	    <div class="widget-container fluid-height clearfix">
	      <div class="heading" style="color:#666666; font-weight: bold;">
	        <i class="icon-reorder fl" style="margin-top: 0.8%; cursor: default;"></i>
	    	<select id="area" class="form-control fl" style="width: 9%;">
	    		<option value="1" selected="selected">大连</option>
	    		<option value="0">沈阳</option>
	    	</select>
	      </div>
	      <div class="widget-content padded">
	        <div class="form-group condition-group">
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">客户</label>
	          	</div>
	          	<div class="condition-control fl">
	          	  <div class="col-md-7" style="width: 100%;">
		            <input class="form-control" placeholder="客户姓名" id="customerName" type="text" style="width: 100%;">
		          </div>
	          	</div>
	          </div>
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">客户类型</label>
	          	</div>
	          	<div class="condition-control fl">
	          	  <div class="col-md-7" style="width: 100%;">
		            <select id="type" class="form-control" style="width: 100%; height: 34px;">
		            	<option value="">请选择</option>
		            	<option value="0">新客户</option>
		            	<option value="1">重复登门</option>
		            	<option value="2">说明会</option>
		            	<option value="3">成单</option>
		            	<option value="4">锁定</option>
		            	<option value="5">转介绍</option>
		            </select>
		          </div>
	          	</div>
	          </div>
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">统计日期</label>
	          	</div>
	          	<div class="condition-control fl">
	          	  <div class="col-md-3" style="width: 100%;">
				    <div class="input-group date datepicker">
		              <input class="form-control" type="text" id="startDate" value="${startDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
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
		              <input class="form-control" type="text" id="endDate" value="${endDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
		            </div>
			      </div>
	          	</div>
	          </div>
	          <div class="condition fl">
		        <button class="btn btn-success fl" style="margin-left: 1%;">查&nbsp;询</button>
		        <!-- <button class="btn btn-primary" id="export" style="float: left;">导&nbsp;出</button> -->
		        <!-- <button class="btn btn-default" id="back" style="float: left;">返&nbsp;回</button> -->
		      </div>
			</div>
			<div style="clear: both;"></div>
			
			<table class="table table-bordered" id="visitOutOrder">
				<thead>
					<th>客户ID</th>
                   	<th>日期</th>
                   	<th>开始<br>接待时间</th>
                   	<th>结束<br>接待时间</th>
                   	<th>共接待时长<br>分钟</th>
                   	<th>姓名</th>
                   	<th>客户类型</th>
                   	<th>联系方式</th>
                   	<th>地址</th>
                   	<th>生日</th>
                   	<th>会员卡号</th>
                   	<th>关联客户</th>
                   	<th>客服</th>
                   	<th>接待</th>
                   	<th>出单数量</th>
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
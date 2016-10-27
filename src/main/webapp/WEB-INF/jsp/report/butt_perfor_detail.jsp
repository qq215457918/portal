<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>展厅客服对接业绩</title>
<base href="${basePath}">
<script type="text/javascript" src="resources/js/report/butt_perfor_detail.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="col-lg-12">
	    <div class="widget-container fluid-height clearfix">
	      <div class="heading" style="color:#666666; font-weight: bold;">
	        <i class="icon-reorder" style="float: left; margin-top: 0.8%; cursor: default;"></i>
	    	<select id="viewPhoneStaffGroupName" class="form-control" style="width: 9%; float: left;">
	    		<option value="大连" selected="selected">大连</option>
	    		<option value="沈阳">沈阳</option>
	    	</select>
	      </div>
	      <div class="widget-content padded">
	        <div class="form-group" style="margin-left: 9.2%; height: 35px;">
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">客服</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          	  <div class="col-md-7" style="width: 100%;">
		            <input class="form-control" placeholder="客服姓名" id="viewPhoneStaffName" type="text" style="width: 100%;">
		          </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">统计日期</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          	  <div class="col-md-3" style="width: 100%;">
				    <div class="input-group date datepicker">
		              <input class="form-control" type="text" id="startReportDate" value="${startReportDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
		            </div>
		          </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          	  <label class="control-label col-md-2" style="width: 100%;">至</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          	  <div class="col-md-3" style="width: 100%;">
		            <div class="input-group date datepicker">
		              <input class="form-control" type="text" id="endReportDate" value="${endReportDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
		            </div>
			      </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">接待</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          	  <div class="col-md-7" style="width: 100%;">
		            <input class="form-control" placeholder="接待姓名" id="viewReceiveStaffName" type="text" style="width: 100%;">
		          </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
		        <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
		        <button class="btn btn-primary" id="export" style="float: left;">导&nbsp;出</button>
		      </div>
			</div>
			<div style="clear: both;"></div>
			
			<table class="table table-bordered" id="buttPerforDetail">
				<thead>
                   	<th>客&nbsp;&nbsp;服</th>
                   	<th>接&nbsp;&nbsp;待</th>
                   	<th>所属机构</th>
                   	<th>成单接待数</th>
                   	<th>成单出单数</th>
                   	<th>成单出单率</th>
                   	<th>成单业绩</th>
                   	<th>成单单均</th>
                   	<th>成单件均</th>
                   	<th>锁定接待数</th>
                   	<th>锁定出单数</th>
                   	<th>锁定出单率</th>
                   	<th>锁定业绩</th>
                   	<th>锁定单均</th>
                   	<th>锁定件均</th>
                   	<th>单均产品件数</th>
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
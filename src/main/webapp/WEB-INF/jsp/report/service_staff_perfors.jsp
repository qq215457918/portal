<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>客服业绩统计</title>
<base href="${basePath}">
<script type="text/javascript" src="resources/js/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="resources/js/report/service_staff_perfors.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="col-lg-12">
	    <div class="widget-container fluid-height clearfix">
	      <div class="widget-content padded">
	        <div class="form-group" style="margin-left: 9.2%; height: 35px;">
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">客服</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="客服姓名" id="staffName" type="text" style="width: 100%;">
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
		              <input class="form-control" type="text" id="startDate" value="${startDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
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
		              <input class="form-control" type="text" id="endDate" value="${endDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
		            </div>
			      </div>
	          	</div>
	          </div>
		      <div style="height: 50px; float: left;width: 30%;">
		      	<button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
		      </div>
			</div>
			<div style="clear: both;"></div>
			
			<div style="width: 50%; float: left; margin-top: 1.3%; text-align: -webkit-center;">
			  <!-- 大连客服业绩 -->
			  <div id="dlContainer" style="width: 400px; height: 400px;"></div>
			</div>
			  
			<div style="width: 50%; float: left; margin-top: 1.3%; text-align: -webkit-center;">
			  <!-- 沈阳客服业绩 -->
			  <div id="syContainer" style="width: 400px; height: 400px;"></div>
			</div>
			
	      </div>
	    </div>
	  </div>
    </div>
  </div>
</body>
</html>
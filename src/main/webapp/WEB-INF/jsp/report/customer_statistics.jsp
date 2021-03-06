<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>客户统计</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="resources/js/report/customer_report.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="form-group" style="margin-left: 9.2%;">
	    <div class="condition fl">
		  <div class="condition-label fl">
			<label class="control-label col-md-2" style="width: 100%;">统计日期</label>
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
      	  <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
      	</div>
	  </div>
	  <div style="clear: both;"></div>
	  
	  <div class="container fl">
	  	<div id="dlPicture"><i class="icon-reorder dl"></i></div>
	  	<!-- 大连客户数量 -->
	  	<div id="dlContainer" style="width: 400px; height: 400px;"></div>
	  </div>
	  
	  <div class="container fl">
	    <div id="syPicture"><i class="icon-reorder sy"></i></div>
	  	<!-- 沈阳客户数量 -->
	 	<div id="syContainer" style="width: 400px; height: 400px;"></div>
	  </div>
	</div>
  </div>
</body>
</html>
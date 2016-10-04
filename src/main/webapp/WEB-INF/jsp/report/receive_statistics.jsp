<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>登门统计</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="resources/js/report/receive_report.js"></script>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="form-group" style="margin-left: 9.2%;">
		<div class="col-md-3" style="width: 20.7%; float: left;">
		  <div class="input-group date datepicker">
            <input class="form-control" type="text" id="startReportDate" value="${startReportDate }"><span class="input-group-addon"><i class="icon-calendar"></i></span>
          </div>
        </div>
        <label class="control-label col-md-2" style="width: 3%; margin-top: 0.5%; float: left;">至</label>
        <div class="col-md-3" style="width: 20.7%; float: left;">
          <div class="input-group date datepicker">
            <input class="form-control" type="text" id="endReportDate" value="${endReportDate }"><span class="input-group-addon"><i class="icon-calendar"></i></span>
          </div>
        </div>
        <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
	  </div>
	  <div style="clear: both;"></div>
	  
	  <div style="width: 50%; float: left; margin-top: 1.3%; text-align: -webkit-center;">
	  	<div id="dlPicture"><i class="icon-reorder dl"></i></div>
	  	<!-- 大连客户数量 -->
	  	<div id="dlContainer" style="width: 400px; height: 400px;"></div>
	  </div>
	  
	  <div style="width: 50%; float: left; margin-top: 1.3%; text-align: -webkit-center;">
	    <div id="syPicture"><i class="icon-reorder sy"></i></div>
	  	<!-- 沈阳客户数量 -->
	 	<div id="syContainer" style="width: 400px; height: 400px;"></div>
	  </div>
	</div>
  </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>每日成交业绩</title>
<base href="${basePath}">
<script type="text/javascript" src="resources/js/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="resources/js/report/clinch_performance.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="form-group" style="margin-left: 9.2%;">
	  	<div style="height: 50px; float: left; width: 30%;">
          <div style="width: 35%; float: left;">
         	<label class="control-label col-md-2" style="width: 100%; margin-left: 0.5%;">开始日期</label>
          </div>
          <div style="width: 65%; float: left;">
         	<div class="col-md-3" style="width: 100%; float: left;">
			  <div class="input-group date datepicker">
	            <input class="form-control" type="text" id="startDate" value="${startDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
	          </div>
	        </div>
          </div>
        </div>
        <div style="height: 50px; float: left;width: 30%;">
          <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
          <label class="control-label col-md-2" style="color: red; font-size: 12px; width: 65%;">查询选中日期所属周的数据</label>
        </div>
	  </div>
	  <div style="clear: both;"></div>
	  
	  <div style="width: 50%; float: left; margin-top: 1.3%; text-align: -webkit-center;">
	  	<!-- 大连客户数量 -->
	  	<div id="dlContainer" style="width: 400px; height: 400px;"></div>
	  </div>
	  
	  <div style="width: 50%; float: left; margin-top: 1.3%; text-align: -webkit-center;">
	  	<!-- 沈阳客户数量 -->
	 	<div id="syContainer" style="width: 400px; height: 400px;"></div>
	  </div>
	</div>
  </div>
</body>
</html>
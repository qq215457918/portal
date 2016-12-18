<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>销售日报表</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/report/sell_daily.js"></script>
<script type="text/javascript" src="resources/js/jQuery.print.js"></script>
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
		        <button class="btn btn-success fl" style="margin-left: 1%;">查&nbsp;询</button>
		        <button class="btn btn-primary fl" id="print">打&nbsp;印</button>
		      </div>
			</div>
			<div style="clear: both;"></div>
			<form id="form" action="report/saveSellDaily" method="post">
				<input type="hidden" name="area" />
				<table class="table table-bordered" id="sellDaily">
				
				</table>
				<div style="float: right;">
			        <button class="btn btn-primary" id="saveSell" style="display: none;">保&nbsp;存</button>
			    </div>
			</form>
	      </div>
	    </div>
	  </div>
    </div>
  </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>机构业绩</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/report/organi_performance.js"></script>
<jsp:include page="/WEB-INF/jsp/report/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="col-lg-12">
	    <div class="widget-container fluid-height clearfix">
	      <div class="widget-content padded">
	        <div class="form-group condition-group">
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">统计日期</label>
	          	</div>
	          	<div class="condition-control fl">
	          	  <div class="col-md-3" style="width: 100%;">
				    <div class="input-group date datepicker">
		              <input class="form-control" type="text" id="startReportDate" value="${startReportDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
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
		              <input class="form-control" type="text" id="endReportDate" value="${endReportDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
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
			
			<table class="table table-striped" id="organiPerformance">
				<thead>
					<th>序号</th>
                   	<th>机构</th>
                   	<th>业绩</th>
                   	<th>新客户数量</th>
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
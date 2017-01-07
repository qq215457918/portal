<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>接待客户及业绩统计</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/report/salesman_statement.js"></script>
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
	          		<label class="control-label col-md-2" style=" width: 100%;">接待</label>
	          	</div>
	          	<div class="condition-control fl">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="接待人姓名" id="receiveStaffName" type="text" style="width: 100%;">
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
		      	<button class="btn btn-primary fl" id="export">导&nbsp;出</button>
		      </div>
			</div>
			<div style="clear: both;"></div>
			
			<table class="table table-bordered" id="statement">
				<thead>
					<tr>
						<th rowspan="2">接&nbsp;&nbsp;待</th>
	                   	<th colspan="3">锁定个数</th>
	                   	<th colspan="3">成单个数</th>
	                   	<th colspan="3">重复个数</th>
	                   	<th colspan="3">说明会个数</th>
	                   	<th colspan="3">新客户个数</th>
	                   	<th colspan="3">合计个数</th>
					</tr>
					<tr>
	                   	<th>个数</th>
	                   	<th>单数</th>
	                   	<th>业绩</th>
	                   	<th>个数</th>
	                   	<th>单数</th>
	                   	<th>业绩</th>
	                   	<th>个数</th>
	                   	<th>单数</th>
	                   	<th>业绩</th>
	                   	<th>个数</th>
	                   	<th>单数</th>
	                   	<th>业绩</th>
	                   	<th>个数</th>
	                   	<th>单数</th>
	                   	<th>业绩</th>
	                   	<th>个数</th>
	                   	<th>单数</th>
	                   	<th>业绩</th>
					</tr>
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
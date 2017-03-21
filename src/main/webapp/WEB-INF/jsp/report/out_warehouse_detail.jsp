<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>出库明细统计</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/report/out_warehouse_detail.js"></script>
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
	    	<div class="fl" style="width: 15%;">
		    	<label class="control-label col-md-2" style="width: 100%;">藏品数量：<span id="commodity" style="color: blue; font-weight: bold;">0</span></label>
	    	</div>
	    	<div class="fl" style="width: 15%;">
		    	<label class="control-label col-md-2" style="width: 100%;">赠品数量：<span id="gift" style="color: blue; font-weight: bold;">0</span></label>
	    	</div>
	      </div>
	      <div class="widget-content padded">
	        <div class="form-group condition-group">
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">出单号</label>
	          	</div>
	          	<div class="condition-control fl">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="出单号" id="orderNumber" type="text" style="width: 100%;">
		            </div>
	          	</div>
	          </div>
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">藏品名称</label>
	          	</div>
	          	<div class="condition-control fl">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="藏品名称" id="goodsName" type="text" style="width: 100%;">
		            </div>
	          	</div>
	          </div>
	          <div class="condition fl">
	          	<div class="condition-label fl">
	          		<label class="control-label col-md-2" style=" width: 100%;">开始日期</label>
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
			
			<table class="table table-striped" id="OutWarehouse">
				<thead>
					<th>出单号</th>
                   	<th>藏品分类</th>
					<th>藏品名称</th>
                   	<th>藏品类型</th>
                   	<th>藏品价格</th>
                   	<th>数量</th>
                   	<th>出库日期</th>
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
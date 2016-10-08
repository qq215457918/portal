<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>接待统计</title>
<base href="${basePath}">
<script type="text/javascript" src="resources/js/report/visit_everyday_list.js"></script>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
	<div class="row">
	  <div class="col-lg-12">
	    <div class="widget-container fluid-height clearfix">
	      <div class="heading" style="color:#666666; font-weight: bold;">
	        <i class="icon-reorder" style="float: left; margin-top: 0.8%; cursor: default;"></i>
	    	<select id="customerArea" class="form-control" style="width: 9%; float: left;">
	    		<option value="1" <c:if test="${area == '1'}">selected="selected"</c:if> >大连</option>
	    		<option value="0" <c:if test="${area == '0'}">selected="selected"</c:if> >沈阳</option>
	    	</select>
	      </div>
	      <div class="widget-content padded">
	        <div class="form-group" style="margin-left: 9.2%; height: 35px;">
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">登门日期</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-3" style="width: 100%;">
				      <div class="input-group date datepicker">
		                <input class="form-control" type="text" id="visitDate" value="${visitDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
		              </div>
		            </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">客户</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="客户姓名" id="customerName" type="text" style="width: 100%;">
		            </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">客服</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="客服姓名" id="customServiceName" type="text" style="width: 100%;">
		            </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">接待</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-7" style="width: 100%;">
		              <input class="form-control" placeholder="接待人姓名" id="receiverStaffName" type="text" style="width: 100%;">
		            </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">活动</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-7" style="width: 100%;">
			            <select id="exercise" class="form-control" style="width: 100%;">
				    		<option value="">请选择</option>
				    		<option value="0">无</option>
				    		<option value="1">HZ</option>
				    		<option value="2">4DS</option>
				    		<option value="3">QB</option>
				    		<option value="4">回款</option>
				    		<option value="5">DS</option>
				    		<option value="6">LY</option>
				    		<option value="7">LY+HZ</option>
				    		<option value="8">XY</option>
				    		<option value="9">HZ+38国家</option>
				    		<option value="10">3B</option>
				    	</select>
				    </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 30%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">客户类型</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          		<div class="col-md-7" style="width: 100%;">
			            <select id="customerType" class="form-control" style="width: 100%;">
				    		<option value="">请选择</option>
				    		<option value="0">新客户</option>
				    		<option value="1">重复</option>
				    		<option value="2">说明会</option>
				    		<option value="3">成单</option>
				    		<option value="4">锁定</option>
				    		<option value="5">转介绍</option>
				    	</select>
				    </div>
	          	</div>
	          </div>
		      <div style="height: 50px; float: left;width: 30%;">
		      	<button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
		      	<button class="btn btn-primary" id="export" style="float: left;">导&nbsp;出</button>
		      	<button class="btn btn-default" id="back" style="float: left;">返&nbsp;回</button>
		      </div>
			</div>
			<div style="clear: both;"></div>
			
			<table class="table table-bordered" id="visitCustomer">
				<thead>
					<th>日&nbsp;期</th>
					<th>客&nbsp;户</th>
					<th>客&nbsp;服</th>
                   	<th>接&nbsp;待</th>
                   	<th>活&nbsp;动</th>
                   	<th>客户类型</th>
                   	<th>成单金额</th>
                   	<th>藏品名称</th>
                   	<th>出单/订单</th>
                   	<th>情况</th>
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
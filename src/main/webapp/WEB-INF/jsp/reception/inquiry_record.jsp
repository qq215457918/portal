<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>接待业务</title>
    <base href="${basePath}">
    <script type="text/javascript" src="resources/js/reception/inquiry_record.js"></script>
	<jsp:include page="head.jsp" />
  </head>
  <body>
    <div class="modal-shiftfix">
      <div class="row">
        <div class="col-md-12">
          <div class="widget-container fluid-height clearfix">
	      <div class="heading" style="color:#666666; font-weight: bold;">
	        <i class="icon-reorder" style="float: left; margin-top: 0.8%; cursor: default;">&nbsp;&nbsp;接待业务查询表</i>
	      </div>
	      <div class="widget-content padded">
          <div class="form-group" style="margin-left: 9.2%; height: 35px;">
         	  <div style="height: 50px; float: left; width: 40%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">查询日期</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          	  <div class="col-md-3" style="width: 100%;">
				    <div class="input-group date datepicker">
		              <input class="form-control" type="text" id="startReportDate" value="${startReportDate }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
		            </div>
		          </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 40%;">
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
	          <div style="height: 50px; float: left; width: 40%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" style=" width: 100%;">员工姓名</label>
	          	</div>
	          	<div style="width: 65%; float: left;">
	          	  <div class="col-md-7" style="width: 100%;">
		            <input class="form-control" placeholder="客服姓名、接待员姓名" id="receiverStaffName" type="text" style="width: 100%;">
		          </div>
	          	</div>
	          </div>
	          <div style="height: 50px; float: left; width: 40%;">
	          	<div style="width: 35%; float: left;">
	          		<label class="control-label col-md-2" id="isReceiver" style=" width: 100%;">接待状态</label>
	          	</div>
	       	  <div style="width: 65%; float: left;">
	          	  <div class="col-md-7" style="width: 100%;">
		           <label class="checkbox"><input type="checkbox"><span>是否正在接待</span></label>
		          </div>
	          	</div>
	          </div>

	          <div style="height: 50px; float: left; width: 20%; margin-left:20px;">
		        <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
		        <button class="btn btn-default" id="back" style="float: left;">返&nbsp;回</button>
		      </div>
			</div>
			<div style="clear: both;"></div>

			<table class="table table-bordered table-bordered" id="inquiryTable">
				<thead>
					<th>序&nbsp;&nbsp;号</th>
                   	<th>客户名称</th>
                   	<th>接待员姓名</th>
                   	<th>客服姓名</th>
                   	<th>开始接待时间</th>
                   	<th>结束接待时间</th>
                   	<th>接待日期</th>
				</thead>
				<tbody>
				</tbody>
			</table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
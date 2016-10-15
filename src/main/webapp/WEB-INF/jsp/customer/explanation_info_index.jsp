<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息查询</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/customer/explanation_info_index.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<div class="form-group">
							<label class="control-label col-md-2 swidth">电话号码</label>
							<div class="col-md-2">
								<input class="form-control" placeholder="电话号码" type="text"
									id="phone">
							</div>

							<label class="control-label col-md-2 swidth">号码段</label>
							<div class="col-md-2">
								<input class="form-control" placeholder="号码段" type="text"
									id="phoneStage">
							</div>

							<label class="control-label col-md-2 swidth">更新日期</label>
							<div class="col-md-2">
								<div class="input-group date datepicker">
									<input class="form-control" type="text" id="updateDate">
									<span class="input-group-addon"><i class="icon-calendar"></i></span>
									</input>
								</div>
							</div>

							<label class="control-label col-md-2 swidth">客户类型</label>
							<div class="col-md-2">
								<select class="form-control" id="type">
									<option value="">-请选择-</option>
									<option value="0">空白客户</option>
									<option value="1">重复登门</option>
									<option value="2">说明会</option>
									<option value="3">成单</option>
									<option value="4">锁定</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-7">
								<button class="btn btn-primary" id="searchCustomer">搜索</button>
								<button class="btn btn-primary" id="exportCustomer">导出</button>
							</div>
						</div>
						<!-- DataTables Example -->
						<table class="table table-bordered" id="customerInfo">
							<thead>
								<th>序号</th>
								<th>电话</th>
								<th>电话2</th>
								<th>访问时间</th>
								<th>客户类型</th>
								<th class="hidden-xs">上次访问时间</th>
								<th class="hidden-xs">上次导出时间</th>
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- end DataTables Example -->
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
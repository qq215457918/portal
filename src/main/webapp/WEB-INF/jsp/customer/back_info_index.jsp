<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户信息查询</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/customer/back_info_index.js" type="text/javascript"></script>
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
							<label class="control-label col-md-2 swidth">登门次数</label>
							<div class="col-sm-2">
				            	<input class="form-control" id="backCountS" type="text">
				            </div>
				            <label class="control-label col-md-2 swidth">-</label>
				            <div class="col-sm-2">
				            	<input class="form-control" id="backCountE" type="text">
				            </div>
						
							<label class="control-label col-md-2 swidth">最近登门时间</label>
							<div class="col-sm-2">
				            	<input class="form-control" data-date-autoclose="true" data-date-format="yyyy-mm-dd" id="dpd1" placeholder="开始时间" type="text">
				            </div>
				            <div class="col-sm-2">
				            	<input class="form-control" data-date-autoclose="true" data-date-format="yyyy-mm-dd" id="dpd2" placeholder="结束时间" type="text">
				            </div>
				            
				            <label class="control-label col-md-2 swidth">导出数量</label>
							<div class="col-md-2">
								<input class="form-control" placeholder="导出数量" type="text"
									id="exportCount">
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
								<th>姓名</th>
								<th>地址</th>
								<th>登门次数</th>
								<th>最近登门时间时间</th>
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
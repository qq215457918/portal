<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成单客户信息查询</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/customer/complete_info_index.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<%-- 	<jsp:include page="/WEB-INF/jsp/common/head.jsp" /> --%>
	<jsp:include page="head.jsp" />
	<input type="hidden" id="importResult" value="${importResult }" />
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2 swidth">区域</label>
								<div class="col-md-2">
									<select id="area" class="form-control fl">
										<option value="1" selected="selected">大连</option>
										<option value="0">沈阳</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2 swidth">电话号码</label>
								<div class="col-md-2">
									<input class="form-control" placeholder="电话号码" type="text"
										id="phoneStage">
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2 swidth">成单金额</label>
								<div class="col-sm-2">
									<input class="form-control" id="payPriceS" type="text">
								</div>
								<div class="col-sm-2">
									<input class="form-control" id="payPriceE" type="text">
								</div>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="form-group">
								<label class="control-label col-md-2 swidth">最近成单时间</label>
								<div class="col-sm-2">
									<input class="form-control" data-date-autoclose="true"
										data-date-format="yyyy-mm-dd" id="dpd1" placeholder="开始时间"
										type="text">
								</div>
								<div class="col-sm-2">
									<input class="form-control" data-date-autoclose="true"
										data-date-format="yyyy-mm-dd" id="dpd2" placeholder="开始时间"
										type="text">
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-top: 10px;">
							<div class="col-md-7">
								<button class="btn btn-success" id="searchCustomer">搜索</button>
								<button class="btn btn-primary" id="exportCustomer">导出</button>
								<button class="btn btn-primary" data-toggle="modal"
									data-target="#importExcel" self="0" name="importExcel">导入</button>
								<button class="btn btn-primary" data-toggle="modal"
									data-target="#importExcel" self="1" name="importExcel">导入（累计）</button>
							</div>
						</div>
						<!-- DataTables Example -->
						<table class="table table-striped" id="customerInfo">
							<thead>
								<tr>
									<th>序号</th>
									<th>客服姓名</th>
									<th>用户姓名</th>
									<th>性别</th>
									<th>电话</th>
									<th>地址</th>
									<th>出单总额</th>
									<th>最近出单时间</th>
									<th>操作</th>
								</tr>
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
	<div class="modal fade" id="importExcel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">导入用户信息</h4>
				</div>
				<div class="modal-body">
					<form action="customerInfo/importCustomer" method="post"
						id="importExcelForm" enctype="multipart/form-data">
						<h4>选择文件</h4>
						<p>
						<div class="fileupload fileupload-new" data-provides="fileupload">
							<div class="input-group">
								<div class="form-control">
									<i class="icon-file fileupload-exists"></i> <span
										class="fileupload-preview"></span>
								</div>
								<div class="input-group-btn">
									<a class="btn btn-default fileupload-exists"
										data-dismiss="fileupload" href="#">删除文件</a><span
										class="btn btn-default btn-file"><span
										class="fileupload-new">选择文件</span><span
										class="fileupload-exists">重新选择</span><input type="file"
										id="importFile" name="importFile"></span>
								</div>
							</div>
						</div>
						</p>
						<input type="hidden" value="3" name="type" />
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button"
						onclick="javascript: $('#importExcelForm').submit()">导入</button>
					<button class="btn btn-default-outline" data-dismiss="modal"
						type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
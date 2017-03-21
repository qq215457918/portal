<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单修改</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/reception/order_modify.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<%-- 	<jsp:include page="/WEB-INF/jsp/common/head.jsp" /> --%>
	<jsp:include page="/WEB-INF/jsp/customer/head.jsp" />
	<input type="hidden" id="orderId" value="${orderId }" />
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<div class="form-inline">
							<div class="form-group col-xs-2 ">
								<label for="orderNumber">订单编号</label> <input
									class="form-control" id="orderNumber" type="text">
							</div>
							<div class="form-group col-xs-1 ">
								<label for="orderNumber">商品名称</label> <input
									class="form-control" id="goodsName" type="text">
							</div>
							<div class="form-group col-xs-1 ">
								<label for="orderNumber">客户姓名</label> <input
									class="form-control" id="customerName" type="text">
							</div>
							<div class="form-group col-xs-1 ">
								<label for="customerPhone">客户电话</label> <input
									class="form-control" id="customerPhone" type="text">
							</div>
							<div class="form-group col-xs-1 ">
								<label for="receiverName">接待人员</label> <input
									class="form-control" id="receiverName" type="text">
							</div>
							<div class="form-group col-xs-3" id="listInfo">
								<label class="radio-inline"> <input name="orderType"
									type="radio" value="1"><span>已售商品</span>
								</label> <label class="radio-inline"> <input name="orderType"
									type="radio" value="2"><span>退还商品</span>
								</label>
							</div>
							<button class="btn btn-primary" id="searchList">搜索</button>
						</div>
						<!-- DataTables Example -->
						<table class="table table-striped" id="orderModify">
							<thead>
								<tr>
									<th>序号</th>
									<th>编号</th>
									<th>客户</th>
									<th>电话</th>
									<th>接待</th>
									<th>商品</th>
									<th>金额</th>
									<th>类型</th>
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
	<div class="modal fade" id="detailModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">订单详情</h4>
				</div>
				<div class="modal-body">
					<form action="order/orderModifyInfo" method="post"
						id="orderModifyForm">
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2 swidth">客户电话</label>
								<div class="col-sm-5">
									<input class="form-control" name="customerPhone" type="text">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2 swidth">订单金额</label>
								<div class="col-sm-5">
									<input class="form-control" name="payPrice" type="text">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="form-group">
								<label class="control-label col-md-2 swidth">商品类型</label>
								<div class="col-md-7">
									<label class="radio-inline"> <input name="orderType"
										type="radio" value="1"><span>已售商品</span>
									</label> <label class="radio-inline"> <input name="orderType"
										type="radio" value="2"><span>退还商品</span>
									</label>
								</div>
							</div>
						</div>
						<input type="hidden" value="" name="orderId" />
					</form>
					<div class="modal-footer">
						<button class="btn btn-primary" type="button"
							onclick="javascript: $('#orderModifyForm').submit();$('#searchList').click();">修改</button>
						<button class="btn btn-default-outline" data-dismiss="modal"
							type="button">关闭</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
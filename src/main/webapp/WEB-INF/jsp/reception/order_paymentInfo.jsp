<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单支付详情</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/reception/order_paymentInfo.js"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<%-- 	<jsp:include page="/WEB-INF/jsp/common/head.jsp" /> --%>
	<jsp:include page="/WEB-INF/jsp/customer/head.jsp" />
	<input type="hidden" id="orderNumber" value="${orderNumber }" />
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<!-- DataTables Example -->
						<table class="table table-striped" id="paymentInfo">
							<thead>
								<tr>
									<th>序号</th>
									<th>付款方式</th>
									<th>收款账户</th>
									<th>应收</th>
									<th>实收</th>
									<th>手续费</th>
									<th></th>
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
					<h4 class="modal-title">支付详情</h4>
				</div>
				<div class="modal-body">
					<form action="order/paymentInfpUpdate" method="post"
						id="paymentInfpUpdate">
						<input type="hidden" value="" name="orderNumber" /> <input
							type="hidden" value="" name="orderId" /> <label
							class="control-label" for=""">收款账户</label> <select
							name="paymentAccountId" id="paymentAccountId"
							class="form-control"><option value="Category 1">Option
								1</option>
							<option value="Category 2">Option 2</option>
							<option value="Category 3">Option 3</option>
							<option value="Category 4">Option 4</option></select> <label
							class="control-label" for="payType">收款方式</label> <select
							name="payType" id="payType" class="form-control">
							<option value="1">信用卡</option>
							<option value="2">储蓄卡（封顶）</option>
							<option value="3">储蓄卡（不封顶）</option>
							<option value="4">支付宝</option>
							<option value="5">微信</option>
							<option value="6">现金</option>
						</select> <label class="control-label" for="amount">收款金额</label> <input
							class="form-control" name="amount" id="amount" type="text">
						<label class="control-label" for="amountActual">实际收款金额</label> <input
							class="form-control" name="amountActual" id="amountActual"
							type="text"> <label class="control-label" for="poundage">手续费</label>
						<input class="form-control" name="poundage" id="poundage"
							type="text">
					</form>
					<div class="modal-footer">
						<button class="btn btn-primary" type="button"
							onclick="javascript: $('#paymentInfpUpdate').submit();">修改</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
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
</body>
</html>
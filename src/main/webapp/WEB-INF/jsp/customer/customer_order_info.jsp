<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户出单详情</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/customer/customer_order_info.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	<input type="hidden" value="${customerId }" id="customerId">
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<!-- DataTables Example -->
						<table class="table table-bordered" id="customerOrderInfo">
							<thead>
								<th>序号</th>
								<th>接待人员</th>
								<th>出单商品&数量</th>
								<th>金额</th>
								<th>实付金额</th>
								<th>创建日期</th>
								<th>状态</th>
								<th>支付类型</th>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务订单列表</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/finance_order_everyday.js" type="text/javascript"></script>
<script src="resources/js/jQuery.print.js" type="text/javascript"></script>
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
							<label class="control-label col-md-2 swidth">对账日期</label>
							<div class="col-sm-2">
				            	<input class="form-control" data-date-autoclose="true" data-date-format="yyyy-mm-dd" id="financeDate" placeholder="开始时间" type="text">
				            </div>
						</div>
						<div class="form-group">
							<div class="col-md-7">
								<button class="btn btn-primary" id="searchList">搜索</button>
							</div>
						</div>
						<!-- DataTables Example -->
						<table class="table table-bordered" id="financeOrderDate">
							<thead>
								<th>序号</th>
								<th>订单编号</th>
								<th>成交商品&数量</th>
								<th>成交金额</th>
								<th>接待人员</th>
								<th>客服人员</th>
								<th>成交时间</th>
								<th>操作</th>
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
	<input type="hidden" value="${userId }" id="hiddenUserId"/>
	<div class="modal fade" id="printInfo">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">打印</h4>
				</div>
				<div class="modal-body">
					<h4>
						选择单一模版
                    </h4>
                    <p>
                    	<button class="btn btn-primary" type="button" id="outgoing">出库单</button>
<!-- 						<button class="btn btn-primary" type="button" id="outNoTake">全款已付货未取</button> -->
						<button class="btn btn-primary" type="button" id="receiveMoney">收款收据</button>
                    </p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
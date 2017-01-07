<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务订单列表</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/finance_order_list.js" type="text/javascript"></script>
<script src="resources/js/jQuery.print.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<%-- 	<jsp:include page="/WEB-INF/jsp/common/head.jsp" /> --%>
	<jsp:include page="/WEB-INF/jsp/customer/head.jsp" />
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<div class="form-group">
							<label class="control-label col-md-2 swidth">订单号</label>
							<div class="col-sm-2">
				            	<input class="form-control" id="orderNumber" type="text">
				            </div>
						</div>
						<div class="form-group">
							<div class="col-md-7">
								<button class="btn btn-primary" id="searchList">搜索</button>
							</div>
						</div>
						<!-- DataTables Example -->
						<table class="table table-bordered" id="financeOrderExam">
							<thead>
								<th>序号</th>
								<th>订单编号</th>
								<th>订单类型</th>
								<th>支付类型</th>
								<th>订单金额</th>
								<th>库房审核状态</th>
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
<!--                     	<button class="btn btn-primary" type="button" id="outgoing">出库单</button> -->
						<button class="btn btn-primary" type="button" id="outNoTake">全款已付货未取</button>
						<button class="btn btn-primary" type="button" id="receiveMoney">收款收据</button>
                    </p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="receiveMoneyInfo" style="display:none;">
		<div class="tc"><h3>收 款 收 据</h3></div>
		<div class="text-left"><span name="customerName">客户：</span></div><div><span name="today">日期：</span></div>
		<div class="table-margin">
			<table border="1">
				<tr>
					<th>商品名称</th>
					<th>数量</th>
					<th>单位</th>
					<th>单价</th>
					<th>订 金</th>
				</tr>
				<tbody name="detail"></tbody>
				<tr>
					<td>大写金额：</td>
					<td colspan="4" name="priceCn"></td>
				</tr>
			</table>
		</div>
		<div class="text-left"><span name="receiverStaffName">接待：</span></div><div><span name="phoneStaffName">客服：</span></div>
	</div>
	
	<div id="hasPayInfo" style="display:none;">
		<div class="tc"><h3>全款已付货未取</h3></div>
		<div class="text-left"><span name="customerName">客户：</span></div><div><span name="today">日期：</span></div>
		<div class="table-margin">
			<table border="1">
				<tr>
					<th style="width:150px;">商品名称</th>
					<th style="width:150px;">单位</th>
					<th style="width:150px;">数量</th>
					<th style="width:150px;">金额</th>
				</tr>
				<tbody name="detail"></tbody>
				<tr>
					<td>备注：</td>
					<td colspan="4" name="remark"></td>
				</tr>
			</table>
		</div>
		<div class="text-left"><span name="receiverStaffName">接待：</span></div><div><span name="phoneStaffName">客服：</span></div>
	</div>
	
	<div class="modal fade" id="orderSettlement">
		<div class="modal-dialog" style="width:1300px;">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">收款</h4>
				</div>
              	<form id="updateCivi" action="workflow/updateOrderAndInsert" method="post">
              		<input type="hidden" name="orderNumber"/>
              		<input type="hidden" name="orderId"/>
              		<div class="row">
	              		<div class="form-group">
				            <div class="col-md-7">
				            	<a name="addSettlement">添加收款</a>
				            </div>
				    	</div>
			    	</div>
              		<div class="row base-column">
		              	<div class="form-group form-data">
				            <label class="control-label col-md-1" style="width:6%;">收款账户</label>
				            <div class="col-md-2">
				            	<select name="paymentAccountId" id="paymentAccountId" class="form-control"><option value="Category 1">Option 1</option><option value="Category 2">Option 2</option><option value="Category 3">Option 3</option><option value="Category 4">Option 4</option></select>
				            </div>
				            <label class="control-label col-md-1" style="width:6%;">收款方式</label>
				            <div class="col-md-2">
				            	<select name="customerPayType" id="customerPayType" class="form-control"><option value="Category 1">Option 1</option><option value="Category 2">Option 2</option><option value="Category 3">Option 3</option><option value="Category 4">Option 4</option></select>
				            </div>
				            <label class="control-label col-md-1" style="width:6%;">收款金额</label>
				            <div class="col-md-1">
				            	<input class="form-control" name="payAmount" id="payAmount" type="text">
				            </div>
				            <label class="control-label col-md-1" style="width:6%;">实际收款金额</label>
				            <div class="col-md-1">
				            	<input class="form-control" name="payAmountActual" id="payAmountActual" type="text">
				            </div>
				            <label class="control-label col-md-1" style="width:6%;">手续费</label>
				            <div class="col-md-1">
				            	<input class="form-control" name="poundage" id="poundage" type="text">
				            </div>
				    	</div>
				    	<div class="form-group">
				            <div class="col-md-1">
				            	<a name="delete" style="display:none;">删除</a>
				            </div>
				    	</div>
			    	</div>
              	</form>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" id="commitForm" data-flag="1">确认</button>
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
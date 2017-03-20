<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库管理列表</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/store_order_info.js" type="text/javascript"></script>
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
							<label class="control-label col-md-2 swidth">订单编号</label>
							<div class="col-sm-2">
				            	<input class="form-control" placeholder="订单编号" type="text" id="orderNumber" name="orderNumber">
				            </div>
				            <label class="control-label col-md-2 swidth">客户电话</label>
							<div class="col-sm-2">
				            	<input class="form-control" data-date-autoclose="true" data-date-format="yyyy-mm-dd" id="customerPhone" type="text">
				            </div>
				            
				            <label class="control-label col-md-2 swidth">客户姓名</label>
							<div class="col-sm-2">
				            	<input class="form-control" data-date-autoclose="true" data-date-format="yyyy-mm-dd" id="customerName" type="text">
				            </div>
				            
				            <label class="control-label col-md-2 swidth">接待人员</label>
							<div class="col-sm-2">
				            	<input class="form-control" data-date-autoclose="true" data-date-format="yyyy-mm-dd" id="receiverName" type="text">
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
								<th>接待人员</th>
								<th>客服人员</th>
								<th>客户姓名</th>
								<th>订单编号</th>
								<th>成交商品&数量</th>
								<th>成交金额</th>
								<th>订单状态</th>
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
	<input type="hidden" name="orderId"/>
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
                    	<button class="btn btn-primary" type="button" id="collect">收藏票</button>
						<button class="btn btn-primary" type="button" id="outNoTake">全款已付货未取</button>
<!-- 						<button class="btn btn-primary" type="button" id="receiveMoney">收款收据</button> -->
                    </p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="collectInfo" style="display:none;">
		<div class="tc"><h3>国韵典藏文化中心</h3></div>
		<div style="width: 40%;margin-left: 46%;"><span name="customerName">专用收藏票</div>
		<div class="table-margin">
			<table border="1">
				<tr>
					<th rowspan="2">藏品名称</th>
					<th rowspan="2">数量</th>
					<th rowspan="2">单位</th>
					<th rowspan="2">单价（元）</th>
					<th colspan="9">金额</th>
				</tr>
				<tr>
					<th>佰</th><th>拾</th><th>万</th><th>仟</th><th>佰</th><th>拾</th><th>元</th><th>角</th><th>分</th>
				</tr>
				<tbody name="detail"></tbody>
				<tr>
					<td>大写金额：</td>
					<td colspan="13" name="remark"></td>
				</tr>
			</table>
		</div>
		<div class="text-left"><span name="receiverStaffName">开票人：</span></div>
	</div>
	
	<div id="hasPayInfo" style="display:none;">
		<div class="tc"><h3>全款已付货未取</h3></div>
		<div class="text-left"><span name="customerName">客户：</span></div><div><span name="today">日期：</span></div>
		<div class="table-margin">
			<table border="0">
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
</body>
</html>
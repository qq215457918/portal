<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文交所订单列表</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/civilization_exchange_list.js" type="text/javascript"></script>
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
				            <label class="control-label col-md-2 swidth">客户电话</label>
							<div class="col-sm-2">
				            	<input class="form-control" id="customerPhone" type="text">
				            </div>
				            
				            <label class="control-label col-md-2 swidth">客户姓名</label>
							<div class="col-sm-2">
				            	<input class="form-control" id="customerName" type="text">
				            </div>
				            
				            <label class="control-label col-md-2 swidth">接待人员</label>
							<div class="col-sm-2">
				            	<input class="form-control" id="receiverName" type="text">
				            </div>
						</div>
						<div class="form-group">
							<div class="col-md-7">
								<button class="btn btn-primary" id="searchList">搜索</button>
							</div>
						</div>
						<!-- DataTables Example -->
						<table class="table table-bordered" id="civilizationOrderList">
							<thead>
								<th>序号</th>
								<th>订单编号</th>
								<th>接待人员</th>
								<th>客户电话</th>
								<th>客户姓名</th>
								<th>订单类型</th>
								<th>支付类型</th>
								<th>审核状态</th>
								<th>配送个数</th>
								<th>配售个数</th>
								<th>详细</th>
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
	
	<div class="modal fade" id="goodsDetailList">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">商品详情</h4>
				</div>
				<div class="widget-content padded clearfix">
	                <table class="table table-bordered">
	                  <thead>
	                    <tr>
	                    	<th>商品名称</th>
	                    	<th>商品编号</th>
	                    	<th>商品类型 </th>
	                    	<th>数量</th>
	                  	</tr>
	                  </thead>
	                  <tbody>
	                  </tbody>
	                </table>
              	</div>
              	<form id="updateCivi" action="workflow/updateCivilizationInfo" method="post">
	              	<div class="widget-content padded clearfix">
	              		<div class="form-group">
							<label class="control-label col-md-2 swidth">备注</label>
							<div class="col-sm-10">
				            	<input class="form-control" name="cultureRemark" id="cultureRemark" type="text">
				            </div>
						</div>
	              	</div>
	              	<input type="hidden" name="orderId"/>
	              	<input type="hidden" name="cultureFlag"/>
              	</form>
				<div class="modal-footer">
					<button class="btn btn-primary" data-dismiss="modal" type="button" data-flag="1">确认</button>
					<button class="btn btn-danger" data-dismiss="modal" type="button" data-flag="-1">驳回</button>
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>回购查询页面</title>
<base href="${basePath}">
<jsp:include page="head.jsp" />
<script type="text/javascript"
	src="resources/js/reception/repurchase_query.js"></script>
</head>
<body>
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="page-title">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-container">
							<div class="widget-content padded">
								<div class="row">
									<div class="col-xs-12">
										<div class="widget-container fluid-height clearfix">
											<div class="heading">
												<i class="icon-table"></i>回购查询页面
											</div>
											<div class="widget-content padded">
												<form action="#" class="form-horizontal">
													<div class="form-group">

														<div class="col-md-6">
															<label class="control-label col-md-4">商品信息</label>
															<div class="col-md-8">
																<input class="form-control" placeholder="请输入商品名称"
																	type="text" id="goodName">
															</div>
														</div>

													</div>
												</form>
												<div class="col-xs-12">
													<button class="btn btn-success">查 询</button>
												</div>
											</div>
											<div class="widget-content padded clearfix">
												<table class="table table-striped" id="orderTable">
													<thead>
														<tr>
															<th>成单号</th>
															<th>商品名称</th>
															<th>商品数量</th>
															<th>交易金额</th>
															<th>回购金额</th>
															<th>交易日期</th>
															<th>接待员</th>
															<th>回购状态</th>
															<th>成单状态</th>
															<th>操作</th>
														</tr>
													</thead>
													<tbody>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 特殊审批  modal Start -->
				<div class="modal fade" id="specialModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" class="close" data-dismiss="modal"
									type="button">&times;</button>
								<h4 class="modal-title">请输入申请回购原因</h4>
							</div>
							<div class="modal-body">
								商品名称： <input class="form-control" value="" type="text"
									id="specialGoodsName" readonly="readonly"> 回购数量： <input
									class="form-control" value="" type="text" id="specialCount"
									readonly="readonly"> 交易金额： <input class="form-control"
									value="" type="text" id="specialPriceOld" readonly="readonly">
								回购单价：
								<div class="input-group">
									<span class="input-group-addon">¥</span><input
										class="form-control" type="text" id="specialPrice"><span
										class="input-group-addon">.00</span>
								</div>
								申请原因：
								<textarea class="form-control" rows="3" id="specialReason"></textarea>
							</div>
							<input id="specialGoodsId" name="specialGoodsId" type="hidden" />
							<div class="modal-footer">
								<button class="btn btn-primary" type="button"
									id="specialConfirm">确 认</button>
								<button class="btn btn-default-outline" data-dismiss="modal"
									type="button">取 消</button>
							</div>
						</div>
					</div>
				</div>
				<!-- modal end -->

				<!--一般回购  modal Start -->
				<div class="modal fade" id="normalModal">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button aria-hidden="true" class="close" data-dismiss="modal"
									type="button">&times;</button>
								<h4 class="modal-title">请输入回购金额</h4>
							</div>
							<div class="modal-body">
								商品名称： <input class="form-control" value="" type="text"
									id="normalGoodsName" readonly="readonly"> 回购数量： <input
									class="form-control" value="" type="text" id="normalCount"
									readonly="readonly"> 交易金额： <input class="form-control"
									value="" type="text" id="normalPriceOld" readonly="readonly">
								回购单价：
								<div class="input-group">
									<span class="input-group-addon">¥</span><input
										class="form-control" type="text" id="normalPrice"><span
										class="input-group-addon">.00</span>
								</div>
							</div>
							<input id="normalGoodsId" name="normalGoodsId" type="hidden" />
							<div class="modal-footer">
								<button class="btn btn-primary" type="button" id="normalConfirm">确
									认</button>
								<button class="btn btn-default-outline" data-dismiss="modal"
									type="button">取 消</button>
							</div>
						</div>
					</div>
				</div>
				<!-- modal end -->

			</div>
		</div>
	</div>
</body>
</html>
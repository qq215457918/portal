<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务员每日业绩查看</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/clerk_everyday_achievement.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/customer/customer_info_index.css" />
</head>
<body>
<%-- 	<jsp:include page="/WEB-INF/jsp/common/head.jsp" /> --%>
	<jsp:include page="/WEB-INF/jsp/customer/head.jsp" />
	<input type="hidden" value="${receiverStaffId }" id="receiverStaffId"/>
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="row">
				<div class="col-lg-12">
					<div class="widget-container fluid-height clearfix">
						<div class="widget-content padded">
							<form action="workflow/clerkEverydayAchievenment" id="flowForm" method="post">
								<div class="form-group">
									<label class="control-label col-md-2 swidth">日期</label>
									<div class="col-md-2">
										<div class="input-group date datepicker">
											<input class="form-control" type="text" value="${dateInfo }" name="dateInfo" id="dateInfo">
												<span class="input-group-addon">
												<i class="icon-calendar"></i>
											</span>
										</div>
						            </div>
								</div>
							</form>
							<c:if test="${empty employeeId }">
								<div class="form-group">
									<div class="col-md-7">
										<button class="btn btn-primary" id="searchAchieve">查询</button>
									</div>
									<div class="col-md-7">
										<c:if test="${hasCommitDaily>0 }">
											<button class="btn btn-primary">已提交</button>
										</c:if>
										<c:if test="${hasCommitDaily==0 }">
											<button class="btn btn-primary" id="toAchieveExam">提交审核</button>
										</c:if>
									</div>
								</div>
							</c:if>
						</div>
					</div>
					
					<div class="widget-container stats-container">
						<div class="col-md-4">
							<div class="number">
								<div class="icon visitors"></div>
								${result.receptionCount }
							</div>
							<div class="text">接待总数</div>
						</div>
						<div class="col-md-4">
							<div class="number">
								<div class="icon chat-bubbles"></div>
								${result.stateTime }<small>分</small>
							</div>
							<div class="text">接待总时长</div>
						</div>
						<div class="col-md-4">
							<div class="number">
								<div class="icon chat-bubbles"></div>
								${result.achieveCount }
							</div>
							<div class="text">出单数</div>
						</div>
					</div>
					<div class="widget-container stats-container">
						<div class="col-md-4">
							<div class="number">
								<div class="icon money"></div>
								${result.payPrice }<small>元</small>
							</div>
							<div class="text">订单总金额</div>
						</div>
						<div class="col-md-4">
							<div class="number">
								<div class="icon money"></div>
								${result.actualPrice }<small>元</small>
							</div>
							<div class="text">实付总金额</div>
						</div>
						<div class="col-md-4">
							<div class="number" style="font-size:1.1em">
								<c:if test="${not empty result.typeCount }">
									<c:forEach items="${result.typeCount}" var="list" varStatus="index"> 
										<c:if test="${index.last }">
											${list }
										</c:if>
										<c:if test="${!index.last }">
											${list }<br/>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
							<div class="text">接待统计</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="heading">
						<i class="icon-table"></i>
						审核信息
			        </div>
					<div class="widget-content padded">
						<!-- DataTables Example -->
						<table class="table table-striped" id="examHistoryInfo">
							<thead>
								<th>序号</th>
								<th>审核人</th>
								<th>审核日期</th>
								<th>审核意见</th>
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
	
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="heading">
						<i class="icon-table"></i>
						每日出单
			        </div>
					<div class="widget-content padded">
						<!-- DataTables Example -->
						<table class="table table-striped" id="clerkOrderInfo">
							<thead>
								<th>序号</th>
								<th>出单编号</th>
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
	
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="heading">
						<i class="icon-table"></i>
						每日接待
			        </div>
					<div class="widget-content padded">
						<!-- DataTables Example -->
						<table class="table table-striped" id="clerkReceiveInfo">
							<thead>
								<th>序号</th>
								<th>客户姓名</th>
								<th>客户类型</th>
								<th>客户电话</th>
								<th>开始接待时间</th>
								<th>结束接待时间</th>
								<th>出单编号</th>
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
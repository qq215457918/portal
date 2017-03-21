<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业绩审核</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/achieve_exam_info.js" type="text/javascript"></script>
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
							<label class="control-label col-md-2 swidth">业务员</label>
							<div class="col-sm-2">
				            	<input class="form-control" id="backCountS" type="text">
				            </div>
						</div>
						<div class="form-group">
							<div class="col-md-7">
								<button class="btn btn-primary" id="searchList">搜索</button>
							</div>
						</div>
						<!-- DataTables Example -->
						<table class="table table-striped" id="achieveExam">
							<thead>
								<th>序号</th>
								<th>时间</th>
								<th>审核人</th>
								<th>审批信息</th>
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
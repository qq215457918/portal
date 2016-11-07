<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业绩审核列表</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/achieve_exam_list.js" type="text/javascript"></script>
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
						<table class="table table-bordered" id="achieveExam">
							<thead>
								<th>序号</th>
								<th>业务员编号</th>
								<th>业务员名称</th>
								<th>任务名称</th>
								<th>审核人</th>
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
	<div class="modal fade" id="detailModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">审批历史</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="widget-container fluid-height clearfix">
								<div class="widget-content padded clearfix">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>审批人</th>
												<th>审批时间</th>
												<th>审批信息</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="examModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button aria-hidden="true" class="close" data-dismiss="modal"
						type="button">&times;</button>
					<h4 class="modal-title">审核</h4>
				</div>
				<div class="modal-body">
					<form action="workflow/commitExam" method="post">
						<h4>
							审核信息
	                    </h4>
	                    <p>
	                    	<textarea class="examMessage" name="examMessage"></textarea>
	                    </p>
	                    <input type="hidden" name="taskId"/>
	                    <input type="hidden" name="id"/>
	                    <input type="hidden" name="suggestion"/>
                    </form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" onclick="commitExam('pass');">通过</button>
					<button class="btn btn-primary" type="button" onclick="commitExam('turnDown');">驳回</button>
					<button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
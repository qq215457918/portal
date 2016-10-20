<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程信息列表</title>
<base href="${basePath}" />
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<script src="resources/js/flow/flow_info_index.js" type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/customer/customer_info_index.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	<div class="modal-shiftfix">
		<div class="container-fluid main-content">
			<div class="col-lg-12">
				<div class="widget-container fluid-height clearfix">
					<div class="widget-content padded">
						<form action="workflow/insertFlowZip" id="flowForm" method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label class="control-label col-md-2 swidth">流程名称</label>
								<div class="col-md-2">
									<input class="form-control" placeholder="流程名称" type="text" id="fileName" name="fileName">
								</div>
	
								<label class="control-label col-md-2 swidth">流程文件上传</label>
								<div class="col-md-4">
									<div class="fileupload fileupload-new"
										data-provides="fileupload">
										<div class="input-group">
											<div class="form-control">
												<i class="icon-file fileupload-exists"></i>
												<span class="fileupload-preview"></span>
											</div>
											<div class="input-group-btn">
												<a class="btn btn-default fileupload-exists"
													data-dismiss="fileupload" href="#">删除文件</a><span
													class="btn btn-default btn-file"><span
													class="fileupload-new">选择文件</span><span
													class="fileupload-exists">重新选择</span><input type="file" id="flowFile" name="flowFile"></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
						<div class="form-group">
							<div class="col-md-7">
								<button class="btn btn-primary" id="uploadFLow">上传流程</button>
							</div>
						</div>
						
						<!-- DataTables Example -->
						<div class="heading">
							<i class="icon-table"></i>流程部署信息列表
						</div>
						<table class="table table-bordered" id="flowDepInfo">
							<thead>
								<th>序号</th>
								<th>ID</th>
								<th>流程名称</th>
								<th>发布时间</th>
								<th>操作</th>
							</thead>
							<tbody>
							</tbody>
						</table>
						<!-- end DataTables Example -->
						<!-- DataTables Example -->
						<div class="heading">
							<i class="icon-table"></i>流程定义信息列表
						</div>
						<table class="table table-bordered" id="flowPdInfo">
							<thead>
								<th>序号</th>
								<th>ID</th>
								<th>名称</th>
								<th>流程定义KEY</th>
								<th>流程定义版本</th>
								<th>流程定义规则文件名称</th>
								<th>流程定义规则图片名称</th>
								<th>部署ID</th>
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
	</div>
</body>
</html>
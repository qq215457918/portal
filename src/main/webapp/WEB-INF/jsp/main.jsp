<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>控制台</title>
<base href="${basePath}">
</head>
<body style="padding-top: 0px;">
	<div class="modal-shiftfix">
		<div class=" main-content">
			<div class="page-title">
				<div class="breadcrumb">
					<i><img width="40" height="40" src="resources/images/logo.png"
						style="margin-right: 20px">你好：<shiro:principal /> ,
						欢迎使用本系统，祝您工作愉快。</i> <a class="btn btn-xs btn-default"
						style="margin-top: 10px; float: right;"
						href="<%=request.getContextPath()%>/password/modify">修改密码</a>
				</div>
			</div>
			<div class="row">
				<shiro:hasPermission name="job:button">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<div class="thumbnail" style="height: 352px;">
							<div class="row text-center">
								<div class="number" style="font-size: 50px; margin-top: 20px;">
									我的工作</div>
								<a class="btn btn-lg btn-info" style="margin-top: 50px"
									href="<%=request.getContextPath()%>/job/init?active=1"><i
									class="icon-briefcase"></i>进入</a>
							</div>
						</div>
					</div>
				</shiro:hasPermission>

				<shiro:hasPermission name="visit:button">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<div class="thumbnail" style="height: 352px;">
							<div class="row text-center">
								<div class="number" style="font-size: 50px; margin-top: 20px;">
									接待管理</div>
								<a class="btn btn-lg btn-info" style="margin-top: 50px"
									href="<%=request.getContextPath()%>/visit/query?active=1"><i
									class="icon-glass"></i>进入</a>
							</div>
						</div>
					</div>
				</shiro:hasPermission>
				<shiro:hasPermission name="telephone:button">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<div class="thumbnail" style="height: 352px;">
							<div class="row text-center">
								<div class="number" style="font-size: 50px; margin-top: 20px;">
									数据管理</div>
								<a class="btn btn-lg btn-info" style="margin-top: 50px"
									href="customerInfo/costomerInfoIndex?type=0&active=1"
									style="margin-top: 20px"><i class="icon-phone-sign"></i>进入</a>
							</div>
						</div>

					</div>
				</shiro:hasPermission>

				<shiro:hasPermission name="statistics:button">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<div class="thumbnail" style="height: 352px;">

							<div class="row text-center">
								<div class="number" style="font-size: 50px; margin-top: 20px;">
									统计/报表</div>
								<a class="btn btn-lg btn-info" style="margin-top: 50px"
									href="report/toSalesmanStatement?active=1"
									style="margin-top: 20px"><i class="icon-qrcode"></i>进入</a>
							</div>
						</div>
					</div>

				</shiro:hasPermission>
				<shiro:hasPermission name="admin:button">
					<div class="col-sm-6 col-md-4 col-lg-3">
						<div class="thumbnail" style="height: 352px;">

							<div class="row text-center">
								<div class="number" style="font-size: 50px; margin-top: 20px;">
									后台管理</div>
								<a class="btn btn-lg btn-info" style="margin-top: 50px"
									href="admin/resource/init?active=1" style="margin-top: 20px"><i
									class="icon-laptop"></i>进入</a>
							</div>
						</div>

					</div>
				</shiro:hasPermission>
				<div class="col-sm-6 col-md-4 col-lg-3">
					<div class="thumbnail" style="height: 352px;">

						<div class="row text-center">
							<div class="number" style="font-size: 50px; margin-top: 20px;">
								退出</div>
							<a class="btn btn-lg btn-danger" style="margin-top: 50px"
								href="<%=request.getContextPath()%>/logout"><i
								class="icon-mail-forward"></i>退出</a>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
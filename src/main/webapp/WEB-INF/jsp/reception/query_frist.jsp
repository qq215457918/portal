<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>首次登陆</title>
<base href="${basePath}">
<%-- <jsp:include page="head.jsp" /> --%>
</head>
<body>
	<div class="modal-shiftfix">
		<div class="row">
			<div class="col-xs-12">
				<div class="widget-container fluid-height clearfix">
					<div class="heading">
						<i class="icon-tags"></i>用户详细信息
					</div>
					<div class="col-xs-6">
						<div class="widget-content padded">
							<dl>
								<dd>
									<strong> 客户类型： </strong>${result.type }
								</dd>
								<dd>
									<strong>用户姓名：</strong> ${result.name }
								</dd>
								<dd>
									<strong> 电话：</strong>${result.encryptPhone }
								</dd>
								<dd>
									<strong> 备用电话：</strong>${result.encryptPhone2 }
								</dd>
								<dd>
									<strong>最近登门时间：</strong>
									<fmt:formatDate value="${result.recentVisitDate}"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</dd>
							</dl>
							<button class="btn btn-lg btn-primary" id="receiveId">开始接待</button>

						</div>
					</div>
					<div class="col-xs-6">
						<div class="widget-content padded">
							<dl>
								<dd>
									<strong> 关联亲友：</strong>${result.relationId }
								</dd>
								<dd>
									<strong> 客服人员：</strong>${result.phoneStaffName }
								</dd>
								<dd>
									<strong> 接待人员：</strong>${result.receiverStaffName }
								</dd>
								<dd>
									<strong>是否黑名单：</strong>${result.blacklistFlag }
								</dd>
								<dd>
									<strong> </strong>&nbsp
								</dd>

							</dl>
							<button class="btn btn-lg btn-default" id="quit">退出</button>

						</div>
					</div>
					<input type="hidden" name="cid" id="cid" value="${result.id }" />
					<input type="hidden" name="phone" id="cphone"
						value="${result.phone }" /> <input type="hidden"
						name="receiverStaffName" id="receiverStaffName"
						value="${result.receiverStaffName}" />

				</div>
				<div class="widget-container fluid-height clearfix"></div>
			</div>
		</div>
	</div>
</body>
<script>
	$(function() {
		base = $("base").attr('href');
		// 查询功能
		$("#receiveId").click(
				function() {
					var cid = $('#cid').val();
					var phone = $('#cphone').val();
					var receiverStaffName = $('#receiverStaffName').val();
					window.location.href = base + "/visit/second?active=1&cId="
							+ cid + "&receiverStaffName=" + receiverStaffName;
				});

		// 查询功能
		$("#quit").click(function() {
			var id = $('#cid').val();
			window.location.href = base + "/visit/quit?id=" + id;
		});

	});
</script>
</html>
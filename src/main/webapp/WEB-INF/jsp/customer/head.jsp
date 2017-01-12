<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar navbar-fixed-top scroll-hide">
	<div class="container-fluid top-bar">
		<div class="pull-right">
			<ul class="nav navbar-nav pull-right">
				<li class="dropdown user hidden-xs"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#"> 你好：<shiro:principal /> <b
						class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<%=request.getContextPath()%>/login"> <i
								class="icon-home"></i>返回工作台
						</a></li>
						<li><a href="<%=request.getContextPath()%>/logout"> <i
								class="icon-signout"></i>退出系统
						</a></li>
					</ul></li>
			</ul>
		</div>
		<button class="navbar-toggle">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
	</div>

	<%-- 下面的内容为页面导航 --%>
  
  <div class="container-fluid main-nav clearfix">
  	<input type="hidden" id="active" name="active" value="${active}" />
	<div class="nav-collapse">
	  <ul class="nav">
	  	<shiro:hasPermission name="user:*">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="se7en-tables"></span>客户信息<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
				<li><a name="tab" href="${base}customerInfo/costomerInfoIndex?type=0">空白客户</a></li>
				<li><a name="tab" href="${base}customerInfo/costomerInfoIndex?type=1">重复登门客户</a></li>
				<li><a name="tab" href="${base}customerInfo/costomerInfoIndex?type=3">成单客户</a></li>
				<li><a name="tab" href="${base}customerInfo/costomerInfoIndex?type=4">锁定客户</a></li>
			  </ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="job:daily*">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="se7en-tables"></span>每日业绩审核<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
				  <shiro:hasPermission name="job:dailyachieve">
					<li><a name="tab" href="${base}workflow/clerkEverydayAchievenment">个人业绩审核</a></li>
				  </shiro:hasPermission>
				  <shiro:hasPermission name="job:dailybossachieve">
					<li><a name="tab" href="${base}workflow/achieveExamList">主管业绩审核</a></li>
				  </shiro:hasPermission>
			  </ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="job:modifyorder">
			<li>
			  <a name="tab" href="${base}order/orderModifyIndex" > <span aria-hidden="true" class="se7en-tables"></span>订单修改</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="job:finance">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="se7en-tables"></span>财务订单<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
				<li><a name="tab" href="${base}workflow/financeOrderList">财务订单列表</a></li>
	<%-- 			<li><a name="tab" href="${base}workflow/financeOrderEveryday">财务对账</a></li> --%>
			  </ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="job:culture">
			<li>
			  <a name="tab" href="${base}workflow/civilizationExchangeIndex" > <span aria-hidden="true" class="se7en-tables"></span>文交所管理</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="job:warehouse">
			<li>
			  <a name="tab" href="${base}workflow/storeOrderinfo" > <span aria-hidden="true" class="se7en-tables"></span>仓库管理</a>
			</li>
		</shiro:hasPermission>
	  </ul>
	</div>
  </div>
</div>
<%-- 回显导航选中状态 --%>
<script type="text/javascript">
	showTab();
</script>
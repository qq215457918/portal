<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-fixed-top scroll-hide">
  <div class="container-fluid top-bar">
    <div class="pull-right">
	  <ul class="nav navbar-nav pull-right">
		<li class="dropdown notifications hidden-xs">
		  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-flag"></span>
			<div class="sr-only">Notifications</div>
			<p class="counter">4</p>
		  </a>
		  <ul class="dropdown-menu">
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>New user added: Jane Smith</p>
			  </a>
			</li>
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>Sales targets available</p>
			  </a>
			</li>
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>New performance metric added</p>
			  </a>
			</li>
			<li>
			  <a href="#">
				<div class="notifications label label-info">New</div>
				<p>New growth data available</p>
			  </a>
			</li>
		  </ul>
		</li>
		<li class="dropdown messages hidden-xs">
		  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-envelope"></span>
				<div class="sr-only">Messages</div>
				<p class="counter">3</p>
		  </a>
		  <ul class="dropdown-menu messages">
			<li>
			  <a href="#">
			  	<img width="34" height="34" src="${basePath}resources/images/avatar-male2.png" />Could we meet today? I wanted...
			  </a>
			</li>
			<li>
			  <a href="#">
				<img width="34" height="34" src="${basePath}resources/images/avatar-female.png" />Important data needs your analysis...
			  </a>
			</li>
			<li>
			  <a href="#">
			    <img width="34" height="34" src="${basePath}resources/images/avatar-male2.png" />Buy Se7en today, it's a great theme...
			  </a>
			</li>
		  </ul>
		</li>
		<li class="dropdown settings hidden-xs">
		  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
		  	<span aria-hidden="true" class="se7en-gear"></span>
			<div class="sr-only">Settings</div>
		  </a>
		  <ul class="dropdown-menu">
			<li>
			  <a class="settings-link blue" href="javascript:chooseStyle('none', 30)"><span></span>Blue</a>
			</li>
			<li>
			  <a class="settings-link green" href="javascript:chooseStyle('green-theme', 30)"><span></span>Green</a>
			</li>
			<li>
			  <a class="settings-link orange" href="javascript:chooseStyle('orange-theme', 30)"><span></span>Orange</a>
			</li>
			<li>
			  <a class="settings-link magenta" href="javascript:chooseStyle('magenta-theme', 30)"><span></span>Magenta</a>
			</li>
			<li>
				<a class="settings-link gray" href="javascript:chooseStyle('gray-theme', 30)"><span></span>Gray</a>
			</li>
		  </ul>
		</li>
		<li class="dropdown user hidden-xs">
		  <a data-toggle="dropdown" class="dropdown-toggle" href="#">
		  	<img width="34" height="34" src="${basePath}resources/images/avatar-male.jpg" />John Smith<b class="caret"></b>
		  </a>
			<ul class="dropdown-menu">
			  <li><a href="#"><i class="icon-user"></i>My Account</a></li>
			  <li><a href="#"> <i class="icon-gear"></i>Account Settings</a></li>
			  <li><a href="login1.html"> <i class="icon-signout"></i>Logout</a></li>
			</ul>
		</li>
	  </ul>
	</div>
	<button class="navbar-toggle">
	  <span class="icon-bar"></span>
	  <span class="icon-bar"></span>
	  <span class="icon-bar"></span>
	</button>
	<!-- 系统LOGO -->
	<a class="logo" href="index-2.html">se7en</a>
	<!-- 系统全站查询 -->
	<!-- <form class="navbar-form form-inline col-lg-2 hidden-xs">
	  <input class="form-control" placeholder="Search" type="text">
	</form> -->
  </div>
  
  <%-- 下面的内容为页面导航 --%>
  
  <div class="container-fluid main-nav clearfix">
  	<input type="hidden" id="active" name="active" value="${active}" />
	<div class="nav-collapse">
	  <ul class="nav">
		<li>
		  <a href="javascript:alert('进入系统首页');"><span aria-hidden="true" class="se7en-home"></span>主页</a>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#"> 
		  	<span aria-hidden="true" class="se7en-tables"></span>接待统计<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<%-- <li><a name="tab" href="${base}report/toReceiveStatistics?active=0">接待客户统计</a></li> --%>
			<li><a name="tab" href="${base}report/toSalesmanStatement?active=1">接待客户及业绩统计</a></li>
			<li><a name="tab" href="${base}report/toVisitEveryDay?active=2">每日登门统计</a></li>
			<li><a name="tab" href="${base}report/toVisitAndOutOrder?active=3">登门出单统计</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#"> 
		  	<span aria-hidden="true" class="se7en-tables"></span>业绩统计<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<li><a name="tab" href="${base}report/toOrganiPerformance?active=4">机构业绩统计</a></li>
			<li><a name="tab" href="${base}report/toServiceStaffPerfor?active=5">客服业绩统计</a></li>
			<li><a name="tab" href="${base}report/toReceiveStaffPerfor?active=6">接待业绩统计</a></li>
			<li><a name="tab" href="${base}report/toIndividualRanking?active=7">个人业绩排名</a></li>
		  </ul>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#"> 
		  	<span aria-hidden="true" class="se7en-tables"></span>客户统计<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<li><a name="tab" href="${base}report/toCustomerStatistics?active=8">客户统计</a></li>
			<li><a name="tab" href="${base}report/toFiltrateCustomers?active=9">筛选客户类型统计</a></li>
		  </ul>
		</li>
		<li>
		  <a name="tab" href="${base}report/toClinchPerforEveryDay?active=10"> <span aria-hidden="true" class="se7en-tables"></span>每日成交业绩统计</a>
		</li>
		<li>
		  <a name="tab" href="${base}report/toButtPerforDetail?active=11" > <span aria-hidden="true" class="se7en-tables"></span>展厅客服对接业绩</a>
		</li>
		<li class="dropdown">
		  <a data-toggle="dropdown" href="#"> 
		  	<span aria-hidden="true" class="se7en-tables"></span>销售日报表<b class="caret"></b>
		  </a>
		  <ul class="dropdown-menu">
			<li><a name="tab" href="${base}report/toSellDaily?active=12">销售日报表</a></li>
			<li><a name="tab" href="${base}report/toCreditCardDepositDetail?active=13">当日刷卡定金明细表</a></li>
			<li><a name="tab" href="${base}report/toOutwarehouseDetail?active=14">出库明细统计</a></li>
			<li><a name="tab" href="${base}report/toOutwarehouseDetail?active=15" >赠品明细</a></li>
		  </ul>
		</li>
	  </ul>
	</div>
  </div>
</div>
<%-- 回显导航选中状态 --%>
<script type="text/javascript">
	showTab();
</script>
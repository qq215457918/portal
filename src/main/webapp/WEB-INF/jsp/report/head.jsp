<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar navbar-fixed-top scroll-hide">
  <div class="container-fluid top-bar">
    <div class="pull-right">
	  <ul class="nav navbar-nav pull-right">
		<li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
	         	你好： <shiro:principal/> <b class="caret"></b></a>
	        <ul class="dropdown-menu">
	          <li><a href="<%=request.getContextPath() %>/logout">
	            <i class="icon-signout"></i>退出系统</a>
	          </li>
	        </ul>
	      </li>
	  </ul>
	  <img width="40" height="40" src="resources/images/logo.png" style="margin:1px">
	</div>
	<button class="navbar-toggle">
	  <span class="icon-bar"></span>
	  <span class="icon-bar"></span>
	  <span class="icon-bar"></span>
	</button>
	<!-- 系统LOGO -->
	<!-- <a class="logo" href="index-2.html">se7en</a> -->
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
		  <a href="${base}"><span aria-hidden="true" class="icon-home"></span>主页</a>
		</li>
		<shiro:hasPermission name="salesmanStatement:view">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="icon-male"></span>接待统计<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
				<%-- <li><a name="tab" href="${base}report/toReceiveStatistics?active=0">接待客户统计</a></li> --%>
				<shiro:hasPermission name="salesmanStatement:view">
		        	<li><a name="tab" href="${base}report/toSalesmanStatement?active=1">接待客户及业绩统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="visitEveryDay:view">
		        	<li><a name="tab" href="${base}report/toVisitEveryDay?active=2">每日登门统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="visitAndOutOrder:view">
		        	<li><a name="tab" href="${base}report/toVisitAndOutOrder?active=3">登门出单统计</a></li>
	            </shiro:hasPermission>
			  </ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="organiPerformance:view">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="icon-bar-chart"></span>业绩统计<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
			  	<shiro:hasPermission name="organiPerformance:view">
		        	<li><a name="tab" href="${base}report/toOrganiPerformance?active=4">机构业绩统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="serviceStaffPerfor:view">
		        	<li><a name="tab" href="${base}report/toServiceStaffPerfor?active=5">客服业绩统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="receiveStaffPerfor:view">
		        	<li><a name="tab" href="${base}report/toReceiveStaffPerfor?active=6">接待业绩统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="individualRanking:view">
		        	<li><a name="tab" href="${base}report/toIndividualRanking?active=7">个人业绩排名</a></li>
	            </shiro:hasPermission>
			  </ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="customerStatistics:view">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="icon-user"></span>客户统计<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
			  	<shiro:hasPermission name="customerStatistics:view">
		        	<li><a name="tab" href="${base}report/toCustomerStatistics?active=8">客户统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="filtrateCustomers:view">
		        	<li><a name="tab" href="${base}report/toFiltrateCustomers?active=9">筛选客户类型统计</a></li>
	            </shiro:hasPermission>
			  </ul>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="clinchPerforEveryDay:view">
       		<li>
			  <a name="tab" href="${base}report/toClinchPerforEveryDay?active=10"> <span aria-hidden="true" class="icon-money"></span>每日成交业绩统计</a>
			</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="buttPerforDetail:view">
       		<li>
			  <a name="tab" href="${base}report/toButtPerforDetail?active=11" > <span aria-hidden="true" class="icon-exchange"></span>展厅客服对接业绩</a>
			</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="sellDaily:view">
			<li class="dropdown">
			  <a data-toggle="dropdown" href="#"> 
			  	<span aria-hidden="true" class="icon-list-alt"></span>销售日报表<b class="caret"></b>
			  </a>
			  <ul class="dropdown-menu">
			  	<shiro:hasPermission name="sellDaily:view">
		        	<li><a name="tab" href="${base}report/toSellDaily?active=12">销售日报表</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="creditCardDepositDetail:view">
		        	<li><a name="tab" href="${base}report/toCreditCardDepositDetail?active=13">当日刷卡定金明细表</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="outwarehouseDetail:view">
		        	<li><a name="tab" href="${base}report/toOutwarehouseDetail?active=14">出库明细统计</a></li>
	            </shiro:hasPermission>
				<shiro:hasPermission name="giftDetail:view">
		        	<li><a name="tab" href="${base}report/toGiftDetail?active=15" >赠品明细统计</a></li>
	            </shiro:hasPermission>
			  </ul>
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
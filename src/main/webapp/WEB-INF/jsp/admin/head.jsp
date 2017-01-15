<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar navbar-fixed-top scroll-hide">
  <div class="container-fluid top-bar">
    <div class="pull-right">
	  <ul class="nav navbar-nav pull-right">
		<%-- <li class="dropdown notifications hidden-xs">
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
		</li> --%>
		<li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
	         	你好： <shiro:principal/><b class="caret"></b></a>
	        <ul class="dropdown-menu">
	          <li><a href="<%=request.getContextPath() %>/logout">
	            <i class="icon-signout"></i>退出系统</a>
	          </li>
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
		  <a href="${basePath}"><span aria-hidden="true" class="icon-home"></span>主页</a>
		</li>
		<shiro:hasPermission name="resource:view">
	        <li>
			  <a name="tab" href="${basePath}admin/resource?active=1"> <span aria-hidden="true" class="icon-user"></span>权限菜单管理</a>
			</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="organization:view">
	        <li>
			  <a name="tab" href="${basePath}admin/organization?active=2"> <span aria-hidden="true" class="icon-user"></span>组织机构管理</a>
			</li>
        </shiro:hasPermission>
		<shiro:hasPermission name="role:view">
			<li>
			  <a name="tab" href="${basePath}admin/role?active=3"> <span aria-hidden="true" class="icon-user"></span>角色管理</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="employeeManage:view">
			<li>
			  <a name="tab" href="${basePath}admin/employeeManage/toEmployeeManage?active=4"> <span aria-hidden="true" class="icon-user"></span>员工管理</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="goodsMagene:view">
			<li>
			  <a name="tab" href="${basePath}admin/goodsManage/toGoodsMagene?active=5"> <span aria-hidden="true" class="icon-barcode"></span>商品管理</a>
			</li>
		</shiro:hasPermission>
		<shiro:hasPermission name="paymentAccount:view">
			<li>
			  <a name="tab" href="${basePath}admin/paymentAccount/toPaymentAccountList?active=6"> <span aria-hidden="true" class="icon-barcode"></span>收款账户管理</a>
			</li>
		</shiro:hasPermission>
		<%-- <li>
		  <a name="tab" href="${basePath}admin/customerManage/toCustomerManage?active=6"> <span aria-hidden="true" class="icon-barcode"></span>客户信息管理</a>
		</li> --%>
	  </ul>
	</div>
  </div>
</div>
<%-- 回显导航选中状态 --%>
<script type="text/javascript">
	showTab();
</script>
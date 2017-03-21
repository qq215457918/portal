<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="navbar navbar-fixed-top scroll-hide">
  <div class="container-fluid top-bar">
    <div class="pull-right">
	  <ul class="nav navbar-nav pull-right">
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
	<a href="<%=request.getContextPath()%>/"><img width="40" height="40" src="${basePath}resources/images/logo.png" style="margin:1px"></a>
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
			  <a name="tab" href="${basePath}admin/resource?active=1"> <span aria-hidden="true" class="icon-key"></span>权限菜单管理</a>
			</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="organization:view">
	        <li>
			  <a name="tab" href="${basePath}admin/organization?active=2"> <span aria-hidden="true" class="icon-sitemap"></span>组织机构管理</a>
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
			  <a name="tab" href="${basePath}admin/paymentAccount/toPaymentAccountList?active=6"> <span aria-hidden="true" class="icon-book"></span>收款账户管理</a>
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
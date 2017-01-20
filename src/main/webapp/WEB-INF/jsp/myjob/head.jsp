<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
      <!-- Navigation -->
      <div class="navbar navbar-fixed-top scroll-hide">
        <div class="container-fluid top-bar">
          <div class="pull-right">
            <ul class="nav navbar-nav pull-right">
              <li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
                 	你好：<shiro:principal/> <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=request.getContextPath() %>/">
                    <i class="icon-home"></i>返回工作台</a>
                  </li>
                  <li><a href="<%=request.getContextPath() %>/logout">
                    <i class="icon-signout"></i>退出系统</a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
          <img width="40" height="40" src="resources/images/logo.png" style="margin:1px">
          <button class="navbar-toggle">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="container-fluid main-nav clearfix">
  			<input type="hidden" id="active" name="active" value="${active}" />
          <!--menu start-->
          <div class="nav-collapse">
            <ul class="nav">
            <shiro:hasPermission name="job:reception">
	              <li><a name="tab" href="${base}job/reception?active=1">
	                	<span aria-hidden="true" class="icon-coffee"></span>接待查询</a>
	              </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="job:repurchaseq">
	              <li><a name="tab" href="${base}job/repurchaseq?active=2">
	                	<span aria-hidden="true" class="icon-mail-reply-all"></span>回购查询</a>
	              </li>
              </shiro:hasPermission> 
              <shiro:hasPermission name="job:repurchasec">
	              <li><a name="tab" href="${base}job/repurchasec?active=3">
	                	<span aria-hidden="true" class="icon-file"></span>回购审批</a>
	              </li>
              </shiro:hasPermission> 
              <shiro:hasPermission name="job:present">
	              <li><a name="tab" href="${base}job/present?active=4">
	                	<span aria-hidden="true" class="icon-file-text"></span>礼品审批</a>
	              </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="job:culture">
	              <li><a name="tab" href="${base}workflow/civilizationExchangeIndex">
	                	<span aria-hidden="true" class="icon-file-text"></span>文交所管理</a>
	              </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="job:finance">
	              <li><a name="tab" href="${base}workflow/financeOrderList">
	                	<span aria-hidden="true" class="icon-file-text"></span>财务审批</a>
	              </li>
              </shiro:hasPermission>
              <shiro:hasPermission name="job:warehouse">
	              <li><a name="tab" href="${base}workflow/storeOrderinfo">
	                	<span aria-hidden="true" class="icon-file-text"></span>库房审批</a>
	              </li>
              </shiro:hasPermission>
            </ul>
          </div>
          <!--menu end-->
        </div>
      </div>
      <!-- End Navigation -->
       <script>
			$(function(){
				base = $("base").attr('href');
				// 查询功能
				/* $("#quitConfirm").click(function(){
					var cId = $("#cId");
					window.location.href=base+"/visit/quit?cId="+cId;
				});
				showTab(); */
			});
	</script>
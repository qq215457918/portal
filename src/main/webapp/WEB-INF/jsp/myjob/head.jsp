<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <!-- Navigation -->
      <div class="navbar navbar-fixed-top scroll-hide">
        <div class="container-fluid top-bar">
          <div class="pull-right">
            <ul class="nav navbar-nav pull-right">
              <li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
                 	你好： <%=session.getAttribute("userName")%><b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=request.getContextPath() %>/login">
                    <i class="icon-home"></i>返回工作台</a>
                  </li>
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
        </div>
        <div class="container-fluid main-nav clearfix">
  			<input type="hidden" id="active" name="active" value="${active}" />
          <!--menu start-->
          <div class="nav-collapse">
            <ul class="nav">
              <li><a name="tab" href="${base}reception/job?active=1">
                	<span aria-hidden="true" class="icon-coffee"></span>接待查询</a>
              </li>
              <li><a name="tab" href="${base}order/init?active=2">
                	<span aria-hidden="true" class="icon-mail-reply-all"></span>回购管理</a>
              </li>
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
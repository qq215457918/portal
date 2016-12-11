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
  			<input type="hidden" id="cId" name="cId" value='<%=session.getAttribute("cId")%>' />
          <!--menu start-->
          <div class="nav-collapse">
            <ul class="nav">
              <li>
                <a name="tab" href="${base}visit/second?active=1&cId=<%=session.getAttribute("cId")%>"> <span aria-hidden="true" class="icon-user"></span>客户概览</a>
              </li>
              <li><a name="tab" href="${base}order/init?active=2&cId=<%=session.getAttribute("cId")%>">
                <span aria-hidden="true" class="icon-book"></span>购买商品</a>
              </li>
              <li><a name="tab" href="${base}deposit/init?active=3&cId=<%=session.getAttribute("cId")%>"><span aria-hidden="true" class="icon-money"></span>定金管理</a>
              </li>
              <li><a name="tab" href="${base}repurchase/init?active=4&cId=<%=session.getAttribute("cId")%>"><span aria-hidden="true" class="icon-retweet "></span>回购记录</a>
              </li>
              <li><a name="tab" href="${base}order/manage/init?active=5&cId=<%=session.getAttribute("cId")%>"><span aria-hidden="true" class="icon-file-text"></span>退换货管理</a>
              </li>
              <li><a name="tab" href="${base}present/list?active=6&cId=<%=session.getAttribute("cId")%>"><span aria-hidden="true" class="icon-gift"></span>领取赠品</a>
              </li>
              <li><a name="tab" href="${base}reception/init?active=7&cId=<%=session.getAttribute("cId")%>">
              		<span aria-hidden="true" class="icon-group"></span>来访查询</a>
              </li>
              <li>
                <a data-toggle="modal" href="#myModal"><span aria-hidden="true" class="icon-signin"></span>结束接待 </a>
              </li>
            </ul>
          </div>
          <!--menu end-->
        </div>
      </div>
      <!-- End Navigation -->
      <!--modal end-->
        <div class="modal fade" id="myModal">
           <div class="modal-dialog">
             <div class="modal-content">
               <div class="modal-header">
                 <button aria-hidden="true" class="close" data-dismiss="modal" type="button">×</button>
                 <h4 class="modal-title">
                                                             结束确认
                 </h4>
               </div>
               <div class="modal-body">
                 <h4>确认要结束本次接待？</h4>
               </div>
               <div class="modal-footer">
                 <button class="btn btn-primary" type="button" id="quitConfirm">确 认</button><button class="btn btn-default-outline" data-dismiss="modal" type="button">取 消</button>
               </div>
             </div>
           </div>
       </div>
         <!--modal end-->
       <script>
			$(function(){
				base = $("base").attr('href');
				// 查询功能
				$("#quitConfirm").click(function(){
					var cId = $("#cId");
					window.location.href=base+"/visit/quit?cId="+cId;
				});
				showTab();
			});
	</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <!-- Navigation -->
      <div class="navbar navbar-fixed-top scroll-hide">
        <div class="container-fluid top-bar">
          <div class="pull-right">
            <ul class="nav navbar-nav pull-right">
              <li class="dropdown messages hidden-xs">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span aria-hidden="true" class="se7en-envelope"></span>
                  <div class="sr-only">
                    Messages
                  </div>
                  <p class="counter">
                    3
                  </p>
                </a>
                <ul class="dropdown-menu messages">
                  <li><a href="#">Could we meet today? I wanted...</a>
                  </li>
                  <li><a href="#">Important data needs your analysis...</a>
                  </li>
                  <li><a href="#">Buy Se7en today, it's a great theme...</a>
                  </li>
                </ul>
              </li>
              <li class="dropdown user hidden-xs"><a data-toggle="dropdown" class="dropdown-toggle" href="#">
                 John Smith<b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">
                    <i class="icon-user"></i>My Account</a>
                  </li>
                  <li><a href="login1.html">
                    <i class="icon-signout"></i>Logout</a>
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
          <!--menu start-->
          <div class="nav-collapse">
            <ul class="nav">
              <li>
                <a class="current" href="index-2.html"><span aria-hidden="true" class="icon-user"></span>客户详情</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="icon-book"></span>购买商品</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="icon-money"></span>定金管理</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="se7en-pages"></span>回购记录</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="icon-retweet"></span>退换货管理</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="icon-gift"></span>领取赠品</a>
              </li>
              <li>
                <a data-toggle="modal" href="#myModal">
                <span aria-hidden="true" class="icon-signin"></span>结束接待
                </a>
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
					alert(1);
					var id = "1";
					window.location.href=base+"/visit/quit?id="+id;
				});
			});
	</script>
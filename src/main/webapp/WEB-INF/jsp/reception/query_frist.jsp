<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>
               首次登陆
    </title>
    <jsp:include page="head.jsp" /><!-- 接待head -->
  </head>
  <body>
    <div class="modal-shiftfix">
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
                <a class="current" href="index-2.html"><span aria-hidden="true" class="se7en-home"></span>客户详情</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="se7en-feed"></span>购买商品</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="icon-user"></span>定金管理</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="se7en-feed"></span>回购记录</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="se7en-feed"></span>退换货管理</a>
              </li>
              <li><a href="social.html">
                <span aria-hidden="true" class="se7en-gallery"></span>领取赠品</a>
              </li>
            </ul>
          </div>
          <!--menu end-->
        </div>
      </div>
      <!-- End Navigation -->

      <div class="row">
        <div class="col-md-12">
          <div class="widget-container fluid-height clearfix">
            <div class="col-md-6">
              <div class="heading">
                <i class="icon-tags"></i>用户详细信息
              </div>
              <div class="widget-content padded">
                <dl>
                  <dt>
                   用户姓名： 张三
                  </dt>
                  <dd>
                   电话：15009876541
                  </dd>
                </dl>

              </div>
            </div>
            <div class="col-md-6">
              <div class="heading"></div>
              <div class="widget-content padded">
                <dl>
                  <dd>
                    接待人员：张三
                  </dd>
                  <dd>
                    业务人员：李四
                  </dd>
                  最近登门时间：2015-07-27
                </dl>
                <dl>
                  <dd>
                    接待人员：张三
                  </dd>
                  <dd>
                    业务人员：李四
                  </dd>
                  最近登门时间：2015-07-27
                </dl>
              </div>
            </div>
            <div  class="col-md-2">
              <button class="btn btn-lg btn-primary">开始接待</button>
            </div>
            <div  class="col-md-3">
              <button class="btn btn-lg btn-default">退 出</button>
            </div>
          </div>

        </div>
      </div>
    </div>
  </body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
    <base href="${basePath}">
    <script type="text/javascript" src="resources/js/reception/inquiry_query.js"></script>
	<jsp:include page="head.jsp" />
  </head>
  <body class="login1 signup">
<!-- Login Screen -->
  <div class="login-wrapper">
    <div class="login-container" style="top:60%;height: 250px;">

      <form action="#" method="get" novalidate="novalidate">
        <div class="form-group"  style="margin-top:20px">
            <input class="form-control" placeholder="请输入客户的电话号码" type="text" id="phoneNo"><input type="submit" value="" >
        </div>     
       	<div class="social-login clearfix" style="margin:40px 0">
        	<a class="btn btn-primary pull-right twitter"id="queryId"><i class="icon-twitter"></i>查 询</a>
      	</div>
      </form>
    </div>
  </div>
<!-- End Login Screen -->
  </body>
</html>
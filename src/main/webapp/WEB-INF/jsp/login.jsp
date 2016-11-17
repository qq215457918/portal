<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
    <base href="${basePath}">
  </head>
  <body class="login1 signup">
<!-- Login Screen -->
  <div class="login-wrapper">
    <div class="login-container" style="top:60%;height: 250px;">

      <form action="/login" method="POST">
        <div class="form-group"  style="margin-top:20px">
            <input class="form-control" placeholder="登录名" type="text" id="loginName">
            <input class="form-control" placeholder="密码" type="text" id="loginPassword">
            <input type="submit" value="" >
        </div>     
       	<div class="social-login clearfix" style="margin:40px 0">
        	<a class="btn btn-primary pull-right twitter"id="queryId"><i class="icon-twitter"></i>登 陆</a>
      	</div>
      	<%--用于输入后台返回的验证错误信息 --%>
		<P>${message }</P>
      </form>
    </div>
  </div>
<!-- End Login Screen -->
  </body>
</html>
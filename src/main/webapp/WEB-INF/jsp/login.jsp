<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <div class="login-container" style="top:60%;height: 400px;">
	  <img width="150" height="150" src="resources/images/logo.png" style="margin:0px">

      <form action="login"  id="validate-form" method="post" novalidate="novalidate">
        <div class="form-group"  style="margin-top:20px">
            <input class="form-control" placeholder="登录名" type="text" id="loginName" name="loginName" value="<shiro:principal/>">
            <input class="form-control" placeholder="密码" type="password" id="password" name="password">
        </div>     
        <div style="color:#F00">${error}</div>
       	<div class="social-login clearfix" style="margin:40px 0">
        	<button class="btn btn-primary" type="submit">登录</button>
      	</div>
      </form>
    </div>
  </div>
<!-- End Login Screen -->
<script>

</script>
  </body>
</html>
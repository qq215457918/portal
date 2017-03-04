<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>修改密码</title>
    <base href="${basePath}">
  </head>
  <body class="login1 signup">
<!-- Login Screen -->
  <div class="login-wrapper">
    <div class="login-container" style="top:60%;height: 300px;">
    
    <form action="password/save"  id="validate-form" method="post" novalidate="novalidate">
        <div class="form-group"  style="margin-top:20px">
            <input class="form-control" placeholder="新密码" id="password" name="password" type="password">
            <input class="form-control" placeholder="确认新密码" id="confirm_password" name="confirm_password" type="password">
        </div>     
        <div style="color:#F00">${error}</div>
       	<div class="social-login clearfix" style="margin:40px 0">
       	    <a class="btn btn-default" href="<%=request.getContextPath() %>/">返&nbsp;&nbsp;&nbsp;&nbsp;回</a>
        	<button class="btn btn-primary" type="submit">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
      	</div>
      </form>
    
<!--       <form action="#" id="validate-form" method="get" novalidate="novalidate">
                <div class="form-group">
                  <label for="password">新密码</label><input class="form-control error" id="password" name="password" type="password"><label for="password" class="error">请输入新密码</label>
                </div>
                <div class="form-group">
                  <label for="confirm_password">确认新密码</label><input class="form-control error" id="confirm_password" name="confirm_password" type="password"><label for="confirm_password" class="error">请确认新密码</label>
                </div>
            <input class="btn btn-primary" type="submit" value="Validate form">
        </form> -->
    </div>
  </div>
<!-- End Login Screen -->
<script>
</script>
  </body>
</html>
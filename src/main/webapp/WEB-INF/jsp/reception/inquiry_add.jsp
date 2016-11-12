<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
  </head>
  <body>
  <div class="modal-shiftfix">
  <div class="container-fluid main-content"><div class="page-title">
 <div class="row">
  <div class="col-md-12">
    <div class="widget-container">
      <div class="heading">
        <i class="icon-shield"></i>新增用户
      </div>
      <div class="widget-content padded">
        <form action="<%=request.getContextPath() %>/visit/add" id="validate-form" method="get" novalidate="novalidate">
          <fieldset>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label for="firstname">姓名</label><input class="form-control" id="firstname" name="firstname" type="text">
                </div>
                <div class="form-group">
                  <label>QQ</label><input class="form-control" id="qqno" name="qqno" type="text">
                </div>
                <div class="form-group">
                  <label>生日</label>
                  <div class="input-group date datepicker" data-date-autoclose="true" data-date-format="yyyy-mm-dd">
                  <input class="form-control" type="text" id="birthday" name="birthday" ><span class="input-group-addon"><i class="icon-calendar"></i></span>
              </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label for="phone">电话号码</label><input class="form-control" id="phone" name="phone" data-inputmask="'mask': ['19999999999']" type="text">
                </div>
                <div class="form-group">
                  <label>地区</label><input class="form-control" id="area" name="area" type="text">
                </div>
                <div class="form-group">
                  <label>邮件</label><input class="form-control" id="email" type="text">
                </div>
              </div>
            </div>
            <input class="btn btn-primary" type="submit" value="提交新用户">
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</div>
  </div>
  </div>
  </div>
  </body>
</html>
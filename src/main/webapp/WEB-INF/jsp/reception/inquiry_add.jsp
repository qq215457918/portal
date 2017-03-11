<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
	<script type="text/javascript" src="resources/js/reception/cityselect.js"></script>
	<link rel="stylesheet" href="resources/css/reception/cityLayout.css" />	
		<style>
		.icon-star:before {
		    content: "\f005";
		    color: red;
		}
	</style>
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
        <form action="visit/add" id="validate-form" method="post" novalidate="novalidate">
          <fieldset>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                  <label for="firstname">姓名</label><i class="icon-star"></i><input class="form-control" id="name" name="name" type="text">
                </div>
                <div class="form-group">
                  <label>QQ</label><input class="form-control" id="qq" name="qq" type="text">
                </div>
                <div class="form-group">
                  <label>生日</label>
                  <div class="input-group date datepicker" data-date-autoclose="true" data-date-format="yyyy-mm-dd">
                  <input class="form-control" type="text" id="birthdayStr" name="birthdayStr" ><span class="input-group-addon"><i class="icon-calendar"></i></span>
              </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group">
                  <label for="phone">电话号码</label><i class="icon-star"></i><input class="form-control" id="phone" name="phone" value=${phone} data-inputmask="'mask': ['19999999999']" type="text">
                </div>
    	        <div class="form-group">
	              <label>地址</label>
	              <input id="area" name="area"  type="text" value="辽宁省-大连市-中山区" class="form-control city_input" readonly="readonly">
	            </div>  
                <div class="form-group">
                  <label>客服</label><!-- <input class="form-control" id="phone2" name="phone2" type="text"> -->
                  <select id="phoneStaffId" name="phoneStaffId" class="form-control" style="width: 100%;">
                       <option value="">请选择</option>	
                       <c:forEach items="${phoneEmp}" var="emp">
                       <option value="${emp.id}">${emp.name}</option>	
                       </c:forEach>
                   </select>
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
   	<script>
		$(function(){
			init_city_select($("#address"));
		});
	</script>
  </body>
</html>
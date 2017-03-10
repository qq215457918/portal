<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>修改用户信息</title>
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
        <i class="icon-shield"></i>修改用户基本信息
      </div>
      <div class="widget-content padded">
        <form action="customer/modify/basic/save" id="validate-form" method="post" novalidate="novalidate">
          <fieldset>
            <div class="row">
              <div class="col-md-6">
	                <div class="form-group">
	                  <label for="firstname">姓名  &nbsp;</label><i class="icon-star"></i>
	                  <input class="form-control" id="firstname" name="firstname" type="text" value=${customerInfo.name}>
	                </div>
	               <div class="form-group">
	                  <label for="phone">手机  &nbsp;</label><i class="icon-star"></i>
	                  <input class="form-control" id="phone" name="phone" type="text" data-inputmask="'mask': ['19999999999']" value=${customerInfo.phone} >
	                </div>
	               <div class="form-group">
	                  <label for="phone">电话号码2</label>
	                  <input class="form-control" id="phone2" name="phone2" type="text" data-inputmask="'mask': ['19999999999']" value=${customerInfo.phone2} >
	                </div>
         	        <div class="form-group">
	                  <label for="phone">商务电话</label>
	                  <input class="form-control" id="businessPhone" name="businessPhone" type="text" value=${customerInfo.businessPhone}>
	                </div>
	               <div class="form-group">
	                  <label>关联亲友</label>
	                  <input class="form-control" id="relationId" name="relationId" type="text" value=${customerInfo.relationId}>
	                </div>  
	               <div class="form-group">
	                  <label>地址</label>
	                  <input id="address" name="address"  type="text" value="${customerInfo.address}" class="form-control city_input" readonly="readonly" onchange="addressChange();">
	                </div>   
                </div>
               <div class="col-md-6">
	               <div class="form-group">
	                  <label >电宅</label>
	                  <input class="form-control" id="homePhone" name="homePhone" type="text" data-inputmask="'mask': ['9999-9999999']" value=${customerInfo.homePhone} >
	                </div>     
	               <div class="form-group">
	                  <label>性别</label>
	                  <select class="form-control" id="sex" name="sex" checked="${customerInfo.sex}"> 
		                  <option value="1">男</option>
		                  <option value="2">女</option>
	                  </select>
	                </div>
	                <div class="form-group">
	                  <label>身份证</label>
	                  <input class="form-control" id="idCard" name="idCard" type="text" value=${customerInfo.idCard}>
	                </div>                 
	                <div class="form-group">
	                  <label>生日</label>
	                  <div class="input-group date datepicker" data-date-autoclose="true" data-date-format="yyyy-mm-dd">
		                  <input class="form-control" type="text" id="birthday" name="birthday" value=${customerInfo.birthday} >
		                  <span class="input-group-addon"><i class="icon-calendar"></i></span>
	           		</div>
	           		<div class="form-group" style="margin-top:20px">
			            <label class="checkbox"><input type="checkbox" id="blacklistFlag" name="blacklistFlag"><span>加入黑名单</span></label>  		              
			        </div>     		
	           </div>
            </div>
            <input class="btn btn-primary" type="submit" style="margin-left:20px"value="提交新用户">
            </div>
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
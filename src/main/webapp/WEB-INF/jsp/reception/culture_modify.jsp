<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>修改文交所信息</title>
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
        <i class="icon-shield"></i>修改文交所信息
      </div>
      <div class="widget-content padded">
        <form action="<%=request.getContextPath() %>/customer/modify/exchange/save" id="validate-form" method="post" novalidate="novalidate">
          <fieldset>
            <div class="row">
              <div class="col-md-6">
	                <div class="form-group">
	                  <label for="firstname">文交所</label>
	                  <input class="form-control" id="cultureName" name="cultureName" type="text" value=${cultureInfo.cultureName}>
	                </div>
	                <div class="form-group">
	                  <label>开户日期</label>
	                  <input class="form-control" id="accountDate" name="accountDate" type="text" value=${cultureInfo.accountDate}>
	                </div>
	               <div class="form-group">
	                  <label>是否绑定银行卡</label>
	                  <input class="form-control" id="bankFlag" name="bankFlag" type="text" value=${cultureInfo.bankFlag}>
	                </div>
                </div>
               <div class="col-md-6">
               	  <div class="form-group">
	                  <label>客户姓名</label>
	                  <input class="form-control" id="customerName" name="customerName" type="text" value=${cultureInfo.customerName}>
	                </div>
	               <div class="form-group">
	                  <label>联系电话</label>
	                  <input class="form-control" id="phone" name="phone" type="text" value=${cultureInfo.phone}>
	                </div> 
	               <div class="form-group">
	                  <label>会员代码</label>
	                  <input class="form-control" id="code" name="code" type="text" value=${customerInfo.code}>
	                </div>  		
	           </div>
            </div>
            <input type="hidden" name="cid" id="cid" value="${cultureInfo.id}"/>
            <input class="btn btn-primary" type="submit" style="margin-left:20px"value="提交文交所信息">
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
  </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>收款账户管理</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/admin/payment_account_list.js"></script>
<jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
<style type="text/css">
	.ceng {
	    z-index: 2000;
	    background-color: rgba(0,0,0,0.5);
	    width: 100%;
	    height: 150%;
	    position: absolute;
	    top: -45px;
	    display: none;
	}
	.cengBox, .deleteCengBox {
	    width: 480px;
	    height: auto;
	    background-color: rgba(0,0,0,0.6);
	    z-index: 3001;
	    position: absolute;
	    left: 50%;
	    top: 117px;
	    margin-left: -240px;
	    border-radius: 5px;
	    display: none;
	}
	.boxText {
	    width: 99%;
	    margin: auto;
	    color: #fff;
	    line-height: 40px;
	    font-weight: bold;
	    text-indent: 10px;
	}
	.userRightCenterCeng {
	    background-color: #fff;
	    width: 468px;
	    margin: auto;
	    padding-bottom: 15px;
	}
	.gblBut {
        width: 32%;
	    float: right;
	    margin-top: 6px;
	}
	.form-group label {
		margin-left: 2%;
	}
	.cengBox input{
		width: 96%;
		margin-left: 2%;
	}
	.deleteCengBox input{
		width: 96%;
		margin-left: 2%;
	}
	.gblBut button{
	    margin: 0 10px 6px 0;
	}
</style>
</head>
<body>
  <div class="container-fluid main-content">
    <div class="row">
      <div class="col-lg-12">
        <div class="widget-container fluid-height clearfix">
         <%--  <div class="heading" style="color:#666666; font-weight: bold;">
            <i class="icon-reorder fl" style="margin-top: 0.8%; cursor: default;"></i>
            <select id="organizationId" class="form-control fl" style="width: 9%;">
                <option value="1" <c:if test="${organizationId == '1'}">selected="selected"</c:if> >大连</option>
                <option value="0" <c:if test="${organizationId == '0'}">selected="selected"</c:if> >沈阳</option>
            </select>
          </div> --%>
          <div class="widget-content padded">
            <div class="form-group condition-group">
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">收款账户</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="收款账户" id="paymentAccountName" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">银行名称</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="银行名称" id="bankName" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">账&nbsp;&nbsp;号</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="账号" id="accountNumber" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">是否可用</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="isUsable" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>
                            <option value="1">可用</option>
                            <option value="0">不可用</option>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
                <button class="btn btn-primary" id="add" style="float: left;">新&nbsp;增</button>
              </div>
            </div>
            <div style="clear: both;"></div>
            
            <table class="table table-bordered" id="payment">
                <thead>
                    <th>序&nbsp;&nbsp;号</th>
                    <th>收款账户</th>
                    <th>银行名称</th>
                    <th>账号</th>
                    <th>是否可用</th>
                    <th>操&nbsp;&nbsp;作</th>
                </thead>
                <tbody>
                </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="ceng" style="display: none;"></div>
  <div class="cengBox" style="display: none;">
    <div class="boxText">编辑收款账户信息</div>
      <form action="admin/paymentAccount/savePaymentAccount" id="form">
      <div class="userRightCenterCeng marginLeft">
          <input type="hidden" name="paymentAccountId" />
          <div class="form-group">
             <label style="margin-top: 10px;">收款账户</label><i class="icon-star" style="color: red;"></i>
             <input class="form-control" name="paymentAccountName" type="text" maxlength="20">
          </div>
          <div class="form-group">
             <label>银行名称</label><i class="icon-star" style="color: red;"></i>
             <input class="form-control" name="bankName" type="text" maxlength="20">
          </div>
          <div class="form-group">
              <label>帐&nbsp;&nbsp;号</label><i class="icon-star" style="color: red;"></i>
              <input class="form-control" name="accountNumber" type="text" maxlength="20">
          </div>
          <div class="form-group" style="margin-bottom: 25px;">
               <label>是否可用</label><i class="icon-star" style="color: red;"></i>
               <div class="col-md-7" style="width: 100%;">
                 <label class="radio-inline" style="width: 15%; margin-left: 10%;"><input name="isUsable" checked="checked" type="radio" value="1"><span>可用</span></label>
                 <label class="radio-inline" style="width: 20%;"><input name="isUsable" type="radio" value="0"><span>不可用</span></label>
               </div>
           </div>
      </div>
      <div class="glbNone gblBottom">
          <div class="gblBut" style="width: 70px;">
              <button class="btn btn-primary glbSave" style="margin-left: -100%;">保&nbsp;&nbsp;存</button>
          </div>
      </div>
      </form>
      <div class="glbNone gblBottom">
          <div class="gblBut" style="margin-right: -14%; width: 68px;">
              <button class="btn btn-default-outline gblOff" >关&nbsp;&nbsp;闭</button>
          </div>
      </div>
    </div>
    <!-- 删除弹层 -->
    <div class="deleteCengBox" style="display: none;">
      <div class="boxText">删除收款账户 :&nbsp;<span id="account"></span></div>
      <div class="userRightCenterCeng marginLeft">
          <input type="hidden" name="paymentId" />
          <div class="form-group">
             <label style="margin-top: 10px;">删除原因</label><i class="icon-star" style="color: red;"></i>
             <input class="form-control" name="deleteReason" type="text" maxlength="20">
          </div>
      </div>
      <div class="glbNone gblBottom">
          <div class="gblBut">
              <button class="btn btn-primary deleteSave" style="margin-left: 1%; float: left;">保&nbsp;&nbsp;存</button>
              <button class="btn btn-default-outline deleteOff" style="float: left;" >关&nbsp;&nbsp;闭</button>
          </div>
      </div>
    </div>
</body>
</html>
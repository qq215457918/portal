<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>结算页面</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
	<script src="resources/js/reception/shopping_cart.js" type="text/javascript"></script>
    <link rel="stylesheet" href="resources/css/reception/purchase_goods.css" />
  </head>
  <body>
  <div class="modal-shiftfix">
  <div class="container-fluid main-content"><div class="page-title">
 <div class="row">
  <div class="col-md-12">
    <div class="widget-container">
      <div class="heading">
        <i class="icon-shield"></i>结算页面
      </div>
      <div class="widget-content padded">
	       <div class="row">
	        <div class="col-md-12">
	          <div class="widget-container fluid-height clearfix">
	            <div class="widget-content padded">
	              <div  class="col-md-12">
	                <button class="btn btn-primary" onclick='checkAll();'>全 选</button>
	                <button class="btn btn-default" onclick='cancelAll();'>全取消</button>
	                <!-- Responsive Table -->
	                <div class="col-lg-12">
	                  <div class="widget-container fluid-height clearfix">
	                    <div class="widget-content padded clearfix">
	                      <div class="table-responsive">
	                        <table class="table">
	                          <thead>
	                          <th>
	                            	选择
	                          </th>
	                          <th>
	                            	商品名称
	                          </th>
	                          <th>
	                          		单价(元)
	                          </th>
	                          <th>
	                            	数量(个)
	                          </th>
	                          <th>
	                            	小计
	                          </th>
	                          </thead>
	                          <tbody id ="cart-good-list">
	                          </tbody>
	                        </table>
	                        <input value='${goodInfo}' id="goodInfo" type="hidden"></input>
	                      </div>
	                    </div>
	                  </div>
	                  <div class="col-lg-6">
	                    <div class="widget-container fluid-height">
	                      <!-- Table -->
	                      <table class="table table-filters">
	                        <tbody>
	                        <tr>
	                          <td class="filter-category red">
	                            <div class="arrow-left"></div>
	                            <label class="radio-inline">
	                              <input id="option-amount" name="optionsRadios" type="radio"  checked="true"><span></span>
	                            </label>
	                          </td>
		                          <td>
		                            	全额付款
		                          </td>
	                          <td>
	                            <div class="danger">
         							总计：<em id="total-amount"></em>  元
	                            </div>
	                          </td>
	                        </tr>
	                        <tr>
	                          <td class="filter-category orange">
	                          	<div class="arrow-left"></div>
	                            <label class="radio-inline" >
	                            	<input id="option-deposit" name="optionsRadios" type="radio"><span></span>
	                            </label>
	                          </td>
		                          <td>
		                            	定金支付
		                          </td>
	                          <td>
	                            <input class="form-control" placeholder="定金金额" type="number" id="deposit-amount">
	                          </td>
	                        </tr>
	                        </tbody>
	                      </table>
	                    </div>
	                  </div>
	                </div>
	                <div class="row">
	                      <button class="btn btn-danger" onclick='submitOrder();'>确认支付</button>
	                </div>
	                <!-- end Responsive Table -->
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
      </div>
    </div>
  </div>
</div>
  </div>
  </div>
  </div>
  </body>
</html>
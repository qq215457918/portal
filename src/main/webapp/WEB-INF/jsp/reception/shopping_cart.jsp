<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>结算页面</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
  </head>
  <body>
  <div class="modal-shiftfix">
  <div class="container-fluid main-content"><div class="page-title">
 <div class="row" style="margin-top:116px">
  <div class="col-md-12">
    <div class="widget-container">
      <div class="heading">
        <i class="icon-shield"></i>结算页面
      </div>
      <div class="widget-content padded">
              <div class="row">
        <div class="col-md-12">
          <div class="widget-container fluid-height clearfix">
              <div class="heading">
                <i class="icon-tags"></i>购物车
              </div>
            <div class="widget-content padded">
              <div  class="col-md-12">
                <button class="btn btn-primary">全 选</button>
                <button class="btn btn-default">全取消</button>

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
                           单价
                          </th>
                          <th>
                            数量
                          </th>
                          <th>
                            小计
                          </th>
                          </thead>
                          <tbody>
                          </tbody>
                        </table>
                        <input value="${goodInfo}" id="goodInfo" type="hidden"></input>
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
                            <label class="radio" for="option1">
                              <input id="option1" name="optionsRadios1" value="option1" type="radio">
                              <span></span>
                            </label>
                          </td>
                          <td>
                            全额付款
                          </td>
                          <td>
                            <div class="danger">
                              ￥800
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td class="filter-category orange">
                            <div class="arrow-left"></div>
                            <label class="radio" for="option1">
                              <input id="option1" name="optionsRadios1" value="option1" type="radio">
                              <span></span>
                            </label>
                          </td>
                          <td>
                            定金支付
                          </td>
                          <td>
                            <input class="form-control" placeholder="定金金额" type="text">
                          </td>
                        </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <div class="row">
                  <div class="col-md-11">
                  </div>
                  <div class="col-md-1">
                      <button class="btn btn-danger">确认支付</button>
                  </div>
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
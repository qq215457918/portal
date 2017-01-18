<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>订单管理页面</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
	<script type="text/javascript" src="resources/js/reception/order_manage.js"></script>
  </head>
  <body>
  <div class="modal-shiftfix">
  <div class="container-fluid main-content"><div class="page-title">
 <div class="row">
  <div class="col-md-12">
    <div class="widget-container">
      <div class="widget-content padded">
      <div class="row">
        <div class="col-lg-12">
          <div class="widget-container fluid-height clearfix">
            <div class="heading">
              <i class="icon-table"></i>订单管理
            </div>
            <div class="widget-content padded">
              <form action="#" class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-6">
                    <label class="control-label col-md-4">商品信息</label>
                    <div class="col-md-8">
                      <input class="form-control" placeholder="请输入商品名称" type="text" id="goodName">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <label class="control-label col-md-4">接待人员</label>
                    <div class="col-md-8">
                      <input class="form-control" placeholder="请输入接待人员名称" type="text" id="staffName">
                    </div>
                  </div>
                </div>

                <div class="form-group col-md-6">
                  <label class="control-label col-md-4">商品状态</label>
                  <div class="col-md-8">
                    <label class="checkbox-inline">
                      <input type="checkbox" name="checkbox" checked="checked" value="1"><span>正常</span>
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="checkbox" checked="checked" value="2"><span>已退货</span>
                    </label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="checkbox" checked="checked" value="3"><span>已换货</span>
                    </label>
                  </div>
                </div>

              </form>
              <div class="col-md-12" style="left:80%">
                <button class="btn btn-success">查 询</button>
              </div>
            </div>
            <div class="widget-content padded clearfix">
              <table  class="table table-bordered" id ="orderTable">
                <thead>
                <tr>
                    <th>订单编码</th>
					<th>商品名称</th>
					<th>商品数量</th>
					<th>交易金额</th>
					<th>交易日期</th>
					<th>接待员</th>
					<th>订单状态</th>
					<th>是否删除</th>
					<th>操作</th>
                </tr>
                </thead>
                <tbody> 
                </tbody>
              </table>
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
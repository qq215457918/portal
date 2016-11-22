<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>接待管理</title>
    <base href="${basePath}">
    <script type="text/javascript" src="resources/js/reception/order_deposit.js"></script>
	<jsp:include page="head.jsp" />
  </head>
  <body>
  <div class="modal-shiftfix">
  <div class="container-fluid main-content"><div class="page-title">
 <div class="row">
  <div class="col-md-12">
    <div class="widget-container">
      <div class="heading">
        <i class="icon-shield"></i>定金管理
      </div>
      <div class="widget-content padded">
      <div class="row">
        <div class="col-lg-12">
          <div class="widget-container fluid-height clearfix">
            <div class="heading">
              <i class="icon-table"></i>购买历史
            </div>
            <div class="widget-content padded">
              <form action="#" class="form-horizontal">
                <div class="form-group">
                  <div class="col-md-6">
                    <label class="control-label col-md-4">商品编码</label>
                    <div class="col-md-8">
                      <input class="form-control" placeholder="商品编码" type="text" id="goodCode">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <label class="control-label col-md-4">商品名称</label>
                    <div class="col-md-8">
                      <input class="form-control" placeholder="商品名称" type="text" id="goodName">
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-md-6">
                    <label class="control-label col-md-4">售卖金额</label>
                    <div class="col-sm-4">
                      <input class="form-control" type="text" id="lprice">
                    </div>
                    <div class="col-sm-4">
                      <input class="form-control" type="text" id="hprice">
                    </div>
                  </div>
                </div>
              </form>
              <div class="col-md-12" style="left:80%">
                <button class="btn btn-success">查 询</button>
              </div>
            </div>
            <div class="widget-content padded clearfix">
              <table class="table table-bordered table-bordered" id ="depositTable">
                <thead>
                <tr>
                  <th>接待人员</th>
                  <th>购买商品</th>
                  <th>定金金额</th>
                  <th>定金日期</th>
                  <th>剩余尾款</th>
                  <th>操作</th>
                </tr></thead>
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
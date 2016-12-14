<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>回购审批页面</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
	<script type="text/javascript" src="resources/js/myjob/repurchase_check.js"></script>
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
              <i class="icon-table"></i>回购审批页面
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
                    <label class="control-label col-md-4">客户姓名</label>
                    <div class="col-md-8">
                      <input class="form-control" placeholder="请输入客户姓名" type="text" id="customerName">
                    </div>
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
                    <th>成单号</th>
                    <th>客户姓名</th>
                    <th>客户电话</th>
					<th>申请商品</th>
					<th>申请数量</th>
					<th>申请金额</th>
					<th>交易日期</th>
					<th>申请接待员</th>
					<th>申请原因</th>
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
<!-- 特殊审批  modal Start -->
  <div class="modal fade" id="specialModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
          <h4 class="modal-title">
          	  请输入申请回购原因
          </h4>
        </div>            
        <div class="modal-body">
                         商品名称： <input class="form-control" value="" type="text" id="applyGoodsName" readonly ="readonly">
                         赠品数量： <input class="form-control" value="" type="text" id="applyCount" readonly ="readonly">
                        回购金额：<div class="input-group">
                <span class="input-group-addon">¥</span><input class="form-control" type="text" id="applyPrice"><span class="input-group-addon">.00</span>
               </div>
                         申请原因： <textarea class="form-control" rows="3" id="applyReason"></textarea>
        </div>
        <input id="applyGoodsId" name="applyGoodsId" type="hidden" />
        <input id="customerId" name="customerId" type="hidden" />
        <div class="modal-footer">
          <button class="btn btn-primary" type="button" id="appConfirm">确 认</button>
          <button class="btn btn-default-outline" data-dismiss="modal" type="button">取 消</button>
        </div>
      </div>
    </div>
  </div>
<!-- modal end -->

  </div>
  </div>
  </div>
  </body>
</html>
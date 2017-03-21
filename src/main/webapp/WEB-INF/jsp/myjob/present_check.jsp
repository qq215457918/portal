<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>礼品审批页面</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
	<script type="text/javascript" src="resources/js/myjob/present_check.js"></script>
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
              <i class="icon-table"></i>礼品审批页面
            </div>
            <div class="widget-content padded">
              <form action="#" class="form-horizontal">
                <div class="form-group">
                
                  <div class="col-md-6">
                    <label class="control-label col-md-4">礼品信息</label>
                    <div class="col-md-8">
                      <input class="form-control" placeholder="请输入礼品名称" type="text" id="goodName">
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
              <table  class="table table-striped" id ="orderTable">
                <thead>
                <tr>
                    <th>礼品单号</th>
					<th>客户姓名</th>
					<th>申请礼品</th>
					<th>申请数量</th>
					<th>申请日期</th>
					<th>接待员</th>
					<th>申请原因</th>
					<th>审批状态</th>
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
  <div class="modal fade" id="checkModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
          <h4 class="modal-title">
          	  确认赠品审批
          </h4>
        </div>            
        <div class="modal-body">
       	  接待员姓名： <input class="form-control" value="" type="text" id="receiverStaffName" readonly ="readonly">
<!--       	 客户姓名： <input class="form-control" value="" type="text" id="customerName" readonly ="readonly">	 -->  
                          礼品名称： <input class="form-control" value="" type="text" id="applyGoodsName" readonly ="readonly">
                         审批数量： <input class="form-control" value="" type="text" id="applyCount" readonly ="readonly">
        <input id="orderNum" name="orderNum" type="hidden" />
        <div class="modal-footer">
          <button class="btn btn-primary" type="button" id="appConfirm">确 认 审 批</button>
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
    <base href="${basePath}">
    <script src="resources/js/reception/purchase_goods.js" type="text/javascript"></script>
    <link rel="stylesheet" href="resources/css/reception/purchase_goods.css" />
	<jsp:include page="head.jsp" />
  </head>
  <body>
  <div class="modal-shiftfix">
  <div class="container-fluid main-content">
  <div class="page-title">

      <div class="row" style="margin-top:122px;">
        <div class="col-lg-8">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>购买商品
          </div>
          <div class="widget-content padded">
            <form action="#" class="form-horizontal">
              <div class="form-group">
                <div class="col-md-6">
                    <label class="control-label col-md-4">商品信息</label>
                  <div class="col-md-8">
                    <input class="form-control" placeholder="商品名称或者编码" type="text" id="goodInfo">
                  </div>
                </div>
                <div class="col-md-6">
                    <label class="control-label col-md-4">售卖金额</label>
                    <div class="col-md-8">
	                    <div class="col-sm-6">
	                      <input class="form-control"  type="text" placeholder="最低金额"  id="lowPrice">
	                    </div>
	                    <div class="col-sm-6">
	                      <input class="form-control"  type="text" placeholder="最高金额" id="highPrice">
	                    </div>
                     </div>
                  </div>
                </div>
            </form>
            <div  class="col-md-12" style="left:90%">
                <button class="btn btn-primary" id="searchGoods">查 询</button>
            </div>
          </div>
          <div class="widget-container fluid-height clearfix">
            <div class="widget-content padded clearfix">
  			<!-- DataTables Example -->
			<table class="table table-bordered" id="goodsInfo">
				<thead>
				  <th>序号</th>
				  <th>商品编码</th>
				  <th>商品名称</th>
                  <th>售卖金额</th>
                  <th>库存数量</th>
                  <th>是否可托管</th>
                  <th>操作</th>
				</thead>
				<tbody>
				</tbody>
			</table>
			<!-- end DataTables Example -->

            </div>
            <!--add myModal statr-->
            <div class="widget-content padded">
              <a class="btn btn-primary btn" onclick='openDelivery();'>配 送</a>
              <a class="btn btn-warning btn" onclick='openPlacing();'>配 售</a>
              <a class="btn btn-default btn" onclick='openGifts();'>赠 品</a>
            </div>
            <div class="modal fade" id="myGoods">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
                    <h4 class="modal-title" id="modal-title">
                    </h4>
                  </div>
                  <div class="modal-body">
	             <table class="table table-bordered">
	              <thead>
	              <tr>
	                <th>
	                	 选择框
	                </th>
	                <th>
	                	 商品编码
	                </th>
	                <th>
	       				商品名称
	                </th>
	                <th>
	       				售价
	                </th>
	                <th>
	       				操作
	                </th>
	              </tr></thead>
	              <tbody id="modal-data">
<%--               <c:forEach var="status" items="${goods}" varStatus="var"> 
	              <tr>
	              	<td>
	              	    ${var.index+1}
	              	</td>
	                <td>
	                    <c:forEach var="detail" items="${status.orderDetailInfoList}">
	                    <b>名称 : </b>${detail.goodName } &nbsp;&nbsp;
	                    <b>数量 : </b>${detail.amount } 件  &nbsp;&nbsp;
	                    <b>价格 : </b> ${detail.price } <i class="icon-yen"></i>
	                    
	                    <br/>
	                    </c:forEach>
	                </td>
	                <td>
	                  ${status.createDateString }
	                </td>
	                <td>
	                   ${status.payPrice }
	                </td>
	              </tr>
              </c:forEach> --%>
              </tbody>
            </table>
                  </div>
                  <div class="modal-footer">
                    <button class="btn btn-primary" type="button" onclick='addOtherGoods();'>添加到购物车</button><button class="btn btn-default-outline" data-dismiss="modal" type="button">关闭</button>
                  </div>
                </div>
              </div>
            </div>
            <!--add myModal end-->
          </div>
          </div>
        </div>
        <div class="col-lg-4">
        <div class="widget-container">
          <div class="heading">
            <i class="icon-bar-chart"></i>购物车
          </div>
          <div class="widget-content padded">
            <table class="table">
              <thead>
              <tr><th>
                                                购买清单
              </th>
              </tr></thead>
              <tbody id="shoppingList">
              </tbody>
            </table>
          <div class="row">
         		<div style="float: right;margin-right: 40px;font-size: 1.2em;color: red;">
         			总计：<em id="total-amount"></em> 元
         		</div>
				<button class="btn btn-warning" onclick='gotoAccount();'>去结算</button>
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
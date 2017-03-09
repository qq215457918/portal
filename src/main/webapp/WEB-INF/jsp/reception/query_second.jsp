<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>客户接待</title>
    <base href="${basePath}">
	<jsp:include page="head.jsp" />
</head>
<body>
    <div class="modal-shiftfix">
      <div class="row">
        <div class="col-md-12">
          <div class="widget-container fluid-height clearfix">
            <div class="col-md-6">
              <div class="heading">
                <i class="icon-tags"></i>用户详细信息
              </div>
              <div class="widget-content padded">
                <dl>
                  <dd>
                  	<strong> 客户类型： </strong>${info.type }
                  </dd>
                  <dd>
                  	 <strong>用户姓名：</strong> ${info.name } 
                  </dd>
                  <dd>
                  	<strong> 电话：</strong>${info.encryptPhone }
                  </dd>
                </dl>
                <button class="btn btn-primary" id="modifyInfo">修改基本信息</button>
                <button class="btn btn-warning" id="modifyExchange">修改文交所信息</button>
              </div>
            </div>
            <div class="col-md-6">
              <div class="heading"></div>
              <div class="widget-content padded">
                 <dl>
                  <dd>
                   <strong>	客服人员：</strong>${info.phoneStaffName }
                  </dd>
                  <dd>
                  	<strong>接待人员：</strong>${info.receiverStaffName }
                  </dd>
                  <dd>
                  	<strong>最近登门时间：</strong>${info.recentVisitDate }
                  </dd>
                  <dd>
                    <strong>是否黑名单：</strong>${info.blacklistFlag }
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      
      <div class="row">
        <div class="col-md-12">
          <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>历史信息
          </div>
            <div class="widget-content padded clearfix">
        	<table class="table table-bordered">
              <thead>
              <tr>
              	<th>
                	 重复登门次数
                </th>
                <th>
                	 登门历史
                </th>
                <th>
                	 历史接待人员
                </th>
                <th>
       				购买商品
                </th>
                <th>
       				领取赠品
                </th>
                <th>
                	购买金额总计
                </th>
                <th>
                	拨打记录
                </th>
              </tr></thead>
              <tbody>
	              <tr>
	              <td>
					  ${info.visitCount}
	                </td>
	                <td>
					  ${info.receiverStaffDate}
	                </td>
	                <td>
	                   ${info.hisReceiverStaffName}
	                </td> 
	                <td>
	                  ${info.product }
	                </td>
	                <td>
	                   ${info.gift }
	                </td>
	                <td>
	                   ${info.transactionAmount }
	                </td>
	                <td>
	                   ${info.callDates}
	                </td>
	              </tr>
              </tbody>
            </table>
            </div>
          </div>
        </div>
      </div>

      <!-- 购买历史 -->
      <div class="row">
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <a name="tab" href="${base}order/manage/init?active=5&cId=<%=session.getAttribute("cId")%>"><i class="icon-table"></i>购买历史</a>
          </div>
          <div class="widget-content padded clearfix">
            <table class="table table-bordered">
              <thead>
              <tr>
                <th>
                	 序号
                </th>
                <th>
                	 商品信息-数量-金额
                </th>
                <th>
       				交易时间
                </th>
                <th>
                	成单总金额
                </th>
               <th>
                	接待人员
                </th>
              </tr></thead>
              <tbody>
              <c:forEach var="status" items="${goods}" varStatus="var"> 
	              <tr>
	              	<td>
	              	    ${var.index+1}
	              	</td>
	                <td>
	                    <c:forEach var="detail" items="${status.orderDetailInfoList}">
	                    ${detail.goodName } &nbsp;&nbsp;—
	                    ${detail.amount } <b>件</b>  &nbsp;&nbsp;—
	                    ${detail.price } <i class="icon-yen"></i>
	                    
	                    <br/>
	                    </c:forEach>
	                </td>
	                <td>
	                  ${status.createDateString }
	                </td>
	                <td>
	                   ${status.payPrice }
	                </td>
	                <td>
	                   ${status.receiverStaffName }
	                </td>
	              </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      
            <!-- 来访记录 -->
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
          <a name="tab" href="${base}reception/init?active=7&cId=<%=session.getAttribute("cId")%>"><i class="icon-table"></i>来访记录</a>
          </div>
          <div class="widget-content padded clearfix">
                        <table class="table table-bordered">
              <thead>
              <tr>
                <th>
                	 序号
                </th>
                <th>
                	接待人员  
                </th>
                <th>
       				客服人员
                </th>
                <th>
                	来访时间
                </th>
                <th>
                	领取赠品
                </th>
              </tr></thead>
              <tbody>
              <c:forEach var="status" items="${receptionInfo}" varStatus="var"> 
	              <tr>
	              	<td>
	              	    ${var.index+1}
	              	</td>
	                <td>
					   ${status.receiverStaffName }
	                </td>
	                <td>
	                   ${status.phoneStaffName }
	                </td>
	                <td>
	                    ${status.createDate }
	                </td>
	                <td>
	                    ${status.presentName }
	                </td>
	              </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
     </div>
      <div class="row">
      <!-- 回购商品 -->
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <a name="tab" href="${base}repurchase/init?active=4&cId=<%=session.getAttribute("cId")%>"><i class="icon-table"></i>回购商品</a>
          </div>
          <div class="widget-content padded clearfix">
             <table class="table table-bordered">
              <thead>
              <tr>
                <th>
                	 序号
                </th>
                <th>
                	 商品信息-数量-金额
                </th>
                <th>
       				交易时间
                </th>
                <th>
                	订单总金额
                </th>
              </tr></thead>
              <tbody>
              <c:forEach var="status" items="${revokeDeposit}" varStatus="var"> 
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
              </c:forEach>
              </tbody>
            </table>
            <input type="hidden" name="cid" id="cid" value='<%=session.getAttribute("cId")%>'/>
          </div>
        </div>
      </div>
        <!-- 撤单商品 -->
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
           <a name="tab" href="${base}order/manage/init?active=5&cId=<%=session.getAttribute("cId")%>"> <i class="icon-table"></i>撤单商品</a>
          </div>
          <div class="widget-content padded clearfix">
            <table class="table table-bordered">
              <thead>
              <tr>
                <th>
                	 序号
                </th>
                <th>
                	 商品信息-数量-金额
                </th>
                <th>
       				交易时间
                </th>
                <th>
                	订单总金额
                </th>
              </tr></thead>
              <tbody>
              <c:forEach var="status" items="${returnGoods}" varStatus="var"> 
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
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </div>
  </body>
    <script>
	$(function(){
		base = $("base").attr('href');
		// 修改基本信息
		$("#modifyInfo").click(function(){
			var id = $('#cid').val();
			window.location.href = base+"/customer/modify/basic?cId="+id;
		});
		$("#modifyExchange").click(function(){
			var id = $('#cid').val();
			window.location.href = base+"/customer/modify/exchange?cId="+id;
		});
	});
	</script>
</html>
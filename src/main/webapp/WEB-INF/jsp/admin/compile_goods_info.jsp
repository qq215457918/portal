<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>编辑商品信息</title>
<base href="${basePath}">
<script type="text/javascript" src="resources/js/admin/compile_goods_info.js"></script>
<jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
<style>
    .icon-star:before {
        content: "\f005";
        color: red;
    }
</style>
</head>
<body>
<div class="modal-shiftfix">
    <div class="container-fluid main-content">
        <div class="page-title">
            <div class="row">
              <div class="col-md-12">
                <div class="widget-container">
                  <div class="widget-content padded">
                     <div class="row">
	                    <form action="admin/goodsManage/saveGoodsInfo" id="form" method="post">
		                   	<input type="hidden" id="id" name="id" value="${goodsInfo.id}" />
		                   	<input type="hidden" id="viewCreateDate" name="viewCreateDate" value="${goodsInfo.createDate}" />
		                   	<input type="hidden" id="viewUpdateDate" name="viewUpdateDate" value="${goodsInfo.updateDate}" />
		                   	<input type="hidden" id="deleteFlag" name="createUserid" value="${goodsInfo.createUserid}" />
		                   	<input type="hidden" id="createDate" name="updateUserid" value="${goodsInfo.updateUserid}" />
		                   	<input type="hidden" id="deleteFlag" name="deleteFlag" value="${goodsInfo.deleteFlag}" />
                             <div class="col-md-6">
                                 <div class="form-group">
                                    <label>商品名称  &nbsp;</label><i class="icon-star"></i>
                                    <input class="form-control" id="name" name="name" type="text" value="${goodsInfo.name }">
                                 </div>
                                 <div class="form-group">
                                   <label>商品种类（大）  &nbsp;</label><i class="icon-star"></i>
                                   <select id="bigSortId" class="form-control" style="width: 100%;">
			                            <option value="">请选择</option>	
			                            <c:forEach items="${goodsBigSortList}" var="goods">
											<option value="${goods.id}" <c:if test="${goods.id eq goodsInfo.bigSortId }">selected="selected"</c:if>>${goods.name }</option>	
			                            </c:forEach>
			                       </select>
                                 </div>
                                 <div class="form-group">
                                    <label>商品分类  &nbsp;</label><i class="icon-star"></i>
			                        <select id="type" name="type" class="form-control" style="width: 100%;">
			                            <option value="">请选择</option>	
			                            <option value="0" <c:if test="${0 eq goodsInfo.type }">selected="selected"</c:if>>常规商品</option>
			                            <option value="1" <c:if test="${1 eq goodsInfo.type }">selected="selected"</c:if>>礼品</option>
			                            <option value="2" <c:if test="${2 eq goodsInfo.type }">selected="selected"</c:if>>配售</option>
			                            <option value="3" <c:if test="${3 eq goodsInfo.type }">selected="selected"</c:if>>配送</option>
			                            <option value="4" <c:if test="${4 eq goodsInfo.type }">selected="selected"</c:if>>兑换</option>	
			                        </select>
                                 </div>
                                 <div class="form-group">
                                    <label>金额  &nbsp;</label>
                                    <input class="form-control" id="price" name="price" type="text" value="${goodsInfo.price }">
                                 </div>
                                 <div class="form-group" style="height: 59px;">
                                     <label>回购标志  &nbsp;</label><i class="icon-star"></i>
                                     <div class="col-md-7" style="width: 100%;">
                                       <label class="radio-inline" style="width: 15%; margin-left: 10%;"><input name="repurchaseFlag" checked="checked" type="radio" value="0"><span>否</span></label>
                                       <label class="radio-inline" style="width: 15%;"><input name="repurchaseFlag" type="radio" <c:if test="${'1' eq goodsInfo.repurchaseFlag }">checked="checked"</c:if> value="1"><span>是</span></label>
                                     </div>
                                 </div>
                                 <div class="form-group" style="height: 59px;">
                                    <label>回购开始时间  &nbsp;</label>
                                    <div class="condition-control fl">
						          	  <div class="col-md-3" style="width: 100%; padding: 0;">
									    <div class="input-group date datepicker">
							              <input class="form-control" type="text" id="viewRepurchaseStarttime" name="viewRepurchaseStarttime" value="${goodsInfo.viewRepurchaseStarttime }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
							            </div>
							          </div>
						          	</div>
                                 </div>
                                 <div class="form-group" style="height: 59px;">
                                     <label>是否可托管 &nbsp;</label><i class="icon-star"></i>
                                     <div class="col-md-7" style="width: 100%;">
                                       <label class="radio-inline" style="width: 15%; margin-left: 10%;"><input name="trusteeshipFlag" checked="checked" type="radio" value="0"><span>否</span></label>
                                       <label class="radio-inline" style="width: 15%;"><input name="trusteeshipFlag" type="radio" <c:if test="${'1' eq goodsInfo.trusteeshipFlag }">checked="checked"</c:if> value="1"><span>是</span></label>
                                     </div>
                                 </div>
                             </div>
                             
                             <div class="col-md-6">
                             	<div class="form-group">
                                    <label>商品序号  &nbsp;</label><i class="icon-star"></i>
                                    <input class="form-control" id="code" name="code" type="text" value="${goodsInfo.code}">
                                 </div>
                                 <div class="form-group">
                                    <label>商品种类（小）  &nbsp;</label><i class="icon-star"></i>
                                    <select id="sortId" name="sortId" class="form-control" style="width: 100%;">
			                            <option value="">请选择</option>	
			                            <c:forEach items="${sortList}" var="goodsSort">
											<option value="${goodsSort.id}" <c:if test="${goodsSort.id eq goodsInfo.sortId }">selected="selected"</c:if>>${goodsSort.name}</option>	
			                            </c:forEach>
			                        </select>
                                 </div>
                                 <div class="form-group">
                                    <label>单位  &nbsp;</label>
                                    <input class="form-control" id="unit" name="unit" type="text" value="${goodsInfo.unit }">
                                 </div>
                                 <div class="form-group">
                                    <label>数量  &nbsp;</label>
                                    <input class="form-control" id="amount" name="amount" type="text" value="${goodsInfo.amount }">
                                 </div>
                                 <div class="form-group">
                                    <label>回购信息  &nbsp;</label>
                                    <input class="form-control" id="repurchaseInfo" name="repurchaseInfo" type="text" value="${goodsInfo.repurchaseInfo}">
                                 </div>
                                 <div class="form-group">
                                    <label>回购结束时间  &nbsp;</label>
                                    <div class="condition-control fl">
						          	  <div class="col-md-3" style="width: 100%; padding: 0;">
									    <div class="input-group date datepicker">
							              <input class="form-control" type="text" id="viewRepurchaseEndtime" name="viewRepurchaseEndtime" value="${goodsInfo.viewRepurchaseEndtime }" readonly="readonly"><span class="input-group-addon"><i class="icon-calendar"></i></span>
							            </div>
							          </div>
						          	</div>
                                 </div>
                                 <div class="form-group" style="margin-bottom: 0px; height: 106px;">
                                    <button class="btn btn-default-outline" id="back" style="float:right; margin-top: 7%;">返回</button>
		                  	  		<button class="btn btn-primary" id="saveGoods" style="float:right; margin-top: 7%;">保存</button>
                                 </div>
                             </div>
	                      </form>
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
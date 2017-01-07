<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
<title>藏品信息管理</title>
<base href="${basePath}">
<link type="text/css" rel="stylesheet" href="resources/css/report/receive_report.css" />
<script type="text/javascript" src="resources/js/admin/goods_manage_list.js"></script>
<jsp:include page="/WEB-INF/jsp/admin/head.jsp" />
</head>
<body>
  <div class="container-fluid main-content">
    <div class="row">
      <div class="col-lg-12">
        <div class="widget-container fluid-height clearfix">
          <div class="widget-content padded">
            <div class="form-group condition-group">
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">藏品名称</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="藏品名称" id="name" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">藏品种类（大）</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="bigSortId" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>	
                            <c:forEach items="${goodsBigSortList}" var="goodsSort">
                            	<option value="${goodsSort.id}">${goodsSort.name}</option>	
                            </c:forEach>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">藏品种类（小）</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="sortId" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>	
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">藏品序号</label>
                  </div>
                  <div class="condition-control fl">
                    <div class="col-md-7" style="width: 100%;">
                    <input class="form-control" placeholder="藏品序号" id="code" type="text" style="width: 100%;">
                  </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">藏品分类</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="type" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>	
                            <option value="0">常规商品</option>
                            <option value="1">礼品</option>
                            <option value="2">配售</option>
                            <option value="3">配送</option>
                            <option value="4">兑换</option>	
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                  <div class="condition-label fl">
                      <label class="control-label col-md-2" style=" width: 100%;">是否回购</label>
                  </div>
                  <div class="condition-control fl">
                      <div class="col-md-7" style="width: 100%;">
                        <select id="repurchaseFlag" class="form-control" style="width: 100%;">
                            <option value="">请选择</option>	
                            <option value="0">否</option>
                            <option value="1">是</option>
                        </select>
                    </div>
                  </div>
              </div>
              <div class="condition fl">
                <button class="btn btn-success" style="margin-left: 1%; float: left;">查&nbsp;询</button>
                <button class="btn btn-primary" id="export" style="float: left;">导&nbsp;出</button>
                <button class="btn btn-primary" id="add" style="float: left;">新&nbsp;增</button>
              </div>
            </div>
            <div style="clear: both;"></div>
            
            <table class="table table-bordered" id="goodsInfo">
                <thead>
                    <th>序&nbsp;&nbsp;号</th>
                    <th>藏品名称</th>
                    <th>藏品序号</th>
                    <th>种&nbsp;&nbsp;类</th>
                    <th>分&nbsp;&nbsp;类</th>
                    <th>金&nbsp;&nbsp;额</th>
                    <th>数&nbsp;&nbsp;量</th>
                    <th>是否可托管</th>
                    <th>回购标识</th>
                    <th>回购开始日期</th>
                    <th>回购结束日期</th>
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
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
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
                <button class="btn btn-primary">修改基本信息</button>
                <button class="btn btn-warning">修改文交所信息</button>
              </div>
            </div>
            <div class="col-md-6">
              <div class="heading"></div>
              <div class="widget-content padded">
                 <dl>
                  <dd>
                   <strong>	客户人员：</strong>${info.phoneStaffName }
                  </dd>
                  <dd>
                  	<strong>业务人员：</strong>${info.receiverStaffName }
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

      <!-- 购买历史 -->
      <div class="row">
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>购买历史
          </div>
          <div class="widget-content padded clearfix">
            <table class="table table-bordered">
              <thead>
              <tr><th>
                商品
              </th>
                <th>
                  金额
                </th>
                <th>
                  购买金额
                </th>
                <th class="hidden-xs">
                 交易时间
                </th>

              </tr></thead>
              <tbody>
              <tr>
                <td>
                  Robert
                </td>
                <td>
                  Kelso
                </td>
                <td>
                  robert@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              <tr>
                <td>
                  John
                </td>
                <td>
                  Dorian
                </td>
                <td>
                  john@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- 撤单商品 -->
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>撤单商品
          </div>
          <div class="widget-content padded clearfix">
            <table class="table table-bordered">
              <thead>
              <tr><th>
                商品
              </th>
                <th>
                  金额
                </th>
                <th>
                  购买金额
                </th>
                <th class="hidden-xs">
                  交易时间
                </th>

              </tr></thead>
              <tbody>
              <tr>
                <td>
                  Robert
                </td>
                <td>
                  Kelso
                </td>
                <td>
                  robert@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              <tr>
                <td>
                  John
                </td>
                <td>
                  Dorian
                </td>
                <td>
                  john@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
      <div class="row">
      <!-- 来访记录 -->
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>来访记录
          </div>
          <div class="widget-content padded clearfix">
            <table class="table table-bordered">
              <thead>
              <tr><th>
                商品
              </th>
                <th>
                  金额
                </th>
                <th>
                  购买金额
                </th>
                <th class="hidden-xs">
                  交易时间
                </th>

              </tr></thead>
              <tbody>
              <tr>
                <td>
                  Robert
                </td>
                <td>
                  Kelso
                </td>
                <td>
                  robert@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              <tr>
                <td>
                  John
                </td>
                <td>
                  Dorian
                </td>
                <td>
                  john@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      <!-- 回购商品 -->
      <div class="col-lg-6">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>回购商品
          </div>
          <div class="widget-content padded clearfix">
            <table class="table table-bordered">
              <thead>
              <tr><th>
                商品
              </th>
                <th>
                  金额
                </th>
                <th>
                  购买金额
                </th>
                <th class="hidden-xs">
                  交易时间
                </th>

              </tr></thead>
              <tbody>
              <tr>
                <td>
                  Robert
                </td>
                <td>
                  Kelso
                </td>
                <td>
                  robert@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              <tr>
                <td>
                  John
                </td>
                <td>
                  Dorian
                </td>
                <td>
                  john@gmail.com
                </td>
                <td class="hidden-xs">
                  8-15-2013
                </td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
      </div>
    </div>
  </body>
</html>
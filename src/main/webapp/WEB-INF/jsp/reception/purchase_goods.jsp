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
  <div class="container-fluid main-content">
  <div class="page-title">

      <div class="row">
        <div class="col-lg-8">
        <div class="widget-container fluid-height clearfix">
          <div class="heading">
            <i class="icon-table"></i>购买商品
          </div>
          <div class="widget-content padded">
            <form action="#" class="form-horizontal">
              <div class="form-group">
                <div class="col-md-6">
                    <label class="control-label col-md-4">商品编码</label>
                  <div class="col-md-8">
                    <input class="form-control" placeholder="Text" type="text">
                  </div>
                </div>
                <div class="col-md-6">
                    <label class="control-label col-md-4">商品名称</label>
                  <div class="col-md-8">
                    <input class="form-control" placeholder="Text" type="text">
                  </div>
                </div>
                </div>
              <div class="form-group">
                  <div class="col-md-6">
                    <label class="control-label col-md-4">售卖金额</label>
                    <div class="col-sm-4">
                      <input class="form-control"  type="text">
                    </div>
                    <div class="col-sm-4">
                      <input class="form-control"  type="text">
                    </div>
                  </div>
              </div>
            </form>
            <div  class="col-md-12" style="left:80%">
                <button class="btn btn-primary">查 询</button>
                <button class="btn btn-default">清 空</button>
            </div>
          </div>
          <div class="widget-container fluid-height clearfix">
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
              <div style="float: right"><ul class="pagination">
                <li>
                  <a class="icon" href="#"><i class="icon-long-arrow-left"></i></a>
                </li>
                <li class="active">
                  <a href="#">1</a>
                </li>
                <li>
                  <a href="#">2</a>
                </li>
                <li>
                  <a href="#">3</a>
                </li>
                <li>
                  <a href="#">4</a>
                </li>
                <li>
                  <a href="#">5</a>
                </li>
                <li>
                  <a class="icon" href="#"><i class="icon-long-arrow-right"></i></a>
                </li>
              </ul></div>
            </div>
            <!--add myModal statr-->
            <div class="widget-content padded">
              <a class="btn btn-primary btn" data-toggle="modal" href="#myModal">配 送</a>
              <a class="btn btn-warning btn" data-toggle="modal" href="#myModal">配 售</a>
              <a class="btn btn-default btn" data-toggle="modal" href="#myModal">赠 品</a>
            </div>
            <div class="modal fade" id="myModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
                    <h4 class="modal-title">
                      Se7en
                    </h4>
                  </div>
                  <div class="modal-body">
                    <h1>
                      Welcome
                    </h1>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet auctor purus, non imperdiet sapien dapibus non. Phasellus pretium rutrum elit in cursus. Donec ullamcorper nec massa vel mattis. Curabitur eros metus, dapibus quis est et, dapibus imperdiet dolor. Aenean ac aliquet dolor. Ut porta ultrices justo a tempor. Curabitur eget magna mattis risus accumsan aliquet et a lorem. Pellentesque hendrerit dapibus urna, adipiscing ultrices velit accumsan eget. Fusce eget ultrices turpis, vitae facilisis orci. Curabitur scelerisque consequat vulputate. Aenean felis orci, porttitor ut eros vel, egestas ultricies nunc. Duis suscipit elementum tincidunt.
                    </p>
                  </div>
                  <div class="modal-footer">
                    <button class="btn btn-primary" type="button">Save Changes</button><button class="btn btn-default-outline" data-dismiss="modal" type="button">Close</button>
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
              <tbody>
              <tr>
                <td>
                  <h6>商品名称： 商品A</h6>
                  <h6>购买数量: 1</h6>
                  <span class="label label-danger" style="float: right">删除</span>
                </td>
              </tr>
              <tr>
                <td>
                  <h6>商品名称： 商品A</h6>
                  <h6>购买数量: 1</h6>
                  <span class="label label-danger" style="float: right">删除</span>
                </td>
              </tr>
              <tr>
                <td>
                  <h6>商品名称： 商品A</h6>
                  <h6>购买数量: 1</h6>
                  <span class="label label-danger" style="float: right">删除</span>
                </td>
              </tr>

              </tbody>
            </table>
          <div class="row">
              <button class="btn btn-warning" >去结算</button>
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
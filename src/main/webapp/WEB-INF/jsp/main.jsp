<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>控制台</title>
    <base href="${basePath}">
  </head>
  <body>
  <div class="modal-shiftfix">
	  <div class="container-fluid main-content">
		  <div class="page-title">
		  <div class="col-lg-12">
            <ul class="breadcrumb">
              <li>
                <i class="icon-home"> 你好：<shiro:principal/> , 欢迎使用本系统，祝您工作愉快  ^ ^ </i>
              </li>
            </ul>
          </div>
			 <div class="row">
			  <shiro:hasPermission name="job:button">
		          <div class="col-md-4">
			            <div class="widget-container weather">
			                <div class="row text-center">
			                    <div class="number" style="font-size: 50px; margin-top: 20px;">
			                      	我的工作
			                    </div>
			                    <a class="btn btn-warning" style="margin-top:20px" href="<%=request.getContextPath() %>/job/init?active=1"><i class="icon-briefcase"></i>点击登陆</a>
			              </div>
			            </div>
		          </div>
	          </shiro:hasPermission>
	          
	          <shiro:hasPermission name="visit:button">
		          <div class="col-md-4">
			            <div class="widget-container weather">
			                <div class="row text-center">
			                    <div class="number" style="font-size: 50px;margin-top: 20px;">
			                      	接待管理
			                    </div>
			                    <a class="btn btn-warning" style="margin-top:20px" href="<%=request.getContextPath() %>/visit/query?active=1"><i class="icon-glass"></i>点击登陆</a>
			              </div>
			            </div>
		          </div>
	          </shiro:hasPermission>
	          <shiro:hasPermission name="telephone:button">
		          <div class="col-md-4">
			            <div class="widget-container weather">
			                <div class="row text-center">
			                    <div class="number" style="font-size: 50px;margin-top: 20px;">
			                      	数据管理
			                    </div>
			                    <a class="btn btn-warning" style="margin-top:20px"><i class="icon-phone-sign"></i>点击登陆</a>
			              </div>
			            </div>
		          </div>
	         </shiro:hasPermission>
			</div>

			<div class="row">
			<shiro:hasPermission name="statistics:button">
	          <div class="col-md-4">
		            <div class="widget-container weather">
		                <div class="row text-center">
		                    <div class="number" style="font-size: 50px; margin-top: 20px;">
		                      	统计/报表
		                    </div>
		                    <a class="btn btn-warning" href="report/toSalesmanStatement?active=1" style="margin-top:20px"><i class="icon-qrcode"></i>点击登陆</a>
		              </div>
		            </div>
	          </div>
	        </shiro:hasPermission>
	         <shiro:hasPermission name="admin:button">
		          <div class="col-md-4">
			            <div class="widget-container weather">
			                <div class="row text-center">
			                    <div class="number" style="font-size: 50px;margin-top: 20px;">
			                      	后台管理
			                    </div>
			                    <a class="btn btn-warning" href="admin/resource?active=1" style="margin-top:20px"><i class="icon-laptop"></i>点击登陆</a>
			                     <!-- <a class="btn btn-warning" href="role?active=1" style="margin-top:20px"><i class="icon-laptop"></i>角色管理</a> -->
			              </div>
			            </div>
		          </div>
	          </shiro:hasPermission>
	          <div class="col-md-4">
		            <div class="widget-container weather">
		                <div class="row text-center">
		                    <div class="number" style="font-size: 50px;margin-top: 20px;">
		                      	退出
		                    </div>
		                    <a class="btn btn-warning" style="margin-top:20px" href="<%=request.getContextPath() %>/logout"><i class="icon-mail-forward"></i>点击退出</a>
		              </div>
		            </div>
	          </div>
			</div>
		  </div>
	  </div>
  </div>
  </body>
</html>
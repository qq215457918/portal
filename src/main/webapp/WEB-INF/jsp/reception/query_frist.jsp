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
                <i class="icon-tags"></i>用户详细信息  ${from }
              </div>
              <div class="widget-content padded">
                <dl>
                  <dt>
                  	 用户姓名： 张三
                  </dt>
                  <dd>
                  	 电话：15009876541
                  </dd>
                </dl>

              </div>
            </div>
            <div class="col-md-6">
              <div class="heading"></div>
              <div class="widget-content padded">
                <dl>
                  <dd>
                   	 接待人员：张三
                  </dd>
                  <dd>
                  	  业务人员：李四
                  </dd>
                  	最近登门时间：2015-07-27
                </dl>
                <dl>
                  <dd>
                    	接待人员：张三
                  </dd>
                  <dd>
                  	  业务人员：李四
                  </dd>
                 	 最近登门时间：2015-07-27
                </dl>
              </div>
            </div>
            <div  class="col-md-2">
              <button class="btn btn-lg btn-primary">开始接待</button>
            </div>
            <div  class="col-md-3">
              <button class="btn btn-lg btn-default">退 出</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
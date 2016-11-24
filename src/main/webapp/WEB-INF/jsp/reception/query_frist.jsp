<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jstl/core_rt" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
    <title>首次登陆</title>
    <base href="${basePath}">
	<%-- <jsp:include page="head.jsp" /> --%>
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
                  	<strong> 客户类型： </strong>${result.type }
                  </dd>
                  <dd>
                  	 <strong>用户姓名：</strong> ${result.name } 
                  </dd>
                  <dd>
                  	<strong> 电话：</strong>${result.encryptPhone }
                  </dd>
                  <dd>
                  	<strong> 备用电话：</strong>${result.encryptPhone2 }
                  </dd>
                </dl>
              </div>
            </div>
            <div class="col-md-6">
              <div class="heading"></div>
              <div class="widget-content padded">
                <dl>
                
                <dd>
                   <strong>	关联亲友：</strong>${result.relationId }
                  </dd>
                  <dd>
                   <strong>	客服人员：</strong>${result.phoneStaffName }
                  </dd>
                  <dd>
                  	<strong> 接待人员：</strong>${result.receiverStaffName }
                  </dd>
                  <dd>
                  	<strong>最近登门时间：</strong>${result.recentVisitDate }
                  </dd>
                  <dd>
                    <strong>是否黑名单：</strong>${result.blacklistFlag }
                  </dd>
                </dl>
              </div>
            </div>
            <input type="hidden" name="cid" id="cid" value="${result.id }"/>
            <input type="hidden" name="phone" id="cphone" value="${result.phone }"/>
            <div  class="col-md-2">
              <button class="btn btn-lg btn-primary" id ="receiveId">开始接待</button>
            </div>
            <div  class="col-md-3">
              <button class="btn btn-lg btn-default" id ="quit">退 出</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  	<script>
	$(function(){
		base = $("base").attr('href');
		// 查询功能
		$("#receiveId").click(function(){
			var cid = $('#cid').val();
			var phone = $('#cphone').val();
			window.location.href=base+"/visit/second?active=1&cId="+cid;
		});
		
		// 查询功能
		$("#quit").click(function(){
			var id = $('#cid').val();
			window.location.href=base+"/visit/quit?id="+id;
		});
		
		
	});
	</script>
</html>
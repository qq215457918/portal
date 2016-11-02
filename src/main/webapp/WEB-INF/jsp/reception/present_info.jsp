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
		        <div class="col-lg-12">
		          <div class="widget-container label-container fluid-height">
		            <div class="heading">
		              <i class="icon-tags"></i>赠品管理
		            </div>
		            <div class="widget-content text-center">
		              <h3>
		              	  欢迎领取赠品 ^ ^
		              </h3>
		              <div class="form-group">
			            <label class="control-label col-md-2">请选择要领取的赠品</label>
			            <div class="col-md-7">
			              <select class="form-control">
			               <c:forEach var="list" items="${goodsInfoList}">
		                      <option value="${list.id}">${list.name}</option>
		                   </c:forEach>
			              </select>
			            </div>
			            <input class="btn btn-warning" style="margin:20px;float:right "type="submit" value="确 认">
			          </div>  
		              <a class="btn btn-primary btn"  data-toggle="modal" href="#presentModal">特殊审批</a>
		            </div>
		          </div>
		        </div>
		      </div>
		      <div class="row">
		        <div class="col-lg-12">
		          <div class="widget-container label-container fluid-height">
		            <div class="heading">
		              <i class="icon-tags"></i>今日领取记录
		            </div>
		            <div class="widget-content text-center">
		              <h3>
		              	  欢迎领取赠品 ^ ^
		              </h3>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
	  </div>
        <!-- modal Start -->
           <div class="modal fade" id="presentModal">
             <div class="modal-dialog">
               <div class="modal-content">
                 <div class="modal-header">
                   <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
                   <h4 class="modal-title">
                   	  请输入审批原因
                   </h4>
                 </div>            
                 <div class="modal-body">
                 	赠品名称：<select class="form-control" id="applyGoods">
		               <c:forEach var="list" items="${goodsInfoList}">
	                      <option value="${list.id}">${list.name}</option>
	                   </c:forEach>
		              </select>
		                                赠品数量： <input class="form-control" value="1" type="text" id="applyCount">
                                                     申请原因：<textarea class="form-control" rows="3" id="applyReason"></textarea>
                 </div>
                 <div class="modal-footer">
                   <button class="btn btn-primary" type="button" id="appConfirm">确 认</button>
                   <button class="btn btn-default-outline" data-dismiss="modal" type="button">取 消</button>
                 </div>
               </div>
             </div>
           </div>
         <!-- modal end -->
   <script>
 	$(function(){
		base = $("base").attr('href'); 		
		// 查询功能
		$("#appConfirm").click(function(){
			var goodId = $('#applyGoods').val();
			var count = $('#applyCount').val();
			var reason = $('#applyReason').val();
			//window.location.href=base+"/present/review?reason="+reason;
			$.ajax({
				method : "POST",
				url : base+"/present/review",
				data : {
					"reason" : reason,
					"count" : count,
					"goodId" : goodId,
					"customerId":"1"
				},
				dataType : "JSON",
				success : function(data) {
					if(data.result==true){
						alert("提交成功，等待审批人进行审批")
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					console.log('XMLHttpRequest.status :' + XMLHttpRequest.status);
					console.log('XMLHttpRequest.readyState :'
							+ XMLHttpRequest.readyState);
					console.log('textStatus:' + textStatus);
				}
			});
		});
	}); 
	</script>
  </body>
</html>
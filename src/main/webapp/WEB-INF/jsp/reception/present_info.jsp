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
			            <input class="btn btn-warning" style="margin:20px;float:right "type="submit" id="presentReceive" value="确 认">
			          </div>  
		              <a class="btn btn-primary btn"  data-toggle="modal" href="#presentModal">特殊审批</a>
		            </div>
		          </div>
		        </div>
		      </div>
      <!-- 购买历史 -->
	      <div class="row">
		      <div class="col-lg-6">
		        <div class="widget-container fluid-height clearfix">
		          <div class="heading">
		            <i class="icon-table"></i>今日礼品领取
		          </div>
		          <div class="widget-content padded clearfix">
		            <table class="table table-bordered">
		              <thead>
		              <tr>
		                <th>
		                	 序号
		                </th>
		                <th>
		                	 礼品名称
		                </th>
		                <th>
		       				申请时间
		                </th>
		                <th>
		                	审核状态
		                </th>
		              </tr></thead>
		              <tbody id="presentListTbody">
		              </tbody>
		            </table>
		          </div>
		        </div>
		      </div>
			</div>
		    <!-- list end -->
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
		getPresent();
		var goodId = $('#applyGoods').val();
		// 查询功能
		$("#appConfirm").click(function(){
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
						alert("提交成功，等待审批人进行审批");
						$('#presentModal').modal('hide');
						getPresent();
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
        //领取赠品		
		$("#presentReceive").click(function(){	
			$.ajax({
				method : "POST",
				url : base+"/present/receive",
				data : {
					"goodId" : goodId,
					"customerId":"1"
				},
				dataType : "JSON",
				success : function(data) {
					if(data.result==true){
						alert("礼物订单已经提交，请到库房进行领取");
						$("#presentReceive").attr('disabled',"true");
						$("#applyGoods").attr('disabled',"true");
						getPresent();
				    }
				}
			});	
		}); 
        
	  }); 
 	
 	function getPresent(){
		$("#presentListTbody").html("");
		$.ajax({
			method : "POST",
			url : base+"/present/today",
			data : {
				"customerId":"1"
			},
			dataType : "JSON",
			success : function(data) {
				if(data.result.length>0){
			 		var item ;
			 		var financeFlag ;
					var orderList = data.result;				
					$.each(data.result, function(n, value) {
						if(value.financeFlag==1){
							financeFlag="已经审批通过";
						}else{
							financeFlag="未通过审核";
						}
						item += "<tr><td>"+Number(n+1)+"</td>";					
						item += "<td>"+value.orderDetailInfoList[0].goodName+"</td>";
						item += "<td>"+value.createDateString+"</td>";
						item += "<td>"+financeFlag+"</td></tr>";
					});
				}else{
					item += "<tr><td align='center' colspan='4'>当天没有领取正品</td></tr>";
				}
				$("#presentListTbody").append(item);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('XMLHttpRequest.status :' + XMLHttpRequest.status);
				console.log('XMLHttpRequest.readyState :'
						+ XMLHttpRequest.readyState);
				console.log('textStatus:' + textStatus);
			}
		});
 	}
	</script>
  </body>
</html>
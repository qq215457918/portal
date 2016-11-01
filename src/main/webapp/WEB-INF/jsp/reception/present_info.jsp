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
				              <option value="Category 1">选项 1</option>
				              <option value="Category 2">选项 2</option>
				              <option value="Category 3">选项 3</option>
				              <option value="Category 4">选项 4</option>
			              </select>
			            </div>
			            <input class="btn btn-warning" style="margin:20px;float:right "type="submit" value="确 认">
			          </div>  
		              <a class="btn btn-primary btn"  data-toggle="modal" href="#presentModal">特殊审批</a>
		              <div class="modal fade" id="myModal">
		                <div class="modal-dialog">
		                  <div class="modal-content">
		                    <div class="modal-header">
		                      <button aria-hidden="true" class="close" data-dismiss="modal" type="button">&times;</button>
		                      <h4 class="modal-title">
		                      	  请输入审批原因
		                      </h4>
		                    </div>
		                    <div class="modal-body">
		                      <textarea class="form-control" rows="3"></textarea>
		                    </div>
		                    <div class="modal-footer">
		                      <button class="btn btn-primary" type="button">确 认</button>
		                      <button class="btn btn-default-outline" data-dismiss="modal" type="button">取 消</button>
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
	  </div>
	        <!--modal end-->
        <div class="modal fade" id="presentModal">
           <div class="modal-dialog">
             <div class="modal-content">
               <div class="modal-header">
                 <button aria-hidden="true" class="close" data-dismiss="modal" type="button">×</button>
                 <h4 class="modal-title">
                                                     特殊审批
                 </h4>
               </div>
               <div class="modal-body">
                 <h4>请输入特殊审批内容，并确认</h4>
               </div>
               
               <div class="modal-footer">
                 <button class="btn btn-primary" type="button" id="quitConfirm">确 认</button><button class="btn btn-default-outline" data-dismiss="modal" type="button">取 消</button>
               </div>
             </div>
           </div>
       </div>
         <!--modal end-->
   <script>
	$(function(){
		base = $("base").attr('href'); 
		
		// 查询功能
		$("#receiveId").click(function(){
			var id = $('#cid').val();
			var phone = $('#cphone').val();
			window.location.href=base+"/visit/second?id="+id+"&phone="+phone;
		});
	});
	</script>
  </body>
</html>
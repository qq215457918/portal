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
			    <div class="widget-container fluid-height clearfix">
			      <div class="heading">
			        <i class="icon-collapse"></i>下拉列表（自定义样式）
			      </div>
			      <div class="widget-content padded">
			        <form action="#" class="form-horizontal">
			          <div class="form-group">
			            <label class="control-label col-md-2">下拉列表（自定义样式）</label>
			            <div class="col-md-7">
			              <div class="select2-container select2able" id="s2id_autogen1"><a href="javascript:void(0)" onclick="return false;" class="select2-choice" tabindex="-1">   <span class="select2-chosen">选项 3</span><abbr class="select2-search-choice-close"></abbr>   <span class="select2-arrow"><b></b></span></a><input class="select2-focusser select2-offscreen" type="text" id="s2id_autogen2"></div><select class="select2able select2-offscreen" tabindex="-1"><option value="Category 1">选项 1</option><option value="Category 2">选项 2</option><option value="Category 3">选项 3</option><option value="Category 4">选项 4</option></select>
			            </div>
			          </div>
			          <div class="form-group">
			            <label class="control-label col-md-2">多选下拉列表（自定义样式）</label>
			            <div class="col-md-7">
			              <div class="select2-container select2-container-multi select2able" id="s2id_autogen3"><ul class="select2-choices">  <li class="select2-search-choice">    <div>选项 2</div>    <a href="#" onclick="return false;" class="select2-search-choice-close" tabindex="-1"></a></li><li class="select2-search-field">    <input type="text" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" class="select2-input" id="s2id_autogen4" style="width: 20px;">  </li></ul></div><select class="select2able select2-offscreen" multiple="" tabindex="-1"><option value="Category 1">选项 1</option><option value="Category 2">选项 2</option><option value="Category 3">选项 3</option><option value="Category 4">选项 4</option></select>
			            </div>
			          </div>
			        </form>
			      </div>
			    </div>
			  </div>
			</div>
		  
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
		              <button class="btn btn-danger">确 认</button>
		              <a class="btn btn-primary btn" data-toggle="modal" href="#presentModal">特殊审批</a>
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
  </body>
</html>
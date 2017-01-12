/**
 * 文件名：compile_employee_info
 * 用途：编辑员工信息页面相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-12-22
 */
// base:项目根路径
var base;
$(function() {
	
	base = $("base").attr('href');
	
	// 所属机构变化事件
    $("#organizationId").change(function(){
    	var value = $(this).val();
    	if(value != null & value != '') {
    		// 获取机构下部门
            changeDept("#departmentId", $("#organizationId").val());
    	}else {
    		// 清空部门
            $("#departmentId").empty();
            $("#departmentId").html('<option value="">请选择</option>');
    	}
        // 清空组
        $("#groupId").empty();
        $("#groupId").html('<option value="">请选择</option>');
    });
    
    // 部门下拉选项变化事件
    $("#departmentId").change(function(){
    	var value = $(this).val();
    	if(value != null & value != '') {
    		// 获取部门下的小组
            changeDept("#groupId", $("#departmentId").val());
    	}else {
    		// 清空组
            $("#groupId").empty();
            $("#groupId").html('<option value="">请选择</option>');
    	}
    });
    
    // 保存信息
    $("#saveEmployee").click(function(){
    	var name = $("#name").val();
    	var loginName = $("#loginName").val();
    	var password = $("#password").val();
    	var organizationId = $("#organizationId").val();
    	var roleIds = $("#roleIds").val();
    	
    	if(name == null || name == "") {
    		$("#name").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("#name").removeAttr("style");
    	}
    	if(loginName == null || loginName == "") {
    		$("#loginName").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("#loginName").removeAttr("style");
    	}
    	if(password == null || password == "") {
    		$("#password").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("#password").removeAttr("style");
    	}
    	if(organizationId == null || organizationId == "") {
    		$("#organizationId").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("#organizationId").removeAttr("style");
    	}
    	if(roleIds == null || roleIds == "") {
    		$("#roleIds").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("#roleIds").removeAttr("style");
    	}
    	
    	var options = {
    		 url: $('#form').attr("action"),
   		     type:"POST",
   		     dataType:"json",
   		     success:function(data){
   		    	 if(data.status == 1) {
   		    		 alert("操作成功");
   		    		 location.href = base + "admin/employeeManage/toEmployeeManage?active=1";
   		    	 }else {
   		    		 if(data.text) {
   		    			 alert(data.text);
   		    		 }else {
   		    			 alert("操作失败,请刷新后重试");
   		    		 }
   		    	 }
   		     },
   		     error:function(err){
   		    	 alert("操作失败,请刷新后重试");
   		     }
   		}
   		$('#form').ajaxForm(options);
    })
	
	$("#back").click(function(){
		window.history.back(-1);
	})

});

/**
 * 文件名：employee_manage
 * 用途：员工管理页面相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-12-21
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
    base = $("base").attr('href');
    
    // 绑定日期事件
    $('#startCreateDate').datepicker({format: 'yyyy-mm-dd'});
    $('#endCreateDate').datepicker({format: 'yyyy-mm-dd'});
    
    // 页面初始化加载数据
    initData();
    
    // 查询功能
    $(".btn-success").click(function(){
        // 查询数据
        $('#employee').dataTable().fnDraw();
    });
    
    // 员工姓名输入框回车事件
    $("#name").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#employee').dataTable().fnDraw();
        }
    });
    
    // 所属机构变化事件
    $("#organizationId").change(function(){
        // 清空查询条件
        $("#name").val('');
        $("#departmentId").val('');
        $("#groupId").val('');
        $("#sex").val('');
        $("#startCreateDate").val('');
        $("#endCreateDate").val('');
        
        // 获取机构下部门
        changeDept("#departmentId", $("#organizationId").val());
        
        // 清空组
        $("#groupId").empty();
        $("#groupId").html('<option value="">请选择</option>');
        
        // 查询数据
        $('#employee').dataTable().fnDraw();
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
        
        // 查询数据
        $('#employee').dataTable().fnDraw();
    });
    
    // 进入新增页面
    $("#add").click(function(){
    	location.href = base + "admin/employeeManage/toCompileEmployeeInfo";
    });
    
    // 导出功能
    $("#export").click(function(){
    	var organizationId = $('#organizationId').val();
        var name = $('#name').val();
        var departmentId = $('#departmentId').val();
        var groupId = $('#groupId').val();
        var sex = $('#sex').val();
        var startCreateDate = $('#startCreateDate').val();
        var endCreateDate = $('#endCreateDate').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '<form id="exportExcel" action="admin/employeeManage/exportEmployee" style="display:none;">' +
						'<input type="hidden" name="name" value="' + name + '"/>' +
						'<input type="hidden" name="sex" value="' + sex + '"/>' +
						'<input type="hidden" name="organizationId" value="' + organizationId + '"/>' +
						'<input type="hidden" name="departmentId" value="' + departmentId + '"/>' +
						'<input type="hidden" name="groupId" value="' + groupId + '"/>' +
						'<input type="hidden" name="startCreateDate" value="' + startCreateDate + '"/>' +
						'<input type="hidden" name="endCreateDate" value="' + endCreateDate + '"/>';
					'</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
    });

});

// 初始获取数据
function initData() {
    $("#employee").dataTable({
        "bSort": false, //是否显示排序
        "bFilter": false, //去掉搜索
        "bPaginate": true,     //分页
        "sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
        "bLengthChange" : true,// 每页显示记录数
        "iDisplayLength" : $("select[name='employee_length']").val(),// 每页显示行数
        "bProcessing": true, //显示正在处理
        "bServerSide": true, // 后台请求
        "bInfo" : true,         // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
        "bRetrieve": true,
        "sAjaxSource": "admin/employeeManage/ajaxEmployeeData", // 地址
        "aoColumns": [ 
                    {"mData": null, "target": 0},    //序列号
                    {"mData": "name"},
                    {"mData": "sex"},
                    {"mData": "loginName"},
                    {"mData": "departmentName"},
                    {"mData": "groupName"},
                    {"mData": "positionType"},
                    {"mData": "receptionFlag"},
                    {"mData": "viewCreateDate"},
                    {"mData": null},
                    {"mData": "id", "visible": false}
                   ],
        // 自定义最后一列的字段内容为修改和删除按钮
        "aoColumnDefs": [{"aTargets":[9],"mRender":function(data,type,full){
        	if(data.receptionFlag != "正在接待") {
        		return '<div style="display: inherit;"><button class="btn btn-xs btn-warning update" data_id="'+data.id+'" style="float: left; margin: 0 10px 0 0;">修&nbsp;改</button><button class="btn btn-xs btn-warning delete" data_id="'+data.id+'" data_name="'+data.name+'" style="margin: 0 0 0 0;">删&nbsp;除</button></div>';
        	}else {
        		return '';
        	}
        }}],
        "fnServerData": function (sSource, aoData, fnCallback) {
                            var organizationId = $('#organizationId').val();
                            var name = $('#name').val();
                            var departmentId = $('#departmentId').val();
                            var groupId = $('#groupId').val();
                            var sex = $('#sex').val();
                            var startCreateDate = $('#startCreateDate').val();
                            var endCreateDate = $('#endCreateDate').val();
                            aoData.push(
                                        {'name':'organizationId','value':organizationId},
                                        {'name':'name','value':name},
                                        {'name':'departmentId','value':departmentId},
                                        {'name':'groupId','value':groupId},
                                        {'name':'sex','value':sex},
                                        {'name':'startCreateDate','value':startCreateDate},
                                        {'name':'endCreateDate','value':endCreateDate}
                                    );
                            $.ajax({
                                "dataType": 'json',
                                "type": "POST",
                                "url": sSource,
                                "data": aoData,
                                "success": function(data){
                                	// 回调函数
                                    fnCallback(data);
                                    // 绑定修改事件
                                    $(".update").click(function(){
                                    	var _this = $(this);
                                    	var id = _this.attr("data_id");
                                    	location.href = base + "admin/employeeManage/toCompileEmployeeInfo?id=" + id;
                                    });
                                    
                                    // 绑定删除事件
                                    $(".delete").click(function(){
                                    	var id = $(this).attr("data_id");
                                    	var name = $(this).attr("data_name");
                                    	deleteEmployee(id, name);
                                    });
                                }
                            })
                        }
    });
}

//异步删除员工信息
function deleteEmployee(id, name) {
	if(id) {
		if(confirm("确认删除[ " + name + " ]的信息吗?")) {
			$.ajax({
		        "dataType": 'json',
		        "type": "POST",
		        "url": base + "admin/employeeManage/deleteEmployeeInfo",
		        "data": {
		        	employeeId : id
		        },
		        "success": function(data){
		            if(data.status == '1') {
		            	alert("操作成功", function(){
		            		location.reload();
		            	});
		            }else {
		            	if(data.text) {
		            		alert(data.text);
		            	}else {
		            		alert("删除数据失败, 请刷新后重试");
		            	}
		            }
		        },
		        "error": function(data){
		            alert("删除数据失败, 请刷新后重试");
		        }
		    });
		}
	}else {
		alert("获取数据失败, 请刷新后重试");
	}
}


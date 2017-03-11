/**
 * 文件名：payment_account_list
 * 用途：收款账户管理页面相关js
 * 作者：Xia ZhengWei
 * 时间: 2017-01-10
 */
// base:项目根路径, 是否可用单选
var base, isUsable;
// json:图标容器
var json = {};

$(function() {
    base = $("base").attr('href');
    
    // 页面初始化加载数据
    initData();
    
    // 查询功能
    $(".btn-success").click(function(){
        // 查询数据
        $('#payment').dataTable().fnDraw();
    });
    
    // 收款账户输入框回车事件
    $("#paymentAccountName").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#payment').dataTable().fnDraw();
        }
    });
    // 银行名称输入框回车事件
    $("#bankName").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#payment').dataTable().fnDraw();
        }
    });
    // 账号输入框回车事件
    $("#accountNumber").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#payment').dataTable().fnDraw();
        }
    });
    
    // 新增
    $("#add").click(function(){
    	$(".ceng").show();
    	$(".cengBox").show();
    });
    
    // 关闭弹窗
    $(".gblOff").click(function(){
    	$(".ceng").hide();
    	$(".cengBox").hide();
    	// 清空输入框内容
    	$("input[name='paymentAccountId']").val('');
    	$("input[name='paymentAccountName']").val('');
    	$("input[name='bankName']").val('');
    	$("input[name='accountNumber']").val('');
    	if(isUsable) {
    		var parent = $("input[name='isUsable']").parent().parent();
    		parent.empty();
    		parent.html(isUsable);
    	}
    	// 移除样式
    	$("input[name='paymentAccountName']").removeAttr("style");
    	$("input[name='bankName']").removeAttr("style");
    	$("input[name='accountNumber']").removeAttr("style");
    });
    
    // 关闭删除弹窗
    $(".deleteOff").click(function(){
    	$(".ceng").hide();
    	$(".deleteCengBox").hide();
    	// 清空输入框内容
    	$("input[name='paymentId']").val('');
    	$("input[name='deleteReason']").val('');
    	$("input[name='deleteReason']").removeAttr("style");
    });
    
    // 保存
    $(".glbSave").click(function(){
    	var paymentAccountName = $("input[name='paymentAccountName']").val();
    	var bankName = $("input[name='bankName']").val();
    	var accountNumber = $("input[name='accountNumber']").val();
    	if(paymentAccountName == null || paymentAccountName == "") {
    		$("input[name='paymentAccountName']").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("input[name='paymentAccountName']").removeAttr("style");
    	}
    	if(bankName == null || bankName == "") {
    		$("input[name='bankName']").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("input[name='bankName']").removeAttr("style");
    	}
    	if(accountNumber == null || accountNumber == "") {
    		$("input[name='accountNumber']").css({"border": "1px solid red"});
    		return false;
    	}else {
    		$("input[name='accountNumber']").removeAttr("style");
    	}
    	var options = {
       		 url: $('#form').attr("action"),
  		     type:"POST",
  		     dataType:"json",
  		     success:function(data){
  		    	 if(data.status == 1) {
  		    		 alert("操作成功");
  		    		$(".cengBox").hide();
  		    		 location.reload();
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
    });
    
    // 删除
    $(".deleteSave").click(function(){
    	var paymentId = $("input[name='paymentId']").val();
    	var deleteReason = $("input[name='deleteReason']").val();
    	if(paymentId == null || paymentId == "") {
    		alert("数据不符, 请刷新后重试");
    		return;
    	}
    	if(deleteReason == null || deleteReason == "") {
    		$("input[name='deleteReason']").css({"border": "1px solid red"});
    		return;
    	}else {
    		$("input[name='deleteReason']").removeAttr("style");
    	}
    	$.ajax({
            "dataType": 'json',
            "type": "POST",
            "url": base + "admin/paymentAccount/deletePaymentAccount",
            "data": {
            	paymentAccountId : paymentId,
            	deleteReason : deleteReason
            },
            "success": function(data){
                if(data.status == '1') {
                	alert("操作成功");
                	$(".deleteCengBox").hide();
                	location.reload();
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
    });

});

// 初始获取数据
function initData() {
    $("#payment").dataTable({
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
        "sAjaxSource": "admin/paymentAccount/ajaxPaymentAccountData", // 地址
        "aoColumns": [ 
                    {"mData": null, "target": 0},    //序列号
                    {"mData": "paymentAccountName"},
                    {"mData": "bankName"},
                    {"mData": "accountNumber"},
                    {"mData": "organization"},
                    {"mData": "viewIsUsable"},
                    {"mData": null}
                   ],
        // 自定义最后一列的字段内容为修改和删除按钮
        "aoColumnDefs": [{"aTargets":[6],"mRender":function(data,type,full){
    		return '<div style="display: inherit;"><button class="btn btn-xs btn-warning update" data_id="'+data.paymentAccountId+'" style="float: left; margin: 0 10px 0 0;">修&nbsp;改</button><button class="btn btn-xs btn-warning delete" data_id="'+data.paymentAccountId+'" data_name="'+data.paymentAccountName+'" style="margin: 0 0 0 0;">删&nbsp;除</button></div>';
        }}],
        "fnServerData": function (sSource, aoData, fnCallback) {
                            var paymentAccountName = $('#paymentAccountName').val();
                            var bankName = $('#bankName').val();
                            var accountNumber = $('#accountNumber').val();
                            var organizationId = $('#organizationId').val();
                            var isUsable = $('#isUsable').val();
                            aoData.push(
                                        {'name':'paymentAccountName','value':paymentAccountName},
                                        {'name':'bankName','value':bankName},
                                        {'name':'accountNumber','value':accountNumber},
                                        {'name':'organizationId','value':organizationId},
                                        {'name':'isUsable','value':isUsable}
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
                                    	loadPayment(id);
                                    });
                                    
                                    // 绑定删除事件
                                    $(".delete").click(function(){
                                    	var id = $(this).attr("data_id");
                                    	var name = $(this).attr("data_name");
                                    	deletePayment(id, name);
                                    });
                                }
                            });
                        }
    });
}

// 更新加载
function loadPayment(id) {
	$.ajax({
        "dataType": 'json',
        "type": "POST",
        "url": base + "admin/paymentAccount/loadPaymentAccount",
        "data": {
        	paymentAccountId : id
        },
        "success": function(data){
            if(data.status == '1') {
            	$(".ceng").show();
            	$(".cengBox").show();
            	var payment = data.payment;
            	isUsable = $("input[name='isUsable']").parent().parent().html();
            	$("input[name='paymentAccountId']").val(payment.paymentAccountId);
            	$("input[name='paymentAccountName']").val(payment.paymentAccountName);
            	$("input[name='bankName']").val(payment.bankName);
            	$("input[name='accountNumber']").val(payment.accountNumber);
            	
            	
            	$("select[name='organizationId']").val(payment.organizationId);
            	
            	
            	$("input[name='isUsable']").each(function(){
            		if($(this).attr("value") == payment.isUsable) {
            			$(this).attr("checked", "checked");
            		}
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

// 删除收款账户信息
function deletePayment(id, name) {
	if(id) {
		if(confirm("确认删除[ " + name + " ]的信息吗?")) {
			$(".ceng").show();
			$(".deleteCengBox").show();
			$("input[name='paymentId']").val(id);
			$("#account").text(name);
		}
	}else {
		alert("获取数据失败, 请刷新后重试");
	}
}

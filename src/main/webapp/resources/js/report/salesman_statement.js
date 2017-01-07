/**
 * 文件名：salesman_statement
 * 用途：业务员统计列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-27
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startDate').datepicker({format: 'yyyy-mm-dd'});
	$('#endDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#statement').dataTable().fnDraw();
	});
	
	// 接待人输入框回车事件
	$("#receiveStaffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#statement').dataTable().fnDraw();
		}
	});
	
	// 接待人所属区域变化事件
	$("#area").change(function(){
		// 清空接待人查询条件
		$("#receiveStaffName").val('');
		// 查询数据
		$('#statement').dataTable().fnDraw();
	});
	
	// 导出功能
	$("#export").click(function(){
		alert("暂时还没有做......");
	});

});

// 初始获取数据
function initData() {
	$("#statement").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='statement_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxSalesmanStatement", // 地址
		"aoColumns": [ 
		            {"mData": "receiverStaffName"},
		            {"mData": "lockedCounts"},
		            {"mData": "lockedOrders"},
		            {"mData": "lockedAmounts"},
		            {"mData": "finishOrderCounts"},
		            {"mData": "finishOrders"},
		            {"mData": "finishAmounts"},
		            {"mData": "repeatCounts"},
		            {"mData": "repeatOrders"},
		            {"mData": "repeatAmounts"},
		            {"mData": "roadshowCounts"},
		            {"mData": "roadshowOrders"},
		            {"mData": "roadshowAmounts"},
		            {"mData": "newCounts"},
		            {"mData": "newOrders"},
		            {"mData": "newAmounts"},
		            {"mData": "totalCounts"},
		            {"mData": "totalOrders"},
		            {"mData": "totalAmounts"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var area = $('#area').val();
							var receiveStaffName = $('#receiveStaffName').val();
							var startDate = $('#startDate').val();
							var endDate = $('#endDate').val();
							aoData.push(
										{'name':'area','value':area},
										{'name':'receiveStaffName','value':receiveStaffName},
										{'name':'startDate','value':startDate},
										{'name':'endDate','value':endDate}
									);
							$.ajax({
								"dataType": 'json',
								"type": "POST",
								"url": sSource,
								"data": aoData,
								"success": function(data){
									fnCallback(data);
								}
							})
						}
	});
}

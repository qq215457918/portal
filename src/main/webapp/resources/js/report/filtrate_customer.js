/**
 * 文件名：filtrate_customer.js
 * 用途：筛选客户类型列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-15
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
		$('#filtrateCustomer').dataTable().fnDraw();
	});
	
	// 所属机构变化事件
	$("#area").change(function(){
		// 查询数据
		$('#filtrateCustomer').dataTable().fnDraw();
	});
	
	/*// 返回上一页
	$("#back").click(function(){
		window.history.back(-1);
	});*/
	
	// 导出功能
	$("#export").click(function(){
		alert("暂时还没有做......");
	});

});

// 初始获取数据
function initData() {
	$("#filtrateCustomer").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : 10,// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxFiltrateCustomers", // 地址
		"aoColumns": [ 
		            {"mData": "type"},
		            {"mData": "visitCount"},
		            {"mData": "outOrderCount"},             
		            {"mData": "outPrices"},
		            {"mData": "outRate"},
		            {"mData": "totalPerforPercentage"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							
							// 隐藏表格的分页等
							$("#filtrateCustomer_length").hide();
							$("#filtrateCustomer_info").hide();
							$("#filtrateCustomer_paginate").hide();
							
							var area = $('#area').val();
							var startDate = $('#startDate').val();
							var endDate = $('#endDate').val();
							aoData.push({'name':'area','value':area},{'name':'startDate','value':startDate},{'name':'endDate','value':endDate});
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

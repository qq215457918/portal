/**
 * 文件名：receive_arealist
 * 用途：接待统计列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-04
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startReportDate').datepicker({format: 'yyyy-mm-dd'});
	$('#endReportDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#receiveCustomer').dataTable().fnDraw();
	});
	
	// 接待人输入框回车事件
	$("#receiverStaffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#receiveCustomer').dataTable().fnDraw();
		}
	});
	
	// 接待人所属区域变化事件
	$("#receiverArea").change(function(){
		// 清空接待人查询条件
		$("#receiverStaffName").val('');
		// 查询数据
		$('#receiveCustomer').dataTable().fnDraw();
	});
	
	// 返回上一页
	$("#back").click(function(){
		window.history.back(-1);
	});
	
	// 导出功能
	$("#export").click(function(){
		alert("暂时还没有做......");
	});

});

// 初始获取数据
function initData() {
	$("#receiveCustomer").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='receiveCustomer_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxReceiveAreaList", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号         
		            {"mData": "receiverStaffName"},
		            {"mData": "lockedCounts"},
		            {"mData": "finishOrderCounts"},             
		            {"mData": "roadshowCounts"},
		            {"mData": "repeatCounts"},
		            {"mData": "newCounts"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var receiverArea = $('#receiverArea').val();
							var receiverStaffName = $('#receiverStaffName').val();
							var startReportDate = $('#startReportDate').val();
							var endReportDate = $('#endReportDate').val();
							aoData.push({'name':'receiverArea','value':receiverArea},{'name':'receiverStaffName','value':receiverStaffName},{'name':'startReportDate','value':startReportDate},{'name':'endReportDate','value':endReportDate});
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

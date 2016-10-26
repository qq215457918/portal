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
	$('#queryDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#inquiryTable').dataTable().fnDraw();
	});
	
	// 接待人输入框回车事件
	$("#receiverStaffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#inquiryTable').dataTable().fnDraw();
		}
	});

	
	// 返回上一页
	$("#back").click(function(){
		window.history.back(-1);
	});
	

});

// 初始获取数据
function initData() {
	$("#inquiryTable").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='inquiryTable_length']").val(),// 每页显示行数
		"iDisplayLength" : 10,
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "reception/query", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号         
		            {"mData": "customerName"},
		            {"mData": "receiverStaffName"},
		            {"mData": "phoneStaffName"},             
		            {"mData": "startTime"},
		            {"mData": "endTime"},
		            {"mData": "createDate"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {//查询项
							var receiverStaffName = $('#receiverStaffName').val();
							var startReportDate = $('#startReportDate').val();
							var endReportDate = $('#endReportDate').val();  //isReceiver
							var isReceiver = $('#isReceiver').is(":checked");
							aoData.push({'name':'isReceiver','value':isReceiver},{'name':'receiverStaffName','value':receiverStaffName},{'name':'startReportDate','value':startReportDate},{'name':'endReportDate','value':endReportDate});
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

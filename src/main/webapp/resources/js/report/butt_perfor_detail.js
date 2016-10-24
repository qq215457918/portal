/**
 * 文件名：butt_perfor_detail.js
 * 用途：展厅客服对接业绩相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-24
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
		$('#buttPerforDetail').dataTable().fnDraw();
	});
	
	// 客服输入框回车事件
	$("#viewPhoneStaffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#buttPerforDetail').dataTable().fnDraw();
		}
	});
	// 接待输入框回车事件
	$("#viewReceiveStaffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#buttPerforDetail').dataTable().fnDraw();
		}
	});
	
	// 所属机构变化事件
	$("#viewPhoneStaffGroupName").change(function(){
		// 清空接待和客服的查询条件
		$("#viewPhoneStaffName").val('');
		$("#viewReceiveStaffName").val('');
		// 查询数据
		$('#buttPerforDetail').dataTable().fnDraw();
	});
	
	// 导出功能
	$("#export").click(function(){
		alert("暂时还没有做......");
	});

});

// 初始获取数据
function initData() {
	$("#buttPerforDetail").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='buttPerforDetail_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxButtPerforDetail", // 地址
		"aoColumns": [ 
		            {"mData": "phoneStaffName"},
		            {"mData": "receiveStaffName"},
		            {"mData": "phoneStaffGroupName"},             
		            {"mData": "receiveFinishedCounts"},
		            {"mData": "outOrdersOfFinished"},
		            {"mData": "outOrderRateOfFinished"},
		            {"mData": "performanceOfFinished"},
		            {"mData": "orderAvgOfFinished"},
		            {"mData": "pieceAvgOfFinished"},             
		            {"mData": "receiveLockedCounts"},
		            {"mData": "outOrdersOfLocked"},
		            {"mData": "outOrderRateOfLocked"},
		            {"mData": "performanceOfLocked"},
		            {"mData": "orderAvgOfLocked"},
		            {"mData": "pieceAvgOfLocked"},             
		            {"mData": "orderAvgOfGoodsCounts"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var viewPhoneStaffGroupName = $('#viewPhoneStaffGroupName').val();
							var viewPhoneStaffName = $('#viewPhoneStaffName').val();
							var viewReceiveStaffName = $('#viewReceiveStaffName').val();
							var startReportDate = $('#startReportDate').val();
							var endReportDate = $('#endReportDate').val();
							aoData.push(
										{'name':'viewPhoneStaffGroupName','value':viewPhoneStaffGroupName},
										{'name':'viewPhoneStaffName','value':viewPhoneStaffName},
										{'name':'viewReceiveStaffName','value':viewReceiveStaffName},
										{'name':'startReportDate','value':startReportDate},
										{'name':'endReportDate','value':endReportDate}
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

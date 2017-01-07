/**
 * 文件名：credit_card_detail
 * 用途：当日刷卡定金明细列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-12-11
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#creditCard').dataTable().fnDraw();
	});
	
	// 接待人所属区域变化事件
	$("#receiverArea").change(function(){
		// 查询数据
		$('#creditCard').dataTable().fnDraw();
	});
	
	// 导出功能
	$("#export").click(function(){
		var area = $('#receiverArea').val();
		var startDate = $('#startDate').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		var exportHtml = '<form id="exportExcel" action="reportExport/exportCreditCard" style="display:none;">' +
							'<input type="hidden" name="area" value="' + area + '"/>' +
							'<input type="hidden" name="startDate" value="' + startDate + '"/>';
						'</form>';
		$('body').append(exportHtml);
		$('#exportExcel').submit();
	});

});

// 初始获取数据
function initData() {
	$("#creditCard").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='creditCard_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxCreditCardDepositDetail", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号         
		            {"mData": "paymentAccountName"},
		            {"mData": "orderNumber"},
		            {"mData": "payAmount"},             
		            {"mData": "payAmountActual"},
		            {"mData": "poundage"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var area = $('#receiverArea').val();
							var startDate = $('#startDate').val();
							aoData.push({'name':'area','value':area},{'name':'startDate','value':startDate});
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

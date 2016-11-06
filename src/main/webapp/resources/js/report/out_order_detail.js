/**
 * 文件名：out_order_detail
 * 用途：登门出单详情列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-18
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#outOrderDetail').dataTable().fnDraw();
	});
	
	// 产品名称输入框回车事件
	$("#goodName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#outOrderDetail').dataTable().fnDraw();
		}
	});
	
	// 返回上一页
	$("#back").click(function(){
		window.history.back(-1);
	});
	
});

// 初始获取数据
function initData() {
	$("#outOrderDetail").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='outOrderDetail_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxOutOrderDetail", // 地址
		"aoColumns": [ 
		            {"mData": "createDate"},
		            {"mData": "goodName"},
		            {"mData": "goodSortName"},             
		            {"mData": "price"},
		            {"mData": "amount"},
		            {"mData": "totalPrice"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var customerId = $('#customerId').val();
							var goodName = $('#goodName').val();
							var startDate = $('#startDate').val();
							var endDate = $('#endDate').val();
							aoData.push(
										{'name':'customerId','value':customerId},
										{'name':'goodName','value':goodName},
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

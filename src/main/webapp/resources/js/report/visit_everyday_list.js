// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#visitDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#visitCustomer').dataTable().fnDraw();
	});
	
	// 客户输入框回车事件
	$("#customerName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#visitCustomer').dataTable().fnDraw();
		}
	});
	// 客服输入框回车事件
	$("#customServiceName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#visitCustomer').dataTable().fnDraw();
		}
	});
	// 接待人输入框回车事件
	$("#receiverStaffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#visitCustomer').dataTable().fnDraw();
		}
	});
	
	// 接待人所属区域变化事件
	$("#customerArea").change(function(){
		// 清空接待人查询条件
		$("#receiverStaffName").val('');
		// 查询数据
		$('#visitCustomer').dataTable().fnDraw();
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
	$("#visitCustomer").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='visitCustomer_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxVisitEveryDayList", // 地址
		"aoColumns": [ 
		            {"mData": "viewVisitDate"},
		            {"mData": "customerName"},
		            {"mData": "customServiceName"},
		            {"mData": "receiverStaffName"},
		            {"mData": "viewExercise"},
		            {"mData": "viewCustomerType"},
		            {"mData": "transactionAmount"}, 
		            {"mData": "goodsName"},             
		            {"mData": "viewOutOrIndent"},
		            {"mData": "remark"}
		           ],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var customerArea = $('#customerArea').val();
							var customerName = $('#customerName').val();
							var customerType = $('#customerType').val();
							var exercise = $('#exercise').val();
							var receiverStaffName = $('#receiverStaffName').val();
							var customServiceName = $('#customServiceName').val();
							var visitDate = $('#visitDate').val();
							aoData.push(
										{'name':'customerArea','value':customerArea},
										{'name':'customerName','value':customerName},
										{'name':'customerType','value':customerType},
										{'name':'exercise','value':exercise},
										{'name':'receiverStaffName','value':receiverStaffName},
										{'name':'customServiceName','value':customServiceName},
										{'name':'visitDate','value':visitDate}
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

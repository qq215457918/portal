/**
 * 文件名：visit_out_order.js
 * 用途：登门出单统计列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-17
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
		$('#visitOutOrder').dataTable().fnDraw();
	});
	
	// 客户姓名输入框回车事件
	$("#customerName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			$('#visitOutOrder').dataTable().fnDraw();
		}
	});
	
	// 所属机构变化事件
	$("#area").change(function(){
		// 查询数据
		$('#visitOutOrder').dataTable().fnDraw();
	});
	
});

// 初始获取数据
function initData() {
	$("#visitOutOrder").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='visitOutOrder_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxVisitAndOutOrder", // 地址
		"aoColumns": [ 
		            {"mData": "customerId"},
		            {"mData": "createDate"},
		            {"mData": "startTime"},
		            {"mData": "endTime"},
		            {"mData": "totalTime"},
		            {"mData": "customerName"},             
		            {"mData": "type"},
		            {"mData": "phone"},
		            {"mData": "area"},
		            {"mData": "birthday"},             
		            {"mData": "vipCard"},
		            {"mData": "relationName"},
		            {"mData": "phoneStaffName"},
		            {"mData": "receiverStaffName"},
		            {"mData": "orderCount"}
		           ],
        // 自定义最后一列的字段内容, 格式化为a标签；并将第一列客户ID隐藏
   		"aoColumnDefs": [{"aTargets":[14],"mRender":function(data,type,full){
   			return '<a href="report/toOutOrderDetail?customerId=' + full.customerId + '&startDate=' + $('#startDate').val() + '&endDate=' + $('#endDate').val() + '" data-toggle="modal">' + data + '</a>';
   		}}, {"aTargets":[0],"visible": false}],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var area = $('#area').val();
							var customerName = $('#customerName').val();
							var type = $('#type').val();
							var startDate = $('#startDate').val();
							var endDate = $('#endDate').val();
							aoData.push({'name':'area','value':area},{'name':'customerName','value':customerName},{'name':'type','value':type},{'name':'startDate','value':startDate},{'name':'endDate','value':endDate});
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

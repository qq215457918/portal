/**
 * 文件名：organi_performance
 * 用途：机构业绩相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-10
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
		$('#organiPerformance').dataTable().fnDraw();
	});
	
});

// 初始获取数据
function initData() {
	$("#organiPerformance").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='organiPerformance_length']").val(),// 每页显示行数
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "report/ajaxOrganiPerforList", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号         
		            {"mData": "organizationName"},
		            {"mData": "performance"},
		            {"mData": "newCustomerCounts"}
		           ],
		// 自定义第一列的字段内容, 格式化为a标签
		"aoColumnDefs": [{"aTargets":[1],"mRender":function(data,type,full){
            return '<a href="report/toDeptPerformance?organiName=' + data + '&startReportDate=' + $('#startReportDate').val() + '&endReportDate=' + $('#endReportDate').val() + '" data-toggle="modal">' + data + '</a>';
        }}],
		"fnServerData": function (sSource, aoData, fnCallback) {
							var startReportDate = $('#startReportDate').val();
							var endReportDate = $('#endReportDate').val();
							aoData.push({'name':'startReportDate','value':startReportDate},{'name':'endReportDate','value':endReportDate});
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

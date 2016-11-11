// base:项目根路径
var base;

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#queryDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#depositTable').dataTable().fnDraw();
	});
});

// 初始获取数据
function initData() {
	$("#depositTable").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='depositTable_length']").val(),// 每页显示行数
		"iDisplayLength" : 10,
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "deposit/query", // 地址
		"aoColumns": [  
		            {"mData": "receiverStaffName"},
		            {"data": "orderDetailInfoList",
						"render": function(data, type, full) {
							var result = "";
							$.each(data,function(i, value) {
								result += "商品名称：" +  value.goodName+"--商品数量：" + value.amount +"</br>";
				   			});
							return result;
						}
					},
		            {"mData": "payPrice"},      
		            {"mData": "createDateString"},
		            {"mData": "depositPrice"},
		            {"data": "id",
						"render": function(data, type, full) {
							return "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='cancelDeposit("+data+");'>  撤销定金   </button>"+
							      "<button class='btn btn-xs btn-warning' id='pId"+data+"' onclick='payDeposit("+data+");'>  支付定金   </button>";
						}
					}
		           ],   	           
		"fnServerData": function (sSource, aoData, fnCallback) {//查询项
					var goodCode = $('#goodCode').val();
					var goodName = $('#goodName').val();
					var lprice = $('#lprice').val();
					var hprice = $('#hprice').val(); 
					aoData.push({'name':'goodCode','value':goodCode},{'name':'goodName','value':goodName},
					{'name':'lprice','value':lprice},{'name':'hprice','value':hprice});							
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

/**
 * 撤销定金
 * @param orderId
 * @returns
 */
function cancelDeposit(orderId){
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base+"deposit/cancel",
		"data": {
			"orderId":orderId
		},
		"success": function(data){
			alert("撤销定金成功，请到财务处进行退款业务");
			$('#depositTable').dataTable().fnDraw();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}
	})
}

/**
 * 支付定金中的余款
 * @returns
 */
function payDeposit(orderId){
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base+"deposit/pay",
		"data": {
			"orderId":orderId
		},
		"success": function(data){
			$('#depositTable').dataTable().fnDraw();
			alert("余款信息已经到达财务，请到财务处进行付款");
		}
	})
}

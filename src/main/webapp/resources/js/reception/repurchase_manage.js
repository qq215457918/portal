// base:项目根路径
var base;

$(function() {
	base = $("base").attr('href');
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#orderTable').dataTable().fnDraw();
	});
});

// 初始获取数据
function initData() {
	$("#orderTable").dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true,	 //分页
		"sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
		"bLengthChange" : true,// 每页显示记录数
		"iDisplayLength" : $("select[name='orderTable_length']").val(),// 每页显示行数
		"iDisplayLength" : 10,
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bInfo" : true,		 // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
		"bRetrieve": true,
		"sAjaxSource": "repurchase/list", // 地址
		"aoColumns": [  
		            {"mData": "id"},
		            {"data": "orderDetailInfoList",
						"render": function(data, type, full) {
							var result = "";
							$.each(data,function(i, value) {
								result += "商品名称：" +  value.goodName+" -- 商品数量：" + value.amount +"</br>";
				   			});
							return result;
						}
					},
		            {"mData": "payPrice"},      
		            {"mData": "createDateString"},
		            {"mData": "receiverStaffName"},
		            {"data": "status",
						"render": function(data, type, full) {
							var type;
							switch (data)
							{
							case "0": type ="未支付";break;
							case "1": type ="已支付";break;
							case "2": type ="已出库";break;
							case "4": type ="已完成";break;
							case "5": type ="待审批";break;
							case "6": type ="回购待确认";break;
						 }
							return type;
						}
					  },
		            {"data": "id",
						"render": function(data, type, full) {
						   var result = "";
						   var checkButton = "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='checkOrder("+data+");'>确认 </button>";
						   var status = full.status; 
						   if(status==6){
							   result = checkButton;
						   }
						   return result;
						 }
					  }
		           ],   	           
		"fnServerData": function (sSource, aoData, fnCallback) {//查询项
				var goodName = $('#goodName').val();
				var staffName = $('#staffName').val();
				var typeList = ""; 
				$("input[name=checkbox]:checked").each(function(){ 
				    var val = $(this).val();
				    typeList +=val+"," ;
				});
				typeList = typeList.substr(0,typeList.length-1);
				aoData.push({'name':'goodName','value':goodName},
				{'name':'staffName','value':staffName},{'name':'typeList','value':typeList});							
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
 * 回购确认
 * @param orderId
 * @returns
 */
function checkOrder(orderId){
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base+"repurchase/check",
		"data": {
			"orderId":orderId
		},
		"success": function(data){
			alert("商品回购已经通知库房，请到库房进行回购业务");
			$('#orderTable').dataTable().fnDraw();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}
	})
}

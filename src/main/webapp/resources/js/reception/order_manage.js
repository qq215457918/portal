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
		"sAjaxSource": "order/manage/query", // 地址
		"aoColumns": [  
				 	{"mData": "orderNumber"},
		            {"mData": "goodName"},
		            {"mData": "amount"}, 
		            {"data": "price",
						"render": function(data, type, full) {
							//var type;
							return data + "元";
						}
					  },
		            {"mData": "createDateString"},
		            {"mData": "receiverStaffName"},
		            {"data": "orderType",
						"render": function(data, type, full) {
							var type;
							switch (data)
							{
							case "1": type ="正常";break;
							case "2": type ="退货";break;
							//case "3": type ="换货";break; 去掉换货业务。
							default:type =" ";break;
						 }
							return type;
						}
					  },
					  //deleteFlag
					  {"data": "deleteFlag",
							"render": function(data, type, full) {
								var type;
								switch (data)
								{
								case "0": type ="正常";break;
								case "1": type ="已删除";break;
								default:type =" ";break;
							 }
								return type;
							}
						  },
		            {"data": "id",
						"render": function(data, type, full) {
						   var result = "";
						   var returnsButton = "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='returnsOrder("+data+");'>退货 </button>";
						   var orderType = full.orderType; 
						   if(orderType==1){
							   result = returnsButton;
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
 * 商品退货
 * @param orderId
 * @returns
 */
function returnsOrder(detailId){
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base+"order/manage/return",
		"data": {
			"detailId":detailId
		},
		"success": function(data){
			alert("商品退货已经通知库房，请到库房进行退货业务");
			$('#orderTable').dataTable().fnDraw();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}
	})
}

/**
 * 商品换货
 * @returns
 */
/*function replaceOrder(orderId){
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base+"order/manage/replace",
		"data": {
			"orderId":orderId
		},
		"success": function(data){
			$('#orderTable').dataTable().fnDraw();
			alert("商品换货已经通知库房，请到库房进行换货业务");
		}
	})
}*/

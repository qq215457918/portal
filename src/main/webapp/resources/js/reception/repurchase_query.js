// base:项目根路径
var base;
var table;

$(function() {
	base = $("base").attr('href');
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		$('#orderTable').dataTable().fnDraw();
	});
	
	// 特殊审批
	$("#specialConfirm").click(function(){
		var count = $('#specialCount').val();
		var reason = $('#specialReason').val();
		var goodName = $('#specialGoodsName').val();
		var detailId = $('#specialGoodsId').val();
		var price = $('#specialPrice').val();
//		var price = $('#specialPriceOld').val();
//		var customerId = $('#customerId').val();
		$.ajax({
			method : "POST",
			url : base+"/repurchase/special",
			data : {
				"reason" : reason,
				"count" : count,
				"goodName" : goodName,	
				"detailId" : detailId,
				"price" : price
//				"customerId":customerId
			},
			dataType : "JSON",
			success : function(data) {
				if(data.result==true){
					alert("提交成功，等待审批人进行审批");
					$('#specialModal').modal('hide');
					$('#orderTable').dataTable().fnDraw();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('XMLHttpRequest.status :' + XMLHttpRequest.status);
				console.log('XMLHttpRequest.readyState :'
						+ XMLHttpRequest.readyState);
				console.log('textStatus:' + textStatus);
			}
		});
	});
	
	// 一般审批
	$("#normalConfirm").click(function(){
		var count = $('#normalCount').val();
		var goodName = $('#normalGoodsName').val();
		var detailId = $('#normalGoodsId').val();
		var price = $('#normalPrice').val();
		$.ajax({
			method : "POST",
			url : base+"/repurchase/normal",
			data : {
				"count" : count,
				"goodName" : goodName,	
				"detailId" : detailId,
				"price" : price
//				"customerId":customerId
			},
			dataType : "JSON",
			success : function(data) {
				if(data.result==true){
					alert("提交成功，请到库房提交回购商品");
					$('#normalModal').modal('hide');
					$('#orderTable').dataTable().fnDraw();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('XMLHttpRequest.status :' + XMLHttpRequest.status);
				console.log('XMLHttpRequest.readyState :'
						+ XMLHttpRequest.readyState);
				console.log('textStatus:' + textStatus);
			}
		});
	});
});

// 初始获取数据
function initData() {
	table = $("#orderTable").dataTable({
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
		"sAjaxSource": "repurchase/query", // 地址
		"aoColumns": [  
				 	{"mData": "orderNumber"},	 	
		            {"mData": "goodName"},
		            {"mData": "amount"}, 
		            {"data": "price",
						"render": function(data, type, full) {
						//原有金额	
							var type;
							if(full.oldPrice==''){
								type = data
							}else{
								type = full.oldPrice
							}
							return type + "元";
						}
					  },
					  {"data": "price",
							"render": function(data, type, full) {
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
							case "5": type ="已回购";break;
							case "7": type ="待审批";break;
							default:type =" ";break;
						 }
							return type;
						}
					  },
					  {"data": "status",
							"render": function(data, type, full) {
								var type;
								switch (data)
								{
								case "0": type ="未支付";break;
								case "1": type ="已支付";break;
								case "2": type ="已出库";break;
								case "3": type ="文交所已审核";break;
								case "4": type ="已完成";break;
								case "5": type ="待审批";break;
								default:type =" ";break;
							 }
								return type;
							}
						  },
		            {"data": "id",
						"render": function(data, type, full) {
						   var result = "";
						   var normalButton = "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='normal(&quot;"+data+"&quot;,&quot;"+full.goodName+"&quot;,&quot;"+full.amount+"&quot;,&quot;"+full.price+"&quot;);'>正常回购</button>";//customerId
						   var specialButton = "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='special(&quot;"+data+"&quot;,&quot;"+full.goodName+"&quot;,&quot;"+full.amount+"&quot;,&quot;"+full.price+"&quot;);'>特殊回购</button>";
						   var orderType = full.orderType; 
						   if(orderType==1){
							   result = normalButton + specialButton;
						   }
							return result;
						 }
					  }
		           ],   	           
		"fnServerData": function (sSource, aoData, fnCallback) {//查询项
				var goodName = $('#goodName').val();
				aoData.push({'name':'goodName','value':goodName});							
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
 * 正常回购流程
 * @param orderId
 * @returns
 */
function normal(detailId , goodName , amount, price ){
	$('#normalGoodsName').val(goodName);
	$('#normalCount').val(amount);
	$('#normalPrice').val(price);
	$('#normalPriceOld').val(price);
	$('#normalGoodsId').val(detailId);
	//customerId
//	$('#customerId').val(customerId);
	$('#normalModal').modal('show');
}

/**
 * 特殊回购流程
 * 弹出需要审批页面 输入审批原因和金额
 * @param orderId
 * @returns
 */
function special(detailId , goodName , amount, price ){
	$('#specialGoodsName').val(goodName);
	$('#specialCount').val(amount);
	$('#specialPrice').val(price);
	$('#specialPriceOld').val(price);
	$('#specialGoodsId').val(detailId);
	//customerId
//	$('#customerId').val(customerId);
	$('#specialModal').modal('show');
}
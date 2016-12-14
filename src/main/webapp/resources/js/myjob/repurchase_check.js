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
	$("#appConfirm").click(function(){
		var count = $('#applyCount').val();
		var reason = $('#applyReason').val();
		var goodName = $('#applyGoodsName').val();
		var detailId = $('#applyGoodsId').val();
		var price = $('#applyPrice').val();
		var customerId = $('#customerId').val();
		//window.location.href=base+"/present/review?reason="+reason;
		$.ajax({
			method : "POST",
			url : base+"/repurchase/special",
			data : {
				"reason" : reason,
				"count" : count,
				"goodName" : goodName,	
				"detailId" : detailId,
				"price" : price,
				"customerId":customerId
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
				 	{"mData": "customerName"},
				 	{"mData": "customerPhone"},			 	
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
		            {"mData": "remarks"},
		            {"data": "id",
						"render": function(data, type, full) {
						   var result = "";
						   var specialButton = "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='special(&quot;"+data+"&quot;,&quot;"+full.goodName+"&quot;);'>特殊回购</button>";
							return specialButton;
						 }
					  }
		           ],   	           
		"fnServerData": function (sSource, aoData, fnCallback) {//查询项
				var goodName = $('#goodName').val();
				var customerName = $('#customerName').val();
//				var typeList = ""; 
//				$("input[name=checkbox]:checked").each(function(){ 
//				    var val = $(this).val();
//				    typeList +=val+"," ;
//				});
//				typeList = typeList.substr(0,typeList.length-1);
				aoData.push({'name':'goodName','value':goodName},{'name':'customerName','value':customerName});							
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
function normal(detailId){
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base+"repurchase/normal",
		"data": {
			"detailId":detailId
		},
		"success": function(data){
			alert("回购信息已经修改，待客户登门即可进行回购确认操作");
			$('#orderTable').dataTable().fnDraw();
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("error");
		}
	})
}

/**
 * 特殊回购流程
 * 弹出需要审批页面 输入审批原因和金额
 * @param orderId
 * @returns
 */
function special(detailId , goodName , amount, price ,customerId){
	$('#applyGoodsName').val(goodName);
	$('#applyCount').val(amount);
	$('#applyPrice').val(price);
	$('#applyGoodsId').val(detailId);
	//customerId
	$('#customerId').val(customerId);
	$('#specialModal').modal('show');
}
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
	
	// 审批确认
	$("#appConfirm").click(function(){
		var orderId = $('#orderId').val();
		$.ajax({
			method : "POST",
			url : base+"/present/confirm",
			data : {
				"orderId" : orderId
			},
			dataType : "JSON",
			success : function(data) {
				if(data.result==true){
					alert("审批成功，客户可以去库房领取赠品");
					$('#checkModal').modal('hide');
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
		"sAjaxSource": "present/check", // 地址
		"aoColumns": [  
				 	{"mData": "orderNumber"},
				 	{"mData": "customerName"},			 	
		            {"mData": "goodName"},
		            {"mData": "amount"}, 
		            {"mData": "createDateString"},
		            {"mData": "receiverStaffName"},
		            {"mData": "remarks"},
		            {"data": "financeFlag",
						"render": function(data, type, full) {
							var type;
							switch (data)
							{
							case "0": type ="未审批";break;
							case "1": type ="审核通过";break;
							default:type =" ";break;
						 }
							return type;
						}
					  },
		            {"data": "orderNumber",
						"render": function(data, type, full) {
						   var result = "";
						   var specialButton = "<button class='btn btn-xs btn-warning' id='cId"+data+"' onclick='approved(&quot;"+data+"&quot;,&quot;"+full.receiverStaffName+"&quot;,&quot;"+full.customerName+"&quot;,&quot;"+full.goodName+"&quot;,&quot;"+full.amount+"&quot;);'>审批确认</button>";
							if(full.financeFlag=='0'){
								return specialButton;
							}
						   
						 }
					  }
		           ],   	           
		"fnServerData": function (sSource, aoData, fnCallback) {//查询项
				var goodName = $('#goodName').val();
				var customerName = $('#customerName').val();
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
 * 审批确认
 * orderId 礼品名称，数量
 * @param orderId
 * @returns
 */
function approved(orderId ,receiverStaffName,customerName, goodName ,amount){
	alert(orderId)
	$('#orderId').val(orderId);
	$('#applyGoodsName').val(goodName);
	$('#customerName').val(customerName);
	$('#receiverStaffName').val(receiverStaffName);
	//applyCount
	$('#applyCount').val(amount);
	$('#checkModal').modal('show');
}
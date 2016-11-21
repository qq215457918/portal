$(document).ready(function(){
	initData();
	
	$('#searchList').click(function(){
		if('' == $('#phone').val() &&
			'' == $('#phoneStage').val() &&
			'' == $('#type option:selected').val() &&
			'' == $('#updateDate').val()){
			return;
		}
		$('#orderModify').dataTable().fnDraw();
	});
}); 

$(document).on('click', '#modifyItem', function () {
	var orderId = $(this).attr('data-customer-id');
	var customerPhone = $(this).attr('data-customer-phone');
	var payPrice = $(this).attr('data-customer-price');
	var orderType = $(this).attr('data-customer-type');
	
	$('#orderModifyForm input[name=orderId]').val(orderId);
	$('#orderModifyForm input[name=customerPhone]').val(customerPhone);
	$('#orderModifyForm input[name=payPrice]').val(payPrice);
	if(orderType == 1){
		$('#orderModifyForm input[name=orderType][value=1]').attr("checked", true); 
	}else {
		$('#orderModifyForm input[name=orderType][value=2]').attr("checked", true); 
	}
	
});

function initData(){
	$('#orderModify').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": true, //分页
		"bInfo": false,//页脚信息
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "order/orderModifyList", // 地址
		"aoColumns": [ 
			        {"mData": null, "target": 0},	//序列号   
		            {"mData": "customerName"},
		            {"mData": "customerPhone"},
		            {"mData": "goodsName"},
		            {"mData": "payPrice"}, 
		            {"mData": "orderTypeName"},    
		            {"mData": ""},             
		           ],
       "columnDefs" : [ {
			"render" : function(data, type, row) {
				return '<a href="#detailModel" data-toggle="modal" ' + 
					'data-customer-id="' + row.id  + '" data-customer-type="' + row.orderType  + '" ' + 
					'data-customer-phone="' + row.customerPhone  + '" ' + 'data-customer-price="' + row.payPrice  + '" ' +
					'id="modifyItem">修改</a>';
			},
			"targets" : 6
			}],
		"fnDrawCallback": function(){
   			var api = this.api();
   			api.column(0).nodes().each(function(cell, i) {
   				cell.innerHTML =  i + 1;
   			});
   		},
		"fnServerData": function (sSource, aoData, fnCallback) {
							var goodsName = $('#goodsName').val();
							var customerName = $('#customerName').val();
							var orderType = $('#listInfo input[name=orderType]:checked').val();
							aoData.push({'name':'goodsName','value':goodsName},{'name':'customerName','value':customerName},
									{'name':'orderType','value':orderType});
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
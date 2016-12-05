$(document).ready(function(){
	initData();
	
	$('#searchList').click(function(){
		if('' == $('#orderNumber').val()){
			return;
		}
		$('#financeOrderExam').dataTable().fnDraw();
	});
	
	$('#outNoTake').click(function(){
		$('#hasPayInfo').show();
//		$('#outgoingInfo').jqprint();
		$("#hasPayInfo").print({
	        globalStyles: true,
	        mediaPrint: false,
	        stylesheet: null,
	        noPrintSelector: ".no-print",
	        iframe: true,
	        append: null,
	        prepend: null,
	        manuallyCopyFormValues: true,
	        deferred: $.Deferred(),
	        timeout: 750,
	        title: null,
	        doctype: '<!doctype html>'
		});
	});
	
	$('#receiveMoney').click(function(){
		$('#receiveMoneyInfo').show();
//		$('#outgoingInfo').jqprint();
		$("#receiveMoneyInfo").print({
	        globalStyles: true,
	        mediaPrint: false,
	        stylesheet: null,
	        noPrintSelector: ".no-print",
	        iframe: true,
	        append: null,
	        prepend: null,
	        manuallyCopyFormValues: true,
	        deferred: $.Deferred(),
	        timeout: 750,
	        title: null,
	        doctype: '<!doctype html>'
		});
	});
	
	$('#printInfo').on('hidden.bs.modal', function () {
		$('#hasPayInfo').hide();
		$('#receiveMoneyInfo').hide();
	});
}); 

$(document).on('click', '#confirmReceipt', function () { 
	var operate = $(this).attr('data-operate-id');
	$.ajax({
		"dataType": 'text',
		"type": "POST",
		"url": 'workflow/updateOrderInfo',
		"data": {
			'orderId': $(this).attr('data-order-id'),
			'operate': operate
		},
		"success": function(data){
			if(1 == operate){
				alert("已确认收款");
			}else{
				alert("更新成功,待仓库审核");
			}
			
			$('#financeOrderExam').dataTable().fnDraw();
		}
	})
});


$(document).on('click', '#orderDetailInfo', function () { 
	window.location.href = 'order/orderModifyIndex?orderId='+$(this).attr('data-order-id');
});

$(document).on('click', '#toPrint', function () { 
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": 'workflow/selectOrderInfoById',
		"data": {
			'orderId': $(this).attr('data-order-id')
		},
		"success": function(data){
			if(data.length > 0){
				var remark = data[0].remark;
				var customerName = data[0].customerName;
				var phoneStaffName = data[0].phoneStaffName;
				var receiverStaffName = data[0].receiverStaffName;
				var actualPrice = data[0].actualPrice;
				var payPrice = data[0].payPrice;
				var payPriceCN = data[0].payPriceCN;
				var today = data[0].today;
				var payTypeName = data[0].payTypeName;
				var outgoingHtml = '';
				var hasPayInfoHtml = '';
				
				for(var i in data){
					outgoingHtml += '<tr><td>' + data[i].goodName + '</td>';
					outgoingHtml += '<td>' + data[i].amount + '</td>';
					outgoingHtml += '<td>' + data[i].price + '</td>';
					outgoingHtml += '<td>' + data[i].price*data[i].amount + '</td>';
					outgoingHtml += '<td>' + payTypeName + '</td>';
					outgoingHtml += '<td></td>';
					outgoingHtml += '<td></td></tr>';
					
					hasPayInfoHtml += '<tr><td>' + data[i].goodName + '</td>';
					hasPayInfoHtml += '<td>' + data[i].amount + '</td>';
					hasPayInfoHtml += '<td>件</td>';
					hasPayInfoHtml += '<td>' + actualPrice + '</td></tr>';
				} 
				
				$('#outgoingInfo span[name=customerName]').html('客户：' + customerName);
				$('#outgoingInfo span[name=today]').html('日期：' + today);
				$('#outgoingInfo tbody[name=detail]').html(outgoingHtml);
				$('#outgoingInfo td[name=remark]').html(remark);
				$('#outgoingInfo span[name=receiverStaffName]').html('接待：' + receiverStaffName);
				$('#outgoingInfo span[name=phoneStaffName]').html('客服：' + phoneStaffName);
				
				$('#hasPayInfo span[name=customerName]').html('客户：' + customerName);
				$('#hasPayInfo span[name=today]').html('日期：' + today);
				$('#hasPayInfo tbody[name=detail]').html(hasPayInfoHtml);
				$('#hasPayInfo td[name=remark]').html(remark);
				$('#hasPayInfo span[name=receiverStaffName]').html('接待：' + receiverStaffName);
				$('#hasPayInfo span[name=phoneStaffName]').html('客服：' + phoneStaffName);
			}
		}
	})
});

function commitExam(suggestion){
	$('#examModel inout[name=suggestion]').val(suggestion);
}

function initData(){
	$('#financeOrderExam').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "workflow/selectFinanceOrder", // 地址
		"aoColumns": [ 
			        {"mData": null, "target": 0},	//序列号   
		            {"mData": "orderNumber"},
		            {"mData": "orderTypeName"},
		            {"mData": "payTypeName"}, 
		            {"mData": "payPrice"}, 
		            {"mData": "wareHouseFlagName"}, 
		            {"mData": ""} 
		           ],
       "columnDefs" : [ {
			"render" : function(data, type, row) {
				var operation = '<a data-toggle="modal" data-order-id="' + row.id + '" id="confirmReceipt">确认收款</a>&nbsp;&nbsp;' + 
					'<a href="#printInfo" data-toggle="modal" data-order-id="' + row.id + '" id="toPrint">打印</a>';
				if(row.warehouseFlag == -1){
					operation = '<a data-toggle="modal" data-order-id="' + row.id + '" data-operate-id="1" id="confirmReceipt">确认付款</a>&nbsp;&nbsp;' + 
						'<a href="#printInfo" data-toggle="modal" data-order-id="' + row.id + '" id="toPrint">打印</a>';
				}
				if(row.financeFlag == 1){
					operation = '<a href="javascript:;" data-toggle="modal">已收款</a>&nbsp;&nbsp;' + 
						'<a href="#printInfo" data-toggle="modal" data-order-id="' + row.id + '" id="toPrint">打印</a>';
				}
				if(row.financeFlag == -1){
					operation = '<a href="javascript:;" data-toggle="modal">已付款</a>&nbsp;&nbsp;' + 
						'<a href="#printInfo" data-toggle="modal" data-order-id="' + row.id + '" id="toPrint">打印</a>';
				}
				
				operation += '&nbsp;&nbsp;<a data-toggle="modal" data-order-id="' + row.id + '" id="orderDetailInfo">订单详情</a>';
				
				return operation;
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
							var orderId = $('#orderId').val();
							var orderNumber = $('#orderNumber').val();
							
							aoData.push({'name':'orderId','value':orderId}, {'name':'orderNumber','value':orderNumber});
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
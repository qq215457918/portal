$(document).ready(function(){
	initData();
	
	$('#searchList').click(function(){
		if('' == $('#orderNumber').val()){
			return;
		}
		$('#financeOrderExam').dataTable().fnDraw();
	});
	
	$('#outgoing').click(function(){
		$('#outgoingInfo').show();
//		$('#outgoingInfo').jqprint();
		$("#outgoingInfo").print({
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
		$('#outgoingInfo').hide();
		$('#receiveMoneyInfo').hide();
	});
}); 

$(document).on('click', '#examPass', function () { 
	var _this = $(this);
	$.ajax({
		"dataType": 'text',
		"type": "POST",
		"url": 'workflow/updateOrderInfo',
		"data": {
			'orderId': _this.attr('data-order-id')
		},
		"success": function(data){
			alert("更新成功");
		}
	})
});

$(document).on('click', '#examError', function () { 
	var _this = $(this);
	$.ajax({
		"dataType": 'text',
		"type": "POST",
		"url": 'workflow/updateOrderInfo',
		"data": {
			'orderId': _this.attr('data-order-id'),
			'operate': '1'
		},
		"success": function(data){
			alert("更新成功");
		}
	})
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
				var receiveMoneyHtml = '';
				
				for(var i in data){
					outgoingHtml += '<tr><td>' + data[i].goodName + '</td>';
					outgoingHtml += '<td>' + data[i].amount + '</td>';
					outgoingHtml += '<td>' + data[i].price + '</td>';
					outgoingHtml += '<td>' + data[i].price*data[i].amount + '</td>';
					outgoingHtml += '<td>' + payTypeName + '</td>';
					outgoingHtml += '<td></td>';
					outgoingHtml += '<td></td></tr>';
					
					receiveMoneyHtml += '<tr><td>' + data[i].goodName + '</td>';
					receiveMoneyHtml += '<td>' + data[i].amount + '</td>';
					receiveMoneyHtml += '<td>件</td>';
					receiveMoneyHtml += '<td>' + data[i].price + '</td>';
					receiveMoneyHtml += '<td>' + actualPrice + '</td></tr>';
				} 
				
				$('#outgoingInfo span[name=customerName]').html('客户：' + customerName);
				$('#outgoingInfo span[name=today]').html('日期：' + today);
				$('#outgoingInfo tbody[name=detail]').html(outgoingHtml);
				$('#outgoingInfo td[name=remark]').html(remark);
				$('#outgoingInfo span[name=receiverStaffName]').html('接待：' + receiverStaffName);
				$('#outgoingInfo span[name=phoneStaffName]').html('客服：' + phoneStaffName);
				
				$('#receiveMoneyInfo span[name=customerName]').html('客户：' + customerName);
				$('#receiveMoneyInfo span[name=today]').html('日期：' + today);
					$('#receiveMoneyInfo tbody[name=detail]').html(receiveMoneyHtml);
				$('#receiveMoneyInfo td[name=priceCn]').html(payPriceCN);
				$('#receiveMoneyInfo span[name=receiverStaffName]').html('接待：' + receiverStaffName);
				$('#receiveMoneyInfo span[name=phoneStaffName]').html('客服：' + phoneStaffName);
			}
		}
	})
});

function commitExam(suggestion){
	$('#examModel inout[name=suggestion]').val(suggestion);
}

function initData(){
	$('#financeOrderDate').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "order/orderModifyList", // 地址
		"aoColumns": [ 
			        {"mData": null, "target": 0},	//序列号   
		            {"mData": "orderNumber"},
		            {"mData": "goodsQuantity"},
		            {"mData": "payPrice"}, 
		            {"mData": "receiverStaffName"},    
		            {"mData": "phoneStaffName"},
		            {"mData": "financeDate"},
		            {"mData": ""}		            
		           ],
       "columnDefs" : [ {
			"render" : function(data, type, row) {
				return formatDate(data);
			},
			"targets" : 6
			},
			{
			"render" : function(data, type, row) {
				var operation = '<a data-toggle="modal" data-order-id="' + row.id + '" id="examPass">审核通过</a>' + 
					'<a data-toggle="modal" data-order-id="' + row.id + '" id="examError">审核错误</a>';
				
				if(row.financeFlag == 1){
					operation = '<a data-toggle="modal" data-order-id="' + row.id + '">已审核</a>' + 
					'<a data-toggle="modal" data-order-id="' + row.id + '" id="examError">审核错误</a>';
				}else if(row.financeFlag == -1){
					operation = '<a data-toggle="modal" data-order-id="' + row.id + '">详情</a>';
				}
				
				return operation;
			},
			"targets" : 7
			}],
		"fnDrawCallback": function(){
   			var api = this.api();
   			api.column(0).nodes().each(function(cell, i) {
   				cell.innerHTML =  i + 1;
   			});
   		},
		"fnServerData": function (sSource, aoData, fnCallback) {
							var orderId = $('#orderId').val();
							var financeDate = $('#financeDate').val();
							var userId = $('#hiddenUserId').val();
							
							aoData.push({'name':'orderId','value':orderId}, {'name':'financeDate','value':financeDate}, {'name':'userId','value':userId});
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

function formatDate(data){
	if('' == data || null == data){
		return '';
	}else {
		if('' == data.hours && '' == data.minutes && '' == data.seconds){
			return (data.year + 1900) + '/' + (data.month+1) + '/' + data.date;
		}else {
			return (data.year + 1900) + '/' + (data.month+1) + '/' + data.date + ' ' + data.hours + ':' + data.minutes + ':' +data.seconds;
		}
	}
}
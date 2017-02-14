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
	
	$('#goodsDetailList button').click(function(){
		if(!$(this).attr('data-flag') || $(this).attr('data-flag') == ''){
			return;
		}
		
		var flag = $(this).attr('data-flag');
		var content = '';
		if(1 == flag){
			content = '确定要通过审核吗？';
		}else {
			content = '确定要拒绝通过审核吗？';
		}
		$('#goodsDetailList input[name=cultureFlag]').val(flag);
		if(confirm(content)) {
			$('#updateCivi').submit();
		}
	});
}); 

$(document).on('click', '#orderDetailInfo', function () { 
	$('#goodsDetailList input[name=orderId]').val($(this).attr('data-order-id'));
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": 'workflow/selectOrderDetailById',
		"data": {
			orderId : $(this).attr('data-order-id')
		},
		"success": function(data){
			var odHtml = '';
			var j = 0;
			for(var i in data){
				if(j == 0) {
					$('#goodsDetailList #cultureRemark').val(data[i].remark);
					j++;
				}
				if(data[i].goodType != 2 && data[i].goodType != 3){
					continue;
				}
				odHtml += '<tr><td>';
				odHtml += data[i].goodName
				odHtml += '</td><td>';
				odHtml += data[i].goodId
				odHtml += '</td><td>';
				odHtml += data[i].goodTypeName
				odHtml += '</td><td>';
				odHtml += data[i].amount;
				odHtml += '</td></tr>';
			}
			$('#goodsDetailList tbody').html(odHtml);
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
	$('#civilizationOrderList').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "workflow/selectCivilizationOrder", // 地址
		"aoColumns": [ 
			        {"mData": null, "target": 0},	//序列号   
		            {"mData": "order_number"},
		            {"mData": "orderTypeName"},
		            {"mData": "payTypeName"}, 
		            {"mData": "culture_flag"}, 
		            {"mData": "freeCount"}, 
		            {"mData": "saleCount"}, 
		            {"mData": ""} 
		           ],
       "columnDefs" : [ {
			"render" : function(data, type, row) {
				var operation = '<a href="#goodsDetailList" data-toggle="modal" data-order-id="' + row.id + '" id="orderDetailInfo">详情</a>';
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
							var orderNumber = $('#orderNumber').val();
							
							aoData.push({'name':'orderNumber','value':orderNumber});
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
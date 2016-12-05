$(document).ready(function(){
	initData();
	
	$('#searchCustomer').click(function(){
		if('' == $('#payPriceS').val() &&
			'' == $('#payPriceE').val() &&
			'' == $('#dpd1').val() &&
			'' == $('#dpd2').val()){
			return;
		}
		$('#customerInfo').dataTable().fnDraw();
	});
	
	$('#exportCustomer').click(function(){
		
		var payPriceS = $('#payPriceS').val();
		var payPriceE = $('#payPriceE').val();
		var dpd1 = $('#dpd1').val();
		var dpd2 = $('#dpd2').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '';
		exportHtml += '<form id="exportExcel" action="customerInfo/exportCustomer" style="display:none;">';
		exportHtml += '<input type="hidden" name="payPriceS" value="' + payPriceS + '"/>'
		exportHtml += '<input type="hidden" name="payPriceE" value="' + payPriceE + '"/>'
		exportHtml += '<input type="hidden" name="createDateS" value="' + dpd1 + '"/>'
		exportHtml += '<input type="hidden" name="createDateE" value="' + dpd2 + '"/>'
		exportHtml += '<input type="hidden" name="type" value="3"/>'
		exportHtml += '</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
	});
}); 

$(document).on('click', '#orderDetail', function () { 
	var customerId = $(this).attr('data-customer-id');
	window.location.href = "customerInfo/customerOrderInfoIndex?customerId=" + customerId
});

function initData(){
	$('#customerInfo').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "customerInfo/selectCustomerExList", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号  
		            {"mData": "phoneStaffName"},
		            {"mData": "name"},
		            {"mData": "sexShow"},
		            {"mData": "phoneHidden"},
		            {"mData": "address"},
		            {"mData": "payPrice"},             
		            {"mData": "recentCreateDate"},            
					{"mData": ""}
		           ],
       "columnDefs" : [ {
   			"render" : function(data, type, row) {
   					return formatDate(data);
   				},
   			"targets" : 7
   			}
       ],
       "columnDefs" : [
		{
		"render" : function(data, type, row) {
				return '<a href="javascript:void(0);" data-customer-id="' + row.id + '" id="orderDetail">详情</a>';
			},
		"targets" : 8
		}],
       "fnDrawCallback": function(){
  			var api = this.api();
  			api.column(0).nodes().each(function(cell, i) {
  				cell.innerHTML =  i + 1;
  			});
  		},
		"fnServerData": function (sSource, aoData, fnCallback) {
			var payPriceS = $('#payPriceS').val();
			var payPriceE = $('#payPriceE').val();
			var createDateS = $('#dpd1').val();
			var createDateE = $('#dpd2').val();
			aoData.push({'name':'payPriceS','value':payPriceS},{'name':'payPriceE','value':payPriceE},
					{'name':'type','value':3},{'name':'createDateS','value':createDateS},{'name':'createDateE','value':createDateE});
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
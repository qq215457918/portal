$(document).ready(function(){
	initData();
	
	$('#searchCustomer').click(function(){
		$('#customerInfo').dataTable().fnDraw();
	});
	
	$('#exportCustomer').click(function(){
		
		var phone = $('#phone').val();
		var phoneStage = $('#phoneStage').val();
		var updateDate = $('#updateDate').val();
		var dpd1 = $('#dpd1').val();
		var dpd2 = $('#dpd2').val();
		var exportCount = $('#exportCount').val();
		
		if('' == dpd1 || '' == dpd2){
			alert('请输入上次电联时间！');
			return;
		}
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '';
		exportHtml += '<form id="exportExcel" action="customerInfo/exportCustomer" style="display:none;">';
		exportHtml += '<input type="hidden" name="phone" value="' + phone + '"/>'
		exportHtml += '<input type="hidden" name="phoneStage" value="' + phoneStage + '"/>'
		exportHtml += '<input type="hidden" name="updateDate" value="' + updateDate + '"/>'
		exportHtml += '<input type="hidden" name="startTime" value="' + dpd1 + '"/>'
		exportHtml += '<input type="hidden" name="endTime" value="' + dpd2 + '"/>'
		exportHtml += '<input type="hidden" name="exportCount" value="' + exportCount + '"/>'
		exportHtml += '<input type="hidden" name="type" value="0"/>'
		exportHtml += '</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
	});
}); 

function initData(){
	$('#customerOrderInfo').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "customerInfo/customerOrderInfoList", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号
		            {"mData": "receiverStaffName"},
		            {"mData": "goodsName"},
		            {"mData": "payPrice"},
		            {"mData": "actualPrice"},
		            {"mData": "createDate"}, 
		            {"mData": "statusName"},            
					{"mData": "payTypeName"}
		           ],
		"columnDefs" : [ {
		   			"render" : function(data, type, row) {
		   					return formatDate(data);
		   				},
		   			"targets" : 5
		   			}],
       "fnDrawCallback": function(){
			var api = this.api();
			api.column(0).nodes().each(function(cell, i) {
				cell.innerHTML =  i + 1;
			});
		},
		"fnServerData": function (sSource, aoData, fnCallback) {
							var customerId = $('#customerId').val();
							aoData.push({'name':'customerId','value':customerId});
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

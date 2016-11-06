$(document).ready(function(){
	initData();
	
	$('#searchCustomer').click(function(){
		if('' == $('#phone').val() &&
			'' == $('#phoneStage').val() &&
			'' == $('#type option:selected').val() &&
			'' == $('#updateDate').val()){
			return;
		}
		$('#customerInfo').dataTable().fnDraw();
	});
	
	$('#exportCustomer').click(function(){
		var phone = $('#phone').val();
		var phoneStage = $('#phoneStage').val();
		var updateDate = $('#updateDate').val();
		var dpd1 = $('#dpd1').val();
		var dpd2 = $('#dpd2').val();
		var exportCount = $('#exportCount').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '';
		exportHtml += '<form id="exportExcel" action="customerInfo/exportCustomer" style="display:none;">';
		exportHtml += '<input type="hidden" name="phone" value="' + phone + '"/>'
		exportHtml += '<input type="hidden" name="phoneStage" value="' + phoneStage + '"/>'
		exportHtml += '<input type="hidden" name="updateDate" value="' + updateDate + '"/>'
		exportHtml += '<input type="hidden" name="dpd1" value="' + dpd1 + '"/>'
		exportHtml += '<input type="hidden" name="dpd2" value="' + dpd2 + '"/>'
		exportHtml += '<input type="hidden" name="exportCount" value="' + exportCount + '"/>'
		exportHtml += '<input type="hidden" name="type" value="4"/>'
		exportHtml += '</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
	});
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
			            {"mData": "phone"},
			            {"mData": "phone2"},
			            {"mData": "payPrice"},             
			            {"mData": "recentCreateDate"},            
						{"mData": ""}
			           ],
		"columnDefs" : [
		{
		"render" : function(data, type, row) {
				return '<a href="javascript:void(0);" id="orderDetail">详情</a>';
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
							var phone = $('#phone').val();
							var phoneStage = $('#phoneStage').val();
							var updateDate = $('#updateDate').val();
							aoData.push({'name':'phone','value':phone},{'name':'phoneStage','value':phoneStage},
									{'name':'type','value':4});
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
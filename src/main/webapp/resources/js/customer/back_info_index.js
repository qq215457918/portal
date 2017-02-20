$(document).ready(function(){
	initData();
	
	if($('#importResult').val() != ''){
		alert("部分数据导入失败，第" + $('#importResult').val() + "行出现问题，请检查数据");
	}
	
	$('#searchCustomer').click(function(){
		$('#customerInfo').dataTable().fnDraw();
	});
	
	$('#exportCustomer').click(function(){
		
		var dpd1 = $('#dpd1').val();
		var dpd2 = $('#dpd2').val();
		var area = $('#area').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '';
		exportHtml += '<form id="exportExcel" action="customerInfo/exportCustomer" style="display:none;">';
		exportHtml += '<input type="hidden" name="startTime" value="' + dpd1 + '"/>'
		exportHtml += '<input type="hidden" name="endTime" value="' + dpd2 + '"/>'
		exportHtml += '<input type="hidden" name="area" value="' + area + '"/>'
		exportHtml += '<input type="hidden" name="type" value="1"/>'
		exportHtml += '</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
	});
	
	$('button[name=importExcel]').click(function(){
		var flag = $(this).attr('self');
		if(flag == 1){
			$('#importExcelForm').attr('action','customerInfo/importCustomerAdd');
		}else {
			$('#importExcelForm').attr('action','customerInfo/importCustomer');
		}
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
		"sAjaxSource": "customerInfo/selectCustomerInfoList", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号   
		            {"mData": "phoneStaffName"},
		            {"mData": "name"},
		            {"mData": "sexShow"},             
		            {"mData": "phoneHidden"},            
					{"mData": "address"},
					{"mData": "visitCount"},
					{"mData": "recentVisitDate"}
		           ],
		"columnDefs" : [ {
				   			"render" : function(data, type, row) {
			   					return formatDate(data);
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
							var backCountS = $('#backCountS').val();
							var backCountE = $('#backCountE').val();
							var phoneStage = $('#phoneStage').val();
							var visiteDate1 = $('#dpd1').val();
							var visiteDate2 = $('#dpd2').val();
							var area = $('#area').val();
							aoData.push({'name':'backCountS','value':backCountS},{'name':'backCountE','value':backCountE},
									{'name':'visiteDate1','value':visiteDate1},{'name':'visiteDate2','value':visiteDate2},
									{'name':'type','value':1},{'name':'phoneStage','value':phoneStage},
									{'name':'area','value':area});
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
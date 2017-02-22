$(document).ready(function(){
	initData();
	
	if($('#importResult').val() != ''){
		alert("部分数据导入失败，第" + $('#importResult').val() + "行出现问题，请检查数据");
	}
	
	$('#searchCustomer').click(function(){
		$('#customerInfo').dataTable().fnDraw();
	});
	
	$('#exportCustomer').click(function(){
		
		var phoneStage = $('#phoneStage').val();
		var dpd1 = $('#dpd1').val();
		var dpd2 = $('#dpd2').val();
		var importDate1 = $('#importDate1').val();
		var importDate2 = $('#importDate2').val();
		var area = $('#area').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '';
		exportHtml += '<form id="exportExcel" action="customerInfo/exportCustomer" style="display:none;">';
		exportHtml += '<input type="hidden" name="phoneStage" value="' + phoneStage + '"/>'
		exportHtml += '<input type="hidden" name="exportDate1" value="' + dpd1 + '"/>'
		exportHtml += '<input type="hidden" name="exportDate2" value="' + dpd2 + '"/>'
		exportHtml += '<input type="hidden" name="importDate1" value="' + importDate1 + '"/>'
		exportHtml += '<input type="hidden" name="importDate2" value="' + importDate2 + '"/>'
		exportHtml += '<input type="hidden" name="area" value="' + area + '"/>'
//		exportHtml += '<input type="hidden" name="exportCount" value="' + exportCount + '"/>'
		exportHtml += '<input type="hidden" name="type" value="0"/>'
		exportHtml += '</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
	});
	
	// 初始化日期插件
	nowTemp = new Date();
	now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
	checkin = $("#importDate1").datepicker({
		onRender : function(date) {
			if (date.valueOf() < now.valueOf()) {
				return "disabled";
			} else {
				return "";
			}
		}
	}).on("changeDate", function(ev) {
		var newDate;
		if (ev.date.valueOf() > checkout.date.valueOf()) {
			newDate = new Date(ev.date);
			newDate.setDate(newDate.getDate() + 1);
			checkout.setValue(newDate);
		}
		checkin.hide();
		return $("#importDate2")[0].focus();
	}).data("datepicker");
	checkout = $("#importDate2").datepicker({
		onRender : function(date) {
			if (date.valueOf() <= checkin.date.valueOf()) {
				return "disabled";
			} else {
				return "";
			}
		}
	}).on("changeDate", function(ev) {
		return checkout.hide();
	}).data("datepicker");
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
		            {"mData": "typeName"},
		            {"mData": "name"},
		            {"mData": "sexShow"},
		            {"mData": "phoneHidden"},
		            {"mData": "address"}, 
		            {"mData": "recentImportDate"},            
					{"mData": "recentExportDate"}
		           ],
		"columnDefs" : [ {
		   			"render" : function(data, type, row) {
		   					return formatDate(data);
		   				},
		   			"targets" : 6
		   			},
		   			{
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
							var phoneStage = $('#phoneStage').val();
							var dpd1 = $('#dpd1').val();
							var dpd2 = $('#dpd2').val();
							var importDate1 = $('#importDate1').val();
							var importDate2 = $('#importDate2').val();
							var area = $('#area').val();
							aoData.push({'name':'phoneStage','value':phoneStage}, {'name':'type','value':0},
									{'name':'importDate1','value':importDate1}, {'name':'importDate2','value':importDate2},
									{'name':'exportDate1','value':dpd1}, {'name':'exportDate2','value':dpd2},
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

$(document).ready(function(){
	var date = new Date();
	if($('#dateInfo').val()==''){
		$('#dateInfo').val(date.getFullYear() + '-'
				+ (date.getMonth()+1<10?'0'+date.getMonth():(date.getMonth()+1)) + '-' 
				+ (date.getDate()<10?'0'+date.getDate():date.getDate()));
	}
	
	$('#dateInfo').datepicker({format: 'yyyy-mm-dd',defaultViewDate: 'today'});
	
	$('#searchAchieve').click(function(){
		if('' == $('#dateInfo').val()){
			return;
		}
		$('#flowForm').submit();	
	});
	
	$('#toAchieveExam').click(function(){
		alert("提交成功");
		$.ajax({
			"dataType": 'json',
			"type": "POST",
			"url": 'workflow/toAchieveExam',
			"data": '',
			"success": function(data){
				location.reload();
			}
		})
	});
	
	initData();
}); 

function initData(){
	$('#clerkOrderInfo').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "customerInfo/customerOrderInfoList", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号
		            {"mData": "orderNumber"},
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
							var receiverStaffId = $('#receiverStaffId').val();
							var createDate = $('#dateInfo').val();
							aoData.push({'name':'receiverStaffId','value':receiverStaffId},{'name':'createDate','value':createDate});
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
	
	// 接待
	$('#clerkReceiveInfo').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "workflow/clerkReceiveInfo", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号
		            {"mData": "customerName"},
		            {"mData": "typeName"},
		            {"mData": "customerPhone"},
		            {"mData": "startTime"},
		            {"mData": "endTime"},
		            {"mData": "orderNumber"}, 
		           ],
       "columnDefs" : [ {
   			"render" : function(data, type, row) {
   					return formatDate(data);
   				},
   			"targets" : 4
   			},{
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
							var receiverStaffId = $('#receiverStaffId').val();
							var createDate = $('#dateInfo').val();
							aoData.push({'name':'receiverStaffId','value':receiverStaffId},{'name':'createDate','value':createDate});
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

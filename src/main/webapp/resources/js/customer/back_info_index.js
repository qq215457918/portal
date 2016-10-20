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
		            {"mData": "phone"},
		            {"mData": "phone2"},
		            {"mData": "name"},             
		            {"mData": "site"},             
		            {"mData": "transactionAmount"},            
					{"mData": "recentVisitDate"}
		           ],
        "fnDrawCallback": function(){
   			var api = this.api();
   			api.column(0).nodes().each(function(cell, i) {
   				cell.innerHTML =  i + 1;
   			});
   		},
		"fnServerData": function (sSource, aoData, fnCallback) {
							var backCountS = $('#backCountS').val();
							var backCountE = $('#backCountE').val();
							var startTime = $('#dpd1').val();
							var endTime = $('#dpd2').val();
							aoData.push({'name':'backCountS','value':backCountS},{'name':'backCountE','value':backCountE},
									{'name':'startTime','value':startTime},{'name':'endTime','value':endTime},
									{'name':'type','value':1});
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
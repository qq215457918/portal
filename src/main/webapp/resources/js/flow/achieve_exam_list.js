$(document).ready(function(){
	initData();
	
	$('#uploadFLow').click(function(){
		if('' == $('#flowFile').val() || '' == $('#flowName').val()){
			return;
		}
		$('#flowForm').submit();	
	});
	
}); 

$(document).on('click', '#deleteFlow', function () { 
	var deploymentId = $(this).closest('tr').find('td').eq(1).html();
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": "workflow/delDeployment",
		"data": {'deploymentId' : deploymentId},
		"async": false
	})
	$('#flowDepInfo').dataTable().fnDraw();
	$('#flowPdInfo').dataTable().fnDraw();
});

function initData(){
	$('#achieveExam').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"bPaginate": false, //分页
		"bInfo": false,//页脚信息
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "workflow/selectTaskListById", // 地址
		"aoColumns": [ 
			        {"mData": null, "target": 0},	//序列号   
		            {"mData": "clerkId"},
		            {"mData": "clerkName"},
		            {"mData": "name"}, 
		            {"mData": "assignee"},    
		            {"mData": ""},             
		           ],
       "columnDefs" : [ {
			"render" : function(data, type, row) {
				return (data.year + 1900) + '/' + data.month + '/' + data.day;
			},
			"targets" : 3
			},
			{
			"render" : function(data, type, row) {
				return '<a href="javascript:void(0);">审批</a>';
			},
			"targets" : 4
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
							var type = $('#type option:selected').val();
							var updateDate = $('#updateDate').val();
							aoData.push({'name':'phone','value':phone},{'name':'phoneStage','value':phoneStage},{'name':'type','value':type});
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
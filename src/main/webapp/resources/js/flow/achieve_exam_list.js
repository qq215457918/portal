$(document).ready(function(){
	initData();
	
	$('#uploadFLow').click(function(){
		if('' == $('#flowFile').val() || '' == $('#flowName').val()){
			return;
		}
		$('#flowForm').submit();	
	});
}); 

$(document).on('click', '#itemDetail', function () { 
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": 'workflow/selectHistoryList',
		"data": {
			'taskId': 1
		},
		"success": function(data){
			var hisHtml = '';
			if(data.length > 0){
				hisHtml += '<tr><td>' + (data.year + 1900) + '-' + data.month + '-' + data.day + '</td>';
				hisHtml += '<td>' + data.userId.split('.')[1] + '</td>';
				hisHtml += '<td>' + (data.year + 1900) + '-' + data.month + '-' + data.day + '</td></tr>';
			}else {
				hisHtml = '<tr><td colspan="3" style="text-align: center;">没有审批记录</td></tr>'
			}
			$('#detailModel tbody').html(hisHtml);
		}
	})
});

$(document).on('click', '#itemExam', function () { 
//	$('#modal-body').data({'taskId': 1, 'id': 1});
	$('#examModel input[name=taskId]').val(1);
	$('#examModel input[name=id]').val(1);
});

function commitExam(suggestion){
	$('#examModel inout[name=suggestion]').val(suggestion);
	$('#examModel').modal('hide');
	if(suggestion=='pass'){
		$('#itemExam').html('已审核');
	}else {
		$('#itemExam').html('已拒绝');
	}
}

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
				return '<a href="#examModel" data-toggle="modal" id="itemExam">审批</a>&nbsp;'
					+ '<a href="#detailModel" data-toggle="modal" data-taskId="1010" id="itemDetail">详情</a>';
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
$(document).ready(function(){
	var date = new Date();
	$('#dateInfo').val(date.getFullYear() + '-'
			+ (date.getMonth()+1<10?'0'+date.getMonth():(date.getMonth()+1)) + '-' 
			+ (date.getDate()<10?'0'+date.getDate():date.getDate()));
	$('#dateInfo').datepicker({format: 'yyyy-mm-dd',defaultViewDate: 'today'});
	
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
			        {"mData": ""},
		            {"mData": "employeeName"},
		            {"mData": "orderCount"},
		            {"mData": "payPrice"}, 
		            {"mData": "actualPrice"}, 
		            {"mData": "receptionCount"},
		            {"mData": ""},             
		           ],
       "columnDefs" : [ {
			"render" : function(data, type, row) {
				return '<label><input name="optionsRadios1" type="checkbox" value="' + row.clerkId + '"><span></span></label>';
			},
			"targets" : 0
			},{
			"render" : function(data, type, row) {
				return '<a href="workflow/clerkEverydayAchievenment?employeeId=' + row.employeeId + '" data-toggle="modal" id="itemDetail">详情</a>';
			},
			"targets" : 6
			}],
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
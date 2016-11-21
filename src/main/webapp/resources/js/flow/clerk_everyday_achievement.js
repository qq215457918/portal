$(document).ready(function(){
	var date = new Date();
	$('#dateInfo').val(date.getFullYear() + '-'
			+ (date.getMonth()+1<10?'0'+date.getMonth():(date.getMonth()+1)) + '-' 
			+ (date.getDate()<10?'0'+date.getDate():date.getDate()));
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
}); 
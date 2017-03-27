// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 查询功能
	$("#queryId").click(function(){
		var phone = $("#phoneNo").val();
		if(phone =="undefined" || phone == null ||phone =="" ||phone ==" "||isNaN(phone)){
			alert("请输入正确的电话号码")
			return;
		}else{
			window.location.href="visit/first?phoneNo="+phone;
		}
	});
});

/**
 * 文件名：receive_arealist
 * 用途：接待统计列表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-04
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 查询功能
	$("#queryId").click(function(){
		var phone = $("#phoneNo").value;
		alert(1)
		var url="/reception/first?phone="+did;
		window.location.href=detailUrl;
	});
		
	// 返回上一页
	$("#back").click(function(){
		window.history.back(-1);
	});	
});

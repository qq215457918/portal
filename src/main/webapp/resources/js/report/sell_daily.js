/**
 * 文件名：sell_daily
 * 用途：销售日报表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-11-26
 */
// base:项目根路径
var base;
$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 页面初始化加载数据
	initData();
	
	// 查询功能
	$(".btn-success").click(function(){
		// 查询数据
		initData();
	});
	
	// 所属区域变化事件
	$("#area").change(function(){
		// 查询数据
		initData();
	});
	
	// 导出功能
	/*$("#export").click(function(){
		alert("暂时还没有做......");
	});*/

});

// 初始获取数据
function initData() {
	$.ajax({
		"dataType": 'json',
		"type": "POST",
		"url": base + "report/ajaxSellDaily",
		"data": {
			startDate : $('#startDate').val(),
			area : $("#area").val()
		},
		"success": function(data){
			var area = $("#area").find("option:selected").text();
			var goodsList = data.goodsList;
			var clearing = data.clearing;
			$("#sellDaily").empty();
			var html =  "<tr><td colspan='5' style='background: #CCDDFF; font-size: 20px; font-weight: bold;'>" + area + "销售日报表</td></tr>" +
						"<tr><td colspan='5' style='text-align: left;'><div style='width: 15%; margin-left: 10%;'>日&nbsp;&nbsp;&nbsp;&nbsp;期 <span style='margin-left: 20%;'>" + $('#startDate').val() + "</span></div></td></tr>" +
						"<tr><td colspan='5' style='font-weight: bold;'>销售藏品明细</td></tr>" +
						"<tr>" +
							"<td>藏品名称</td>" +
				           	"<td>数&nbsp;&nbsp;量</td>" +
							"<td>单&nbsp;&nbsp;价</td>" +
				           	"<td>总计金额</td>" +
				           	"<td>备&nbsp;&nbsp;注</td>" +
						"</tr>";
			var contents = "";
			var total = "";
			for (var int = 0; int < goodsList.length; int++) {
				contents = "<tr>" + 
							"<td>" + goodsList[int].goodName + "</td>" + 
							"<td>" + goodsList[int].amount + "</td>" + 
							"<td>" + goodsList[int].price + "</td>" + 
							"<td>" + goodsList[int].totalPrice + "</td>" + 
							"<td>" + goodsList[int].viewRemark + "</td>" +
						"<tr>";
				total += goodsList[int].totalPrice;
				html += contents;
			}
			contents = "<tr style='background: #CCDDFF;'>" + 
							"<td>合&nbsp;&nbsp;计</td>" + 
							"<td></td>" + 
							"<td></td>" + 
							"<td>" + total + "</td>" + 
							"<td></td>" +
						"<tr>" +
						"<tr><td colspan='5' style='font-weight: bold;'>销售结算明细</td></tr>" +
						"<tr>" +
						"<td>收款方式</td>" +
						"<td>金&nbsp;&nbsp;额</td>" +
						"<td>实际入账</td>" +
						"<td colspan='2'>备&nbsp;&nbsp;注</td>" +
						"</tr>";
			html += contents;
			total = "";
			var income = "";
			for (var int = 0; int < clearing.length; int++) {
				contents = "<tr>" + 
							"<td>" + clearing[int].financeType + "</td>" + 
							"<td>" + clearing[int].actualPrice + "</td>" + 
							"<td>" + clearing[int].income + "</td>" + 
							"<td colspan='2'>" + clearing[int].remarks + "</td>" + 
						"<tr>";
				total += clearing[int].actualPrice;
				income += clearing[int].income;
				html += contents;
			}
			contents = "<tr style='background: #CCDDFF;'>" + 
							"<td>合&nbsp;&nbsp;计</td>" + 
							"<td>" + total + "</td>" + 
							"<td>" + income + "</td>" + 
							"<td colspan='2'></td>" +
						"<tr>";
			html += contents;
			$("#sellDaily").append(html);
		}
	});
}

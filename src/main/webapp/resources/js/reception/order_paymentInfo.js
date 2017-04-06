// base:项目根路径
var base;

$(function() {
	base = $("base").attr('href');
	// 页面初始化加载数据
	initData();

});
// 初始获取数据
function initData() {
	$("#paymentInfo")
			.dataTable(
					{
						"bSort" : false, // 是否显示排序
						"bFilter" : false, // 去掉搜索
						"bPaginate" : true, // 分页
						"sPaginationType" : "full_numbers", // 分页，一共两种样式
						"bLengthChange" : true,// 每页显示记录数
						"iDisplayLength" : $("select[name='orderTable_length']")
								.val(),// 每页显示行数
						"iDisplayLength" : 10,
						"bProcessing" : true, // 显示正在处理
						"bServerSide" : true, // 后台请求
						"bInfo" : true, // Showing 1 to 10 of 23 entries
						"bRetrieve" : true,
						"sAjaxSource" : "order/paymentInfo", // 地址
						"aoColumns" : [ {
							"mData" : "orderNumber"
						}, {
							"data" : "customerPayType",
							"render" : function(data, type, full) {
								var type;
								switch (data) {
								case "1":
									type = "信用卡";
									break;
								case "2":
									type = "储蓄卡（封顶）";
									break;
								case "3":
									type = "储蓄卡（不封顶）";
									break;
								case "4":
									type = "支付宝";
									break;
								case "5":
									type = "微信";
									break;
								case "6":
									type = "现金";
									break;
								default:
									type = "";
									break;
								}
								return type;
							}
						}, {
							"mData" : "paymentAccountId"
						}, {
							"mData" : "payAmount"
						}, {
							"mData" : "payAmountActual"
						}, {
							"mData" : "poundage"
						} ],
						"columnDefs" : [ {
							"render" : function(data, type, row) {
								return '<a class="btn btn-info btn-block" href="#detailModel" data-toggle="modal" '
										+ 'data-order-number='
										+ '"'
										+ row.orderNumber
										+ '"'
										+ 'data-paymentInfo-id="'
										+ row.id
										+ '" '
										+ 'data-pay-type="'
										+ row.customerPayType
										+ '" '
										+ 'data-pay-account="'
										+ row.paymentAccountId
										+ '" '
										+ 'data-pay-amount="'
										+ row.payAmount
										+ '" '
										+ 'data-pay-amountActual="'
										+ row.payAmountActual
										+ '" '
										+ 'data-pay-poundage="'
										+ row.poundage
										+ '" ' + 'id="modifyItem">修改</a>';
							},
							"targets" : 6
						} ],
						"fnServerData" : function(sSource, aoData, fnCallback) {// 查询项
							aoData.push({
								'name' : 'orderNumber',
								'value' : $('#orderNumber').val()
							});
							$.ajax({
								"dataType" : 'json',
								"type" : "POST",
								"url" : sSource,
								"data" : aoData,
								"success" : function(data) {
									fnCallback(data);
								}
							})
						}
					});
}
$(document)
		.on(
				'click',
				'#modifyItem',
				function() {
					var orderNumber = $(this).attr('data-order-number');
					var paymentInfoId = $(this).attr('data-paymentInfo-id');
					var amount = $(this).attr('data-pay-amount');
					var amountActual = $(this).attr('data-pay-amountActual');
					var poundage = $(this).attr('data-pay-poundage');

					$('#paymentInfpUpdate input[name=orderNumber]').val(
							orderNumber);
					$('#paymentInfpUpdate input[name=orderId]').val(paymentInfoId);
					$('#paymentInfpUpdate input[name=amount]').val(amount);
					$('#paymentInfpUpdate input[name=amountActual]').val(
							amountActual);
					$('#paymentInfpUpdate input[name=poundage]').val(poundage);

					var payType = $(this).attr('data-pay-type');
					$(
							'#paymentInfpUpdate select[name=payType][value='
									+ payType + ']').attr("selected", true);
					var account = $(this).attr('data-pay-account');

					$
							.ajax({
								"dataType" : 'json',
								"type" : "POST",
								"url" : 'workflow/getAccountAndPayTypeInfo',
								"data" : {
									'orderId' : paymentInfoId
								},
								"success" : function(data) {
									var accountHtml = '';
									for ( var i in data.accountList) {
										accountHtml += '<option value="'
												+ data.accountList[i].payment_account_id
												+ '"';
										if (data.accountList[i].payment_account_name === account) {
											accountHtml += ' selected = true'
										}
										accountHtml += '>'
												+ data.accountList[i].payment_account_name
												+ '</option>'
									}
									$(
											'#paymentInfpUpdate select[name$=paymentAccountId]')
											.html(accountHtml);
								}
							})
				});
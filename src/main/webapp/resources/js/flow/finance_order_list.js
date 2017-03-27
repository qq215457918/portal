$(document)
		.ready(
				function() {
					initData();

					// 初始化日期插件
					nowTemp = new Date();
					now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(),
							nowTemp.getDate(), 0, 0, 0, 0);
					checkin = $("#createDate").datepicker({
						onRender : function(date) {
							if (date.valueOf() < now.valueOf()) {
								return "disabled";
							} else {
								return "";
							}
						}
					}).data("datepicker");

					$('#searchList').click(function() {
						// if ('' == $('#orderNumber').val()
						// && '' == $('#customerPhone').val()
						// && '' == $('#customerName').val()
						// && '' == $('#receiverName').val()
						// && '' == $('#createDate').val()
						// && '' == $('#payType').val()) {
						// return;
						// }
						$('#financeOrderExam').dataTable().fnDraw();
					});

					$('#outgoing')
							.click(
									function() {
										window.location.href = "workflow/downloadExcel?orderId="
												+ $('input[name=orderId]')
														.val();
									});

					$('#receiveMoney')
							.click(
									function() {
										window.location.href = "workflow/downloadExcel?orderId="
												+ $('input[name=orderId]')
														.val();
									});

					// 关闭madel窗口隐藏打印条目
					$('#printInfo').on('hidden.bs.modal', function() {
						// $('#outgoingInfo').hide();
						// $('#receiveMoneyInfo').hide();
					});

					// 添加收款条目
					$('a[name=addSettlement]').click(
							function() {
								var col = $('#updateCivi .base-column:first')
										.clone();
								col.find('#payAmount').val('');
								col.find('#payAmountActual').val('');
								col.find('#poundage').val('');
								col.appendTo('#updateCivi').find(
										'a[name=delete]').show();
							});

					$('#commitForm')
							.click(
									function() {
										$('.base-column .form-data')
												.each(
														function(i) {
															$(this)
																	.find(
																			'select,input')
																	.each(
																			function() {
																				$(
																						this)
																						.attr(
																								'name',
																								'paymentList['
																										+ i
																										+ '].'
																										+ $(
																												this)
																												.attr(
																														'id'));
																			});
														});
										$('#updateCivi').submit();
									});
				});

$(document).delegate('input[name=payAmount],input[name=payAmountActual]',
		'blur', function() {
			var obj = $(this).closest('.base-column');
			var total = obj.find('#payAmount').val();
			var actual = obj.find('#payAmountActual').val();
			if ('' == total || '' == actual || isNaN(total) || isNaN(actual)) {
				return;
			}
			obj.find('#poundage').val(total - actual);
		});

$(document).on('click', 'a[name=delete]', function() {
	$(this).closest('.base-column').remove();
});

$(document)
		.on(
				'click',
				'#confirmReceipt',
				function() {
					var operate = $(this).attr('data-operate-id');

					if (1 == operate) {
						$('#orderSettlement .modal-title').html('付款');
						$('#orderSettlement a[name=addSettlement]')
								.html('添加付款');
						$('#orderSettlement label[name=addSettlement]').html(
								'付款账户');
						$('#orderSettlement label[name=payType]').html('付款方式');
						$('#orderSettlement label[name=amount]').html('付款金额');
						$('#orderSettlement label[name=amountActual]').html(
								'实际付款金额');
					} else {
						$('#orderSettlement .modal-title').html('收款');
						$('#orderSettlement a[name=addSettlement]')
								.html('添加收款');
						$('#orderSettlement label[name=addSettlement]').html(
								'收款账户');
						$('#orderSettlement label[name=payType]').html('收款方式');
						$('#orderSettlement label[name=amount]').html('收款金额');
						$('#orderSettlement label[name=amountActual]').html(
								'实际收款金额');
					}

					$('input[name=orderNumber]').val(
							$(this).attr('data-order-number'));
					var orderId = $(this).attr('data-order-id');
					$('input[name=orderId]').val(orderId);
					$('input[name=payType]').val($(this).attr('data-pay-type'));
					$('input[name=operate]').val(operate);

					// 初始化支付类型
					$('#updateCivi .base-column:not(:first)').remove();
					$("#updateCivi option").removeAttr("selected");
					$("#updateCivi select[name$=paymentAccountId] option:first")
							.attr("selected", true);
					$('#updateCivi select[name$=customerPayType] option:first')
							.attr("selected", true);
					$('#updateCivi input[name$=payAmount]').val('');
					$('#updateCivi input[name$=payAmountActual]').val('');
					$('#updateCivi input[name$=poundage]').val('');

					$
							.ajax({
								"dataType" : 'json',
								"type" : "POST",
								"url" : 'workflow/getAccountAndPayTypeInfo',
								"data" : {
									'orderId' : orderId
								},
								"success" : function(data) {
									var accountHtml = '<option value="">--请选择--</option>';
									// var payTypeHtml = '<option
									// value="">--请选择--</option>';
									var orderDetailHtml = '<option value="">--请选择--</option>';

									for ( var i in data.accountList) {
										accountHtml += '<option value="'
												+ data.accountList[i].payment_account_id
												+ '">'
												+ data.accountList[i].payment_account_name
												+ '</option>'
									}

									for ( var i in data.orderDetailList) {
										orderDetailHtml += '<option value="'
												+ data.orderDetailList[i].id
												+ '">'
												+ data.orderDetailList[i].goodName
												+ '</option>'
									}

									// for(var j in data.payTypeList){
									// payTypeHtml += '<option value="' +
									// data.payTypeList[j].payment_account_id +
									// '">' +
									// data.payTypeList[j].payment_account_name
									// + '</option>'
									// }

									$(
											'#updateCivi select[name$=paymentAccountId]:first')
											.html(accountHtml);
									$(
											'#updateCivi select[name$=orderDetailId]:first')
											.html(orderDetailHtml);
									// $('#updateCivi
									// select[name$=customerPayType]:first').html(payTypeHtml);
								}
							})
				});
$(document).on('click', '#confirmReceipt1', function() {
	var operate = $(this).attr('data-operate-id');

	// 更新订单状态
	$.ajax({
		"dataType" : 'text',
		"type" : "POST",
		"url" : 'workflow/updateOrderInfo',
		"data" : {
			'orderId' : $(this).attr('data-order-id'),
			'operate' : operate
		},
		"success" : function(data) {
			if (1 == operate) {
				alert("已确认收款");
			} else {
				alert("更新成功,待仓库审核");
			}

			$('#financeOrderExam').dataTable().fnDraw();
		}
	})
});

$(document).on(
		'click',
		'#orderDetailInfo',
		function() {
			window.location.href = 'order/orderModifyIndex?orderId='
					+ $(this).attr('data-order-id');
		});

$(document)
		.on(
				'click',
				'#toPrint',
				function() {
					$('input[name=orderId]').val($(this).attr('data-order-id'));
					$
							.ajax({
								"dataType" : 'json',
								"type" : "POST",
								"url" : 'workflow/selectOrderInfoById',
								"data" : {
									'orderId' : $(this).attr('data-order-id')
								},
								"success" : function(data) {
									if (data.length > 0) {
										var remark = data[0].remark;
										var customerName = data[0].customerName;
										var phoneStaffName = data[0].phoneStaffName;
										var receiverStaffName = data[0].receiverStaffName;
										var actualPrice = data[0].actualPrice;
										var payPrice = data[0].payPrice;
										var payPriceCN = data[0].payPriceCN;
										var today = data[0].today;
										var payTypeName = data[0].payTypeName;
										var receiveMoneyHtml = '';
										var outgoingHtml = '';

										var count = 0
										for ( var i in data) {
											count++;
											receiveMoneyHtml += '<tr><td>'
													+ data[i].goodName
													+ '</td>';
											receiveMoneyHtml += '<td>'
													+ data[i].amount + '</td>';
											receiveMoneyHtml += '<td>件</td>';
											receiveMoneyHtml += '<td>'
													+ data[i].price + '</td>';
											receiveMoneyHtml += '<td>'
													+ actualPrice
													+ '</td></tr>';

											outgoingHtml += '<tr><td>'
													+ data[i].goodName
													+ '</td>';
											outgoingHtml += '<td>'
													+ data[i].amount + '</td>';
											outgoingHtml += '<td>'
													+ data[i].price + '</td>';
											outgoingHtml += '<td>'
													+ data[i].price
													* data[i].amount + '</td>';
											outgoingHtml += '<td>'
													+ payTypeName + '</td>';
											outgoingHtml += '<td></td>';
											outgoingHtml += '<td></td></tr>';
										}
										if (count < 4) {
											for (var j = 0; j < 4 - count; j++) {
												outgoingHtml += '<tr style="height:24px;"><td colspan="8"><td></tr>';
												receiveMoneyHtml += '<tr style="height:24px;"><td colspan="4"><td></tr>';
											}
										}

										$(
												'#receiveMoneyInfo span[name=customerName]')
												.html('' + customerName);
										$('#receiveMoneyInfo span[name=today]')
												.html('' + today);
										$(
												'#receiveMoneyInfo tbody[name=detail]')
												.html(receiveMoneyHtml);
										$('#receiveMoneyInfo td[name=priceCn]')
												.html(payPriceCN);
										$(
												'#receiveMoneyInfo span[name=receiverStaffName]')
												.html('' + receiverStaffName);
										$(
												'#receiveMoneyInfo span[name=phoneStaffName]')
												.html('' + phoneStaffName);

										$(
												'#outgoingInfo span[name=customerName]')
												.html('' + customerName);
										$('#outgoingInfo span[name=today]')
												.html('' + today);
										$('#outgoingInfo tbody[name=detail]')
												.html(outgoingHtml);
										$('#outgoingInfo td[name=remark]')
												.html(remark);
										$(
												'#outgoingInfo span[name=receiverStaffName]')
												.html('' + receiverStaffName);
										$(
												'#outgoingInfo span[name=phoneStaffName]')
												.html('' + phoneStaffName);
									}
								}
							})
				});

function commitExam(suggestion) {
	$('#examModel inout[name=suggestion]').val(suggestion);
}
$(document).on(
		'click',
		'#orderPaymentInfo',
		function() {
			window.location.href = 'order/orderPaymentInfo?orderNumber='
					+ $(this).attr('data-order-number');
		});

function initData() {
	$('#financeOrderExam')
			.dataTable(
					{
						"bSort" : false, // 是否显示排序
						"bFilter" : false, // 去掉搜索
						"sPaginationType" : "full_numbers", // 分页
						"bProcessing" : true, // 显示正在处理
						"bServerSide" : true, // 后台请求
						"bRetrieve" : true,
						"sAjaxSource" : "workflow/selectFinanceOrder", // 地址
						"aoColumns" : [ {
							"mData" : null,
							"target" : 0
						}, {
							"mData" : "orderNumber"
						}, {
							"mData" : "receiverStaffName"
						}, {
							"mData" : "phoneStaffName"
						}, {
							"mData" : "customerName"
						}, {
							"mData" : "goodsQuantity"
						}, {
							"mData" : "orderTypeName"
						}, {
							"mData" : "payTypeName"
						}, {
							"mData" : "payPrice"
						}, {
							"mData" : "wareHouseFlagName"
						}, {
							"mData" : ""
						} ],
						"columnDefs" : [
								{
									"render" : function(data, type, row) {
										var operation = '<a class="btn btn-warning btn-block" href="#orderSettlement" data-toggle="modal" data-order-id="'
												+ row.id
												+ '" data-pay-type="'
												+ row.payType
												+ '"  data-order-number="'
												+ row.orderNumber
												+ '" id="confirmReceipt">确认收款</a>'
												+ '<a class="btn btn-primary btn-block" href="#printInfo" data-toggle="modal" data-order-id="'
												+ row.id
												+ '" id="toPrint">打印</a>';
										if (row.warehouseFlag == -1) {
											operation = '<a class="btn btn-warning btn-block" href="#orderSettlement" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" data-pay-type="'
													+ row.payType
													+ '"  data-order-number="'
													+ row.orderNumber
													+ '" data-operate-id="1" id="confirmReceipt">确认付款</a>'
													+ '<a class="btn btn-primary btn-block" href="#printInfo" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" id="toPrint">打印</a>';
										}
										if (row.orderType == 2) {
											var operation = '<a class="btn btn-danger btn-block" href="#orderSettlement" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" data-pay-type="'
													+ row.payType
													+ '"  data-order-number="'
													+ row.orderNumber
													+ '" id="confirmReceipt">确认付款</a>'
													+ '<a class="btn btn-primary btn-block" href="#printInfo" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" id="toPrint">打印</a>';
										}
										if (row.financeFlag == 1) {
											if (row.orderType == 2
													|| row.orderType == 5) {
												operation = '<a class="btn btn-info btn-block" href="javascript:;" data-toggle="modal">已付款</a>'
											} else {
												operation = '<a class="btn btn-success btn-block" href="javascript:;" data-toggle="modal">已收款</a>'
											}
											operation += '<a class="btn btn-primary btn-block" href="#printInfo" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" id="toPrint">打印</a>';
											operation += '<a class="btn btn-default btn-block" data-toggle="modal" data-order-number="'
													+ row.orderNumber
													+ '" id="orderPaymentInfo">支付详情</a>';

										}
										if (row.financeFlag == -1) {

											operation = '<a class="btn btn-success btn-block" href="javascript:;" data-toggle="modal">已付款</a>'

											operation
													+ '<a class="btn btn-primary btn-block" href="#printInfo" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" id="toPrint">打印</a>';
											operation += '<a class="btn btn-default btn-block" data-toggle="modal" data-order-number="'
													+ row.orderNumber
													+ '" id="orderPaymentInfo">支付详情</a>';
										}
										operation += '<a class="btn btn-default btn-block" data-toggle="modal" data-order-id="'
												+ row.id
												+ '" id="orderDetailInfo">订单详情</a>';

										return operation;
									},
									"targets" : 10
								}, {
									"targets" : 5,
									"render" : function(data, type, row) {
										return data.slice(4);
									}
								} ],
						"fnDrawCallback" : function() {
							var api = this.api();
							api.column(0).nodes().each(function(cell, i) {
								cell.innerHTML = i + 1;
							});
						},
						"fnServerData" : function(sSource, aoData, fnCallback) {
							var orderId = $('#orderId').val();
							var orderNumber = $('#orderNumber').val();

							var customerPhone = $('#customerPhone').val();
							var customerName = $('#customerName').val();
							var receiverName = $('#receiverName').val();
							var createDate = $('#createDate').val();
							var payType = $('#payType').val();

							aoData.push({
								'name' : 'orderId',
								'value' : orderId
							}, {
								'name' : 'orderNumber',
								'value' : orderNumber
							}, {
								'name' : 'customerPhone',
								'value' : customerPhone
							}, {
								'name' : 'customerName',
								'value' : customerName
							}, {
								'name' : 'receiverName',
								'value' : receiverName
							}, {
								'name' : 'createDate',
								'value' : createDate
							}, {
								'name' : 'payType',
								'value' : payType
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
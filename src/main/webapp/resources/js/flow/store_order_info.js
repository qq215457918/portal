$(document)
		.ready(
				function() {
					initData();

					$('#searchList').click(
							function() {
								if ('' == $('#orderNumber').val()
										&& '' == $('#customerPhone').val()
										&& '' == $('#customerName').val()
										&& '' == $('#receiverName').val()) {
									return;
								}
								$('#financeOrderDate').dataTable().fnDraw();
							});

					$('#collect')
							.click(
									function() {
										// $('#collectInfo').show();
										// // $('#outgoingInfo').jqprint();
										// $("#collectInfo").print({
										// globalStyles: true,
										// mediaPrint: false,
										// stylesheet: null,
										// noPrintSelector: ".no-print",
										// iframe: true,
										// append: null,
										// prepend: null,
										// manuallyCopyFormValues: true,
										// deferred: $.Deferred(),
										// timeout: 750,
										// title: null,
										// doctype: '<!doctype html>'
										// });
										window.location.href = "workflow/downloadExcelCollect?orderId="
												+ $('input[name=orderId]')
														.val();
									});

					$('#outNoTake')
							.click(
									function() {
										// $('#hasPayInfo').show();
										// // $('#outgoingInfo').jqprint();
										// $("#hasPayInfo").print({
										// globalStyles: true,
										// mediaPrint: false,
										// stylesheet: null,
										// noPrintSelector: ".no-print",
										// iframe: true,
										// append: null,
										// prepend: null,
										// manuallyCopyFormValues: true,
										// deferred: $.Deferred(),
										// timeout: 750,
										// title: null,
										// doctype: '<!doctype html>'
										// });
										window.location.href = "workflow/downloadExcelPay?orderId="
												+ $('input[name=orderId]')
														.val();
									});

					$('#outgoing').click(function() {
						$('#outgoingInfo').show();
						// $('#outgoingInfo').jqprint();
						$("#outgoingInfo").print({
							globalStyles : true,
							mediaPrint : false,
							stylesheet : null,
							noPrintSelector : ".no-print",
							iframe : true,
							append : null,
							prepend : null,
							manuallyCopyFormValues : true,
							deferred : $.Deferred(),
							timeout : 750,
							title : null,
							doctype : '<!doctype html>'
						});
					});

					$('#receiveMoney').click(function() {
						$('#receiveMoneyInfo').show();
						// $('#outgoingInfo').jqprint();
						$("#receiveMoneyInfo").print({
							globalStyles : true,
							mediaPrint : false,
							stylesheet : null,
							noPrintSelector : ".no-print",
							iframe : true,
							append : null,
							prepend : null,
							manuallyCopyFormValues : true,
							deferred : $.Deferred(),
							timeout : 750,
							title : null,
							doctype : '<!doctype html>'
						});
					});

					$('#printInfo').on('hidden.bs.modal', function() {
						$('#collectInfo').hide();
						$('#outgoingInfo').hide();
						$('#receiveMoneyInfo').hide();
						$('#hasPayInfo').hide();
					});
				});

$(document).on('click', '#examPass', function() {
	var _this = $(this);
	$.ajax({
		"dataType" : 'text',
		"type" : "POST",
		"url" : 'workflow/updateOrderInfo',
		"data" : {
			'orderId' : _this.attr('data-order-id')
		},
		"success" : function(data) {
			alert("更新成功");
			$('#financeOrderExam').dataTable().fnDraw();
		}
	})
});

$(document).on('click', '#examError', function() {
	var _this = $(this);
	$.ajax({
		"dataType" : 'text',
		"type" : "POST",
		"url" : 'workflow/updateOrderInfo',
		"data" : {
			'orderId' : _this.attr('data-order-id'),
			'operate' : '1'
		},
		"success" : function(data) {
			alert("更新成功");
		}
	})
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
										var outgoingHtml = '';
										var receiveMoneyHtml = '';
										var hasPayInfoHtml = '';
										var collectHtml = '';

										for ( var i in data) {
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

											hasPayInfoHtml += '<tr><td>'
													+ data[i].goodName
													+ '</td>';
											hasPayInfoHtml += '<td>'
													+ data[i].unit + '</td>';
											hasPayInfoHtml += '<td>'
													+ data[i].amount + '</td>';
											hasPayInfoHtml += '<td>'
													+ actualPrice
													+ '</td></tr>';

											collectHtml += '<tr><td>'
													+ data[i].goodName
													+ '</td>';
											collectHtml += '<td></td>';
											collectHtml += '<td>'
													+ data[i].unit + '</td>';
											collectHtml += '<td>'
													+ data[i].price + '</td>';
											collectHtml += '<td></td><td></td><td></td><td></td><td>';
											collectHtml += '</td><td></td><td></td><td></td><td></td></tr>';

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
										}

										$('#collectInfo tbody[name=detail]')
												.html(collectHtml);
										$('#collectInfo td[name=remark]').html(
												payPriceCN);

										$(
												'#outgoingInfo span[name=customerName]')
												.html('客户：' + customerName);
										$('#outgoingInfo span[name=today]')
												.html('日期：' + today);
										$('#outgoingInfo tbody[name=detail]')
												.html(outgoingHtml);
										$('#outgoingInfo td[name=remark]')
												.html(remark);
										$(
												'#outgoingInfo span[name=receiverStaffName]')
												.html('接待：' + receiverStaffName);
										$(
												'#outgoingInfo span[name=phoneStaffName]')
												.html('客服：' + phoneStaffName);

										$(
												'#receiveMoneyInfo span[name=customerName]')
												.html('客户：' + customerName);
										$('#receiveMoneyInfo span[name=today]')
												.html('日期：' + today);
										$(
												'#receiveMoneyInfo tbody[name=detail]')
												.html(receiveMoneyHtml);
										$('#receiveMoneyInfo td[name=priceCn]')
												.html(payPriceCN);
										$(
												'#receiveMoneyInfo span[name=receiverStaffName]')
												.html('接待：' + receiverStaffName);
										$(
												'#receiveMoneyInfo span[name=phoneStaffName]')
												.html('客服：' + phoneStaffName);

										$('#hasPayInfo span[name=customerName]')
												.html('客户：' + customerName);
										$('#hasPayInfo span[name=today]').html(
												'日期：' + today);
										$('#hasPayInfo tbody[name=detail]')
												.html(hasPayInfoHtml);
										$('#hasPayInfo td[name=remark]').html(
												remark);
										$(
												'#hasPayInfo span[name=receiverStaffName]')
												.html('接待：' + receiverStaffName);
										$(
												'#hasPayInfo span[name=phoneStaffName]')
												.html('客服：' + phoneStaffName);
									}
								}
							})
				});

function commitExam(suggestion) {
	$('#examModel inout[name=suggestion]').val(suggestion);
}

function initData() {
	$('#financeOrderDate')
			.dataTable(
					{
						"bSort" : false, // 是否显示排序
						"bFilter" : false, // 去掉搜索
						"sPaginationType" : "full_numbers", // 分页
						"bProcessing" : true, // 显示正在处理
						"bServerSide" : true, // 后台请求
						"bRetrieve" : true,
						"sAjaxSource" : "order/orderModifyList", // 地址
						"aoColumns" : [ {
							"mData" : null,
							"target" : 0
						}, // 序列号
						{
							"mData" : "receiverStaffName"
						}, {
							"mData" : "phoneStaffName"
						}, {
							"mData" : "customerName"
						}, {
							"mData" : "orderNumber"
						}, {
							"mData" : "goodsQuantity"
						}, {
							"mData" : "payPrice"
						}, {
							"mData" : "orderTypeName"
						}, {
							"mData" : "financeDate"
						}, {
							"mData" : ""
						} ],
						"columnDefs" : [
								{
									"render" : function(data, type, row) {
										return formatDate(data);
									},
									"targets" : 8
								},
								{
									"render" : function(data, type, row) {
										var operation = '';
										if (row.financeFlag == 1) {
											operation = '<a class="btn btn-danger btn-block" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" onclick="doStoreOp(1, this)">出库确认</a>';
											if (row.warehouseFlag == 1) {
												operation = '<a class="btn btn-success btn-block" data-toggle="modal" data-order-id="'
														+ row.id + '">已出库</a>';
											}
										} else if (row.orderType == 2
												|| row.orderType == 3
												|| row.orderType == 5) {
											operation = '<a class="btn btn-warning btn-block" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" onclick="doStoreOp(-1, this)">入库确认</a>';
											if (row.warehouseFlag == -1) {
												operation = '<a class="btn btn-success btn-block" data-toggle="modal" data-order-id="'
														+ row.id + '">已入库</a>';
											}
										} else if (row.orderType == 4
												|| row.orderType == 6) {
											operation = '<a class="btn btn-info btn-block" data-toggle="modal" data-order-id="'
													+ row.id
													+ '" onclick="doStoreOp(3, this)">赠品领取</a>';
											if (row.warehouseFlag == 3) {
												operation = '<a class="btn btn-success btn-block" data-toggle="modal" data-order-id="'
														+ row.id + '">已领取</a>';
											}
										}
										operation += '<a class="btn btn-primary btn-block" href="#printInfo" data-toggle="modal" data-order-id="'
												+ row.id
												+ '" id="toPrint">打印</a>';
										return operation;
									},
									"targets" : 9
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
							var userId = $('#hiddenUserId').val();

							var customerPhone = $('#customerPhone').val();
							var customerName = $('#customerName').val();
							var receiverName = $('#receiverName').val();

							aoData.push({
								'name' : 'orderId',
								'value' : orderId
							}, {
								'name' : 'orderNumber',
								'value' : orderNumber
							}, {
								'name' : 'userId',
								'value' : userId
							}, {
								'name' : 'store',
								'value' : 1
							}, {
								'name' : 'customerPhone',
								'value' : customerPhone
							}, {
								'name' : 'customerName',
								'value' : customerName
							}, {
								'name' : 'receiverName',
								'value' : receiverName
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

function doStoreOp(opt, obj) {
	var _this = $(obj);
	$.ajax({
		"dataType" : 'text',
		"type" : "POST",
		"url" : 'order/updateOrderInfo',
		"data" : {
			'orderId' : _this.attr('data-order-id'),
			'opt' : opt
		},
		"success" : function(data) {
			alert("更新成功");
			$('#financeOrderDate').dataTable().fnDraw();
		}
	})
}

function formatDate(data) {
	if ('' == data || null == data) {
		return '';
	} else {
		if ('' == data.hours && '' == data.minutes && '' == data.seconds) {
			return (data.year + 1900) + '/' + (data.month + 1) + '/'
					+ data.date;
		} else {
			return (data.year + 1900) + '/' + (data.month + 1) + '/'
					+ data.date + ' ' + data.hours + ':' + data.minutes + ':'
					+ data.seconds;
		}
	}
}
// base:项目根路径
var base;
var cartPrefix = "goods-";

$(function() {
	base = $("base").attr('href');
	
	if($("#submitFlag").val()=="true"){
		$("#submitModal").modal('show');
	}
	
	//加载基本信息
	initGoodsData();
	//$('.spinner').spinner(); 
	$('#searchGoods').click(function(){
		$('#goodsInfo').dataTable().fnDraw();
	});
	
	// 查询功能
	$("#queryId").click(function(){
		// now the cart is {"item":"Product 1","price":35.50,"qty":2}
		var cartValue = sessionStorage.getItem( "cart" );
		var cartObj = JSON.parse( cartValue );	
	});	
	numControl();
	// 特殊审批
	$("#appConfirm").click(function(){
		var count = $('#applyCount').val();
		var reason = $('#applyReason').val();
		var goodId = $('#applyGoods').val();
		$.ajax({
			method : "POST",
			url : base+"/present/review",
			data : {
				"reason" : reason,
				"count" : count,
				"goodId" : goodId,
				"isVIP" : true
			},
			dataType : "JSON",
			success : function(data) {
				if(data.result==true){
					alert("提交成功，等待审批人进行审批");
					$('#presentModal').modal('hide');
					//getPresent();
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log('XMLHttpRequest.status :' + XMLHttpRequest.status);
				console.log('XMLHttpRequest.readyState :'
						+ XMLHttpRequest.readyState);
				console.log('textStatus:' + textStatus);
			}
		});
	});
});


function numControl(){
	//加的效果
	$(".add").click(function(){
	var n=$(this).prev().val();
	var num=parseInt(n)+1;
	if(num==0){ return;}
	$(this).prev().val(num);
	modifyTotalAmount();
	});
	//减的效果
	$(".jian").click(function(){
	var n=$(this).next().val();
	var num=parseInt(n)-1;
	if(num==0){ return}
	$(this).next().val(num);
	modifyTotalAmount();
	});
}


function initGoodsData(){
	$('#goodsInfo').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "order/receive", // 地址
		data : {
			"type" : "0"
		},
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号   
		            {"mData": "code"},
		            {"mData": "name"},
		            {"mData": "price"},             
		            {"mData": "amount"},       
		            {"mData": "unit"},    
					{"mData": "trusteeshipFlag"}
		           ],
	       "columnDefs": [
				{
					"targets": [7],
					"data": "id",
					"render": function(data, type, full) {
						return "<button class='btn btn-xs btn-warning' id='addId"+data+"' onclick='addGoods("+data+");'>  购  买   </button>";
					}
				}
			],           
        "fnDrawCallback": function(){
   			var api = this.api();
   			api.column(0).nodes().each(function(cell, i) {
   				cell.innerHTML =  i + 1;
   			});
   		},
		"fnServerData": function (sSource, aoData, fnCallback) {
							var lowPrice = $('#lowPrice').val();
							var highPrice = $('#highPrice').val();
							var goodInfo = $('#goodInfo').val();
							aoData.push({'name':'lowPrice','value':lowPrice},{'name':'highPrice','value':highPrice},
									{'name':'goodInfo','value':goodInfo},{'name':'type','value':0},
									{'name':'isPage','value':'true'});
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

function clearModal(){
	$('#modal-title').text("");
	$('#modal-data').html("");
}

//#myGoods
function openDelivery(){
	clearModal();
	$('#modal-title').text("请选择配送商品");
	getModalContent("3");
}

//#myGoods
function openPlacing(){
	clearModal();
	$('#modal-title').text("请选择配售商品");
	getModalContent("2");
}

//#myGoods
function openGifts(){
	clearModal();
	$('#modal-title').text("请选择礼品");
	getModalContent("1");
}

function getModalContent(type){
	$.ajax({
		method : "POST",
		url : base + "/order/receive",
		data : {
			"type" : type
		},
		dataType : "JSON",
		success : function(data) {
			var item ="";
			$.each(data.aaData, function(index, goodsForm) {
				item+="<tr><td>"+parseInt(index+1)+"</td>";	
				item+="<td>"+goodsForm.code+"</td>";
				item+="<td>"+goodsForm.name+"</td>";
				item+="<td>"+goodsForm.price+"</td>";
				item+="<td>"+goodsForm.unit+"</td>";
				item+="<td>"+goodsForm.amount+"</td>";
				item+="<td><label class='checkbox-inline' style='padding-top:0px;margin-right:0px'><input type='checkbox' name='row_checkbox'  id='check"+goodsForm.id+"'><span></span></label></td></tr>";
			});
			$('#modal-data').append(item);
			$('#myGoods').modal('show');
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest);
			console.log(textStatus);
			console.log(errorThrown);
		}
	});
}

function addOtherGoods(){
	//if($('input[name="row_checkbox"]').prop('checked')==true){
	if($("input[name=row_checkbox]:checked").length > 0){
		$("input[name=row_checkbox]:checked").each(function(){ 
		    var id = $(this).attr("id");
		    addGoodsDiv(id.substr(5,id.length), $(this).parent().parent().prev().prev().prev().prev().html(),$(this).parent().parent().prev().prev().prev().html());
		}); 
		
		$('#myGoods').modal('hide');
	}else{
		alert("请选择至少一个商品");
	}
}

/**
 * 结算
 * @returns
 */
function gotoAccount(){
	if($('#shoppingList').find("tr[id^='tr']").length<1){
		alert("购物车为空，请选择商品");
		return;
	}
	var result = new Array();
	$('#shoppingList').find("tr[id^='tr']").each(function(i, val){ 
		var id = $(this).attr("id").substr(2,$(this).attr("id").length);
		var name = $(this).children($("td[name='goodName']")).html();
		var price = $(this).find($("em[name='goodPrice']")).html();
		var amount = $(this).find($("input[name='goodNum']")).val();
		var jsonData = {"id":id, "name":name.substr(5,name.length),"price":price, "amount":amount}
		result[i] = jsonData;
	});
	var goodInfoJson = JSON.stringify(result);
	//alert(goodInfoJson)
	window.location.href=base+"/order/account?goodInfo="+encodeURI(goodInfoJson);
}

//添加购物车
function addGoods(id) {
	addGoodsDiv(id,$("#addId"+id).parent().prev().prev().prev().prev().prev().html(),$("#addId"+id).parent().prev().prev().prev().prev().html());
}

//修改页面内容
function addGoodsDiv(id, name,price){
	var item= "<tr id=tr"+id+"><td name='goodName'>商品名称："+name+" </td><td>商品售价：<em name='goodPrice'>"+price+" </em>￥ </td>";
	item+='<td> <div style="float:left;margin-right:10px;">购买数量：</div>';
	item+='<div class="gw_num" style="float:left; "><em class="jian">-</em><input type="text" value="1" class="num" name="goodNum" readonly="readonly" /><em class="add">+</em></div> '
	item+=" <span class='label label-danger' style='float: right' onclick='delGoods("+id+");'>删除</span>";
	item+="</td></tr>";
	$("#shoppingList").append(item);
	modifyTotalAmount();
	numControl();
}

/**
 * 修改总金额
 * @returns
 */
function modifyTotalAmount(){
	var total = 0;
	$('#shoppingList').find("tr[id^='tr']").each(function(i, val){ 
		var price = $(this).find($("em[name='goodPrice']")).html();
		var amount = $(this).find($("input[name='goodNum']")).val();
		var unitPrice =parseInt(price*amount);
		total = parseInt(total +unitPrice);
	});
	$("#total-amount").text(total);
}

function delGoods(id){
	removeSession(id);
	$("#tr"+id).remove();
	modifyTotalAmount();
}

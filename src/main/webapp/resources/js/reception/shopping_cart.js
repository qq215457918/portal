// base:项目根路径
var base;

$(function() {
	base = $("base").attr('href');
	getGoodsInfo();
	modifyTotalAmount();
	numControl();
});

function checkAll(){  
     $("input[name='checkGroup']:checkbox").each(function() { 
         $(this).prop("checked", true);  
     });  
}

function cancelAll(){  
	 $("input[name='checkGroup']:checkbox").each(function() { 
         $(this).prop("checked", false);  
     }) ;   
}


function getGoodsInfo(){
	var item ="";
	var goodInfo = eval($("#goodInfo").val());
	$.each(goodInfo,function(index, value) {
		item+="<tr><td><label><input name='checkGroup' type='checkbox'id="+value.id+"><span></span></label></td>";
		item+="<td>"+value.name+"</td>";
		item+="<td name='goodPrice'>"+value.price+" </td>";
		item+='<td><div class="gw_num" style="float:left; "><em class="jian">-</em><input type="text" readonly="readonly" value='+value.amount+' class="num" name="goodNum"/><em class="add">+</em></div></td>';
		item+="<td name='totalPrice'></td></tr>";
	});
	$('#cart-good-list').append(item);
}

function getCheckGoods(){
	var result = new Array();
	if($("input[name=checkGroup]:checked").length==0){
		alert("购物车为空，请选择商品");
	}
	$("input[name=checkGroup]:checked").each(function(i, val){
		result[i] = {"id":$(this).attr("id"), "num":$(this).parent().parent().next().next().next().find("input[name='goodNum']").val().trim()};
	});
	return JSON.stringify(result);
}

//结算提交订单
function submitOrder(){
	var submitType;
	var amount;
	var totalAmount = $("#total-amount").text()
	var depositAmount =$("#deposit-amount").val();
	if($("input[name=optionsRadios]:checked").attr("id")=="option-amount"){
		submitType = "whole";
		amount = totalAmount;
	}else{
		submitType = "deposit";
		amount = depositAmount;
	}
	if(totalAmount < depositAmount){
		alert("定金大于付款总额，请重新输入")
	}else{
		var goodInfo = getCheckGoods();
		if(goodInfo !="[]"){
			window.location.href=base+"/order/submit?goodInfo=" + encodeURI(goodInfo) + "&submitType="+submitType+"&amount="+amount;
		}
	}
}

//数字加减控件
function numControl(){
	//加的效果
	$(".add").click(function(){
	var n=$(this).prev().val();
	var num=parseInt(n)+1;
	if(num==0){ return;}
	$(this).prev().val(num);
	//unitPrice
	var price = $(this).parent().parent().prev().text();
	$(this).parent().parent().next().text(parseInt(num*price));
	//total
	modifyTotalAmount();
	});
	//减的效果
	$(".jian").click(function(){
	var n=$(this).next().val();
	var num=parseInt(n)-1;
	if(num==0){ return}
	$(this).next().val(num);
	//unitPrice
	var price = $(this).parent().parent().prev().text();
	$(this).parent().parent().next().text(parseInt(num*price));
	//total
	modifyTotalAmount();
	});
}

/**
 * 修改总金额
 * @returns
 */
function modifyTotalAmount(){
	var total = "0";
	$('#cart-good-list').find("td[name='totalPrice']").each(function(i, val){ 
		var price = $(this).prev().prev().text().trim();
		var unitPrice = parseInt(price) * parseInt($(this).prev().find("input[name='goodNum']").val().trim())
		 $(this).text( unitPrice + " 元");
		total = parseInt(total)+parseInt(unitPrice);
	});
	$("#total-amount").text(total);
}
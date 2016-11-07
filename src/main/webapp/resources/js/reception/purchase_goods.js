// base:项目根路径
var base;
var cartPrefix = "goods-";

$(function() {
	base = $("base").attr('href');
	//加载基本信息
	initGoodsData();
	$('.spinner').spinner(); 
	$('#searchGoods').click(function(){
		if('' == $('#goodInfo').val() &&
			'' == $('#lowPrice').val() &&
			'' == $('#highPrice').val()){
			return;
		}
		$('#goodsInfo').dataTable().fnDraw();
	});
	
	// 查询功能
	$("#queryId").click(function(){
		// now the cart is {"item":"Product 1","price":35.50,"qty":2}
		var cartValue = sessionStorage.getItem( "cart" );
		var cartObj = JSON.parse( cartValue );	
	});
});

function initGoodsData(){
	$('#goodsInfo').dataTable({
		"bSort": false, //是否显示排序
		"bFilter": false, //去掉搜索
		"sPaginationType": "full_numbers", //分页
		"bProcessing": true, //显示正在处理
		"bServerSide": true, // 后台请求
		"bRetrieve": true,
		"sAjaxSource": "order/receive", // 地址
		"aoColumns": [ 
		            {"mData": null, "target": 0},	//序列号   
		            {"mData": "code"},
		            {"mData": "name"},
		            {"mData": "price"},             
		            {"mData": "amount"},            
					{"mData": "trusteeshipFlag"}
		           ],
	       "columnDefs": [
				{
					"targets": [6],
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
									{'name':'goodInfo','value':goodInfo},{'name':'type','value':1},
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

//新增内存
function addSession(key,value){
	sessionStorage.setItem(cartPrefix + key , value );
}

//移除内存
function removeSession(key){
	sessionStorage.removeItem(cartPrefix + key );
}

//销毁内存
function clearSession(key){
	sessionStorage.clear();
}

//添加购物车
function addGoods(id) {
/*	var cart = {
		  item: "Product 1",
		  price: 35.50,
		  qty: 2
		};
	var jsonStr = JSON.stringify( cart );*/
	addSession (id, id);
	addGoodsDiv(id);
}

//修改页面内容
function addGoodsDiv(id){
	var name = $("#addId"+id).parent().prev().prev().prev().prev().html();
	var item="<tr><td><h6>商品名称："+name+"</h6>";
	item+="<h6>购买数量:"+ 1+"</h6>";
	item+="<input type='text' class='spinner'/> ";
	item+=" <span class='label label-danger' style='float: right'>删除</span>";
	item+="</td></tr>";
	$("#shoppingList").append(item);
}

//删除购物车
function deleteGoods(goodsId) {
	removeSession(goodsId);
}

//数值增加
function increaseGoods() {
	
}

//数值减少
function decreaseGoods() {
	
}

//结算
function settleAccounts(){
	
}

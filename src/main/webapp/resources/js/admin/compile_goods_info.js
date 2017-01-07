/**
 * 文件名：compile_goods_info
 * 用途：编辑商品信息页面相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-12-28
 */
// base:项目根路径
var base;
$(function() {
	
	base = $("base").attr('href');
    
    // 商品种类（大）变化事件
    $("#bigSortId").change(function(){
        // 获取商品大种类下的小种类
    	var condition = $(this).val();
    	if(condition == null || condition == '') {
    		// 清空商品种类（小）
            $("#sortId").empty();
            $("#sortId").html('<option value="">请选择</option>');
    	}else {
    		$.ajax({
                "dataType": 'json',
                "type": "POST",
                "url": base + "admin/goodsManage/ajaxGoodsSmallSorts",
                "data": {
                	bigSortId : condition
                },
                "success": function(data){
                    var datas = data;
                    if(data) {
                    	// 清空原下拉框内容
                    	$("#sortId").empty();
                    	var html = '<option value="">请选择</option>';
                        for(var i = 0; i < datas.length; i ++) {
                        	html += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
                        }
                        $("#sortId").html(html);
                    }else {
                    	// 清空原下拉框内容
                    	$("#sortId").empty();
                    	$("#sortId").html('<option value="">请选择</option>');
                    }
                },
                "error": function(data){
                    alert("加载数据失败, 请刷新后重试");
                }
            });
    	}
    });
    
    // 保存信息
    $("#saveGoods").click(function(){
    	var options = {
    		 url: $('#form').attr("action"),
   		     type:"POST",
   		     dataType:"json",
   		     success:function(data){
   		    	 if(data.status == 1) {
   		    		 alert("操作成功");
   		    		 location.href = base + "admin/goodsManage/toGoodsMagene?active=2";
   		    	 }else {
   		    		 if(data.text) {
   		    			 alert(data.text);
   		    		 }else {
   		    			 alert("操作失败,请刷新后重试");
   		    		 }
   		    	 }
   		     },
   		     error:function(err){
   		    	 console.info(err);
   		    	 alert("操作失败,请刷新后重试");
   		     }
   		}
   		$('#form').ajaxForm(options);
    })
	
	$("#back").click(function(){
		window.history.back(-1);
	});
});

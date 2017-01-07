/**
 * 文件名：goods_manage_list
 * 用途：员工管理页面相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-12-26
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
    base = $("base").attr('href');
    
    // 绑定日期事件
    // $('#startCreateDate').datepicker({format: 'yyyy-mm-dd'});
    // $('#endCreateDate').datepicker({format: 'yyyy-mm-dd'});
    
    // 页面初始化加载数据
    initData();
    
    // 查询功能
    $(".btn-success").click(function(){
        // 查询数据
        $('#goodsInfo').dataTable().fnDraw();
    });
    
    // 物品名称输入框回车事件
    $("#name").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#goodsInfo').dataTable().fnDraw();
        }
    });
    // 物品序号输入框回车事件
    $("#code").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#goodsInfo').dataTable().fnDraw();
        }
    });
    
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
        // 查询数据
        $('#goodsInfo').dataTable().fnDraw();
    });
    
    // 商品种类（小）变化事件
    $("#sortId").change(function(){
        // 查询数据
        $('#goodsInfo').dataTable().fnDraw();
    });
    
    // 商品分类变化事件
    $("#type").change(function(){
        // 查询数据
        $('#goodsInfo').dataTable().fnDraw();
    });
    
    // 是否回购变化事件
    $("#repurchaseFlag").change(function(){
        // 查询数据
        $('#goodsInfo').dataTable().fnDraw();
    });
    
    // 进入新增页面
    $("#add").click(function(){
    	location.href = base + "admin/goodsManage/toCompileGoodsInfo";
    });
    
    // 导出功能
    $("#export").click(function(){
    	var name = $('#name').val();
        var code = $('#code').val();
        var bigSortId = $('#bigSortId').val();
        var sortId = $('#sortId').val();
        var type = $('#type').val();
        var repurchaseFlag = $('#repurchaseFlag').val();
		
		if($('#exportExcel')){
			$('#exportExcel').remove();
		}
		
		var exportHtml = '<form id="exportExcel" action="admin/goodsManage/exportGoods" style="display:none;">' +
						'<input type="hidden" name="name" value="' + name + '"/>' +
						'<input type="hidden" name="code" value="' + code + '"/>' +
						'<input type="hidden" name="bigSortId" value="' + bigSortId + '"/>' +
						'<input type="hidden" name="sortId" value="' + sortId + '"/>' +
						'<input type="hidden" name="type" value="' + type + '"/>' +
						'<input type="hidden" name="repurchaseFlag" value="' + repurchaseFlag + '"/>';
					'</form>';
		$('body').append(exportHtml);
		
		$('#exportExcel').submit();
    });

});

// 初始获取数据
function initData() {
    $("#goodsInfo").dataTable({
        "bSort": false, //是否显示排序
        "bFilter": false, //去掉搜索
        "bPaginate": true,     //分页
        "sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
        "bLengthChange" : true,// 每页显示记录数
        "iDisplayLength" : $("select[name='goodsInfo_length']").val(),// 每页显示行数
        "bProcessing": true, //显示正在处理
        "bServerSide": true, // 后台请求
        "bInfo" : true,         // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
        "bRetrieve": true,
        "sAjaxSource": "admin/goodsManage/ajaxGoodsData", // 地址
        "aoColumns": [ 
                    {"mData": null, "target": 0},    //序列号
                    {"mData": "name"},
                    {"mData": "code"},
                    {"mData": "sortName"},
                    {"mData": "viewType"},
                    {"mData": "price"},
                    {"mData": "amount"},
                    {"mData": "viewTrusteeshipFlag"},
                    {"mData": "viewRepurchaseFlag"},
                    {"mData": "viewRepurchaseStarttime"},
                    {"mData": "viewRepurchaseEndtime"},
                    {"mData": null},
                    {"mData": "id", "visible": false}
                   ],
        // 自定义最后一列的字段内容为修改和删除按钮
        "aoColumnDefs": [{"aTargets":[11],"mRender":function(data,type,full){
    		return '<div style="display: inherit;"><button class="btn btn-xs btn-warning update" data_id="'+full.id+'" style="float: left; margin: 0 10px 0 0;">修&nbsp;改</button><button class="btn btn-xs btn-warning delete" data_id="'+full.id+'" data_name="'+full.name+'" style="margin: 0 0 0 0;">删&nbsp;除</button></div>';
        }}],
        "fnServerData": function (sSource, aoData, fnCallback) {
                            var name = $('#name').val();
                            var code = $('#code').val();
                            var bigSortId = $('#bigSortId').val();
                            var sortId = $('#sortId').val();
                            var type = $('#type').val();
                            var repurchaseFlag = $('#repurchaseFlag').val();
                            aoData.push(
                            			{'name':'name','value':name},
                                        {'name':'code','value':code},
                                        {'name':'bigSortId','value':bigSortId},
                                        {'name':'sortId','value':sortId},
                                        {'name':'type','value':type},
                                        {'name':'repurchaseFlag','value':repurchaseFlag}
                                    );
                            $.ajax({
                                "dataType": 'json',
                                "type": "POST",
                                "url": sSource,
                                "data": aoData,
                                "success": function(data){
                                	// 回调函数
                                    fnCallback(data);
                                    // 绑定修改事件
                                    $(".update").click(function(){
                                    	var _this = $(this);
                                    	var id = _this.attr("data_id");
                                    	location.href = base + "admin/goodsManage/toCompileGoodsInfo?id=" + id;
                                    });
                                    
                                    // 绑定删除事件
                                    $(".delete").click(function(){
                                    	var id = $(this).attr("data_id");
                                    	var name = $(this).attr("data_name");
                                    	deleteGoodsInfo(id, name);
                                    });
                                }
                            })
                        }
    });
}

//异步删除物品信息
function deleteGoodsInfo(id, name) {
	if(id) {
		if(confirm("确认删除商品[ " + name + " ]吗?")) {
			$.ajax({
		        "dataType": 'json',
		        "type": "POST",
		        "url": base + "admin/goodsManage/deleteGoodsInfo",
		        "data": {
		        	id : id
		        },
		        "success": function(data){
		            if(data.status == '1') {
		            	alert("操作成功");
		            	location.href = base + "admin/goodsManage/toGoodsMagene?active=2";
		            }else {
		            	if(data.text) {
		            		alert(data.text);
		            	}else {
		            		alert("删除数据失败, 请刷新后重试");
		            	}
		            }
		        },
		        "error": function(data){
		            alert("删除数据失败, 请刷新后重试");
		        }
		    });
		}
	}else {
		alert("获取数据失败, 请刷新后重试");
	}
}


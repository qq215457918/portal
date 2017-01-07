/**
 * 文件名：customer_manage_list
 * 用途：客户基本信息管理页面相关js
 * 作者：Xia ZhengWei
 * 时间: 2017-1-2
 */
// base:项目根路径
var base;
// json:图标容器
var json = {};

$(function() {
    base = $("base").attr('href');
    
    // 页面初始化加载数据
    initData();
    
    // 查询功能
    $(".btn-success").click(function(){
        // 查询数据
        $('#customer').dataTable().fnDraw();
    });
    
    // 客户姓名输入框回车事件
    $("#name").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#customer').dataTable().fnDraw();
        }
    });
    
    // 客户会员卡号输入框回车事件
    $("#vipCard").keyup(function(event){
        if(event.keyCode == 13) {
            // 查询数据
            $('#customer').dataTable().fnDraw();
        }
    });
    
    // 所属机构变化事件
    $("#area").change(function(){
        // 清空查询条件
        $("#name").val('');
        $("#vipCard").val('');
        $("#sex").val('');
        $("#type").val('');
        // 查询数据
        $('#customer').dataTable().fnDraw();
    });
    
    // 导出功能
    $("#export").click(function(){
        alert("暂时还没有做......");
    });

});

// 初始获取数据
function initData() {
    $("#customer").dataTable({
        "bSort": false, //是否显示排序
        "bFilter": false, //去掉搜索
        "bPaginate": true,     //分页
        "sPaginationType": "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
        "bLengthChange" : true,// 每页显示记录数
        "iDisplayLength" : $("select[name='customer_length']").val(),// 每页显示行数
        "bProcessing": true, //显示正在处理
        "bServerSide": true, // 后台请求
        "bInfo" : true,         // Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息
        "bRetrieve": true,
        "sAjaxSource": "admin/customerManage/ajaxCustomerData", // 地址
        "aoColumns": [ 
                    {"mData": null, "target": 0},    //序列号
                    {"mData": "name"},
                    {"mData": "area"},
                    {"mData": "sex"},
                    {"mData": "typeName"},
                    {"mData": "vipCard"},
                    {"mData": null},
                    {"mData": "id", "visible": false}
                   ],
        // 自定义最后一列的字段内容为修改按钮
        "aoColumnDefs": [{"aTargets":[6],"mRender":function(data,type,full){
    		return '<div style="display: inherit;"><button class="btn btn-xs btn-warning update" data_id="'+data.id+'" style="float: left; margin: 0 10px 0 0;">修&nbsp;改</button></div>';
        }}],
        "fnServerData": function (sSource, aoData, fnCallback) {
                            var area = $('#area').val();
                            var name = $('#name').val();
                            var vipCard = $('#vipCard').val();
                            var sex = $('#sex').val();
                            var type = $('#type').val();
                            aoData.push(
                                        {'name':'area','value':area},
                                        {'name':'name','value':name},
                                        {'name':'vipCard','value':vipCard},
                                        {'name':'sex','value':sex},
                                        {'name':'type','value':type}
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
                                    	location.href = base + "admin/customerManage/toCompileCustomer?id=" + id;
                                    });
                                }
                            })
                        }
    });
}

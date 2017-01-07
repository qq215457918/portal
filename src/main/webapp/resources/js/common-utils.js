/**
 * 文件作用：公共方法js
 * 作者：Xia ZhengWei
 * 时间：2016-10-27
 */

/**
 * 方法描述：为导航回显选中状态
 * 使用方法：在自身模块中的head页面底部中追加调用,例：<script type="text/javascript">showTab();</script>
 * 时间：2016-10-27
 * 
 * 修改内容：菜单出现下拉列表情况下回显选中状态
 * 修改人：Xia ZhengWei
 * 时间：2016-11-15
 */
function showTab() {
	// 获取head页面中活动导航隐藏域内容
	var active = $("#active").val();
	// 如果不为空则回显
	if(active != undefined) {
		// 判断是否是下拉样式的菜单
		var prev = $("a[name='tab']").eq(active-1).parent().parent().prev();
		if(prev == undefined) {
			$("a[name='tab']").eq(active-1).attr("class", "current");
		}else {
			if("dropdown" == $(prev).attr("data-toggle")) {
				$(prev).attr("class", "current");
				$("a[name='tab']").eq(active-1).attr("class", "current");
			}else {
				$("a[name='tab']").eq(active-1).attr("class", "current");
			}
		}
	}
}

/**
 * 切换选项,根据父级ID获取子级部门或组数据
 * @param domId 元素ID
 * @param condition 查询条件
 * @author Xia ZhengWei
 * @date 2016年12月25日 下午18:43:54 
 */
function changeDept(domId, condition) {
	$.ajax({
        "dataType": 'json',
        "type": "POST",
        "url": base + "admin/employeeManage/ajaxDeptOrGroupDataByParentsId",
        "data": {
        	parentsId : condition
        },
        "success": function(data){
            var datas = data;
            if(data) {
            	// 清空原下拉框内容
            	$(domId).empty();
            	var html = '<option value="">请选择</option>';
                for(var i = 0; i < datas.length; i ++) {
                	html += '<option value="' + datas[i].id + '">' + datas[i].name + '</option>';
                }
                $(domId).html(html);
            }else {
            	// 清空原下拉框内容
            	$(domId).empty();
            	$(domId).html('<option value="">请选择</option>');
            }
        },
        "error": function(data){
            alert("加载数据失败, 请刷新后重试");
        }
    });
}


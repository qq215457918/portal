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
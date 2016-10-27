/**
 * 文件作用：公共方法js
 * 作者：Xia ZhengWei
 * 时间：2016-10-27
 */

/**
 * 方法描述：为导航回显选中状态
 * 使用方法：在自身模块中的head页面底部中追加调用,例：<script type="text/javascript">showTab();</script>
 * 时间：2016-10-27
 */
function showTab() {
	// 获取head页面中活动导航隐藏域内容
	var active = $("#active").val();
	// 如果不为空则回显
	if(active != undefined) {
		$("a[name='tab']").eq(active-1).attr("class", "current");
	}
}
// base:项目根路径
var base;

$(function() {
	base = $("base").attr('href');
	getGoodsInfo()
});

function getGoodsInfo(){
	var goodInfo = $("goodInfo").value();
	
}
/*<tr>
<td>
<label><input id="checkAll" name="checkAll" type="checkbox"><span></span></label>
	</td>
 	   	<td>
	    ${status.name }
	</td>
  <td>
	    ${status.price }
  </td>
  <td>
      ${status.amount }
  </td>
  <td>
  </td>
</tr>*/
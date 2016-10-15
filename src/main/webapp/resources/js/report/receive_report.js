/**
 * 文件名：receive_report
 * 用途：接待统计图表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-04
 */
// base:项目根路径, title:图表标题, series:图标数据
var base, title, series;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startReportDate').datepicker({format: 'yyyy-mm-dd'});
	$('#endReportDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 图标宽高
	var chart = {
       width: 400,
       height: 400
    };
	// 鼠标放到图标上显示的内容格式
	var tooltip = {
		pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	};
	// 鼠标放到图标上显示的内容样式
	var plotOptions = {
		pie: {
        	allowPointSelect: false,	// 是否可以选中
        	cursor: 'default',
        	dataLabels: {
        		enabled: false,	// 是否启用数据指引
        		style: {
        			color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
        		}
        	},
			showInLegend: true	// 是否显示图例
		}
   	};
	json.chart = chart; 
    json.tooltip = tooltip;  
    json.plotOptions = plotOptions;
    
    // 初始化图表数据
    getData();
    
    // 查询功能
	$(".btn-success").click(function(){
		getData();
	});
	
	// 查看大连详细
	$(".dl").click(function(){
		var startReportDate = $("#startReportDate").val();
		var endReportDate = $("#endReportDate").val();
		location.href = base + "report/toReceiveAreaList?receiverArea=1&startReportDate=" + startReportDate + "&endReportDate=" + endReportDate;
	});
	
	// 查看沈阳详细
	$(".sy").click(function(){
		var startReportDate = $("#startReportDate").val();
		var endReportDate = $("#endReportDate").val();
		location.href = base + "report/toReceiveAreaList?receiverArea=0&startReportDate=" + startReportDate + "&endReportDate=" + endReportDate;
	});
	
});

// 异步获取数据
function getData() {
    $.ajax({
        url:base + "report/ajaxStatistics",
        data:{
        	startReportDate : $("#startReportDate").val(),
        	endReportDate : $("#endReportDate").val()
        },
        type:'POST',
        dataType:'json',
        success:function(data){
        	var dlResult = data.dlResult;
        	var syResult = data.syResult;
        	var customerCounts = data.customerCounts;
        	var dlCounts = data.dlCounts;
        	var syCounts = data.syCounts;
            
            if(dlResult) {
        		var dlResults = new Array();
        		for(var i = 0; i < 5; i ++) {
        			var result = new Array();
        			switch(i) {
        				case 0:
        					result[0] = '成单';
        					result[1] = dlResult.成单;
        					break;
        				case 1:
        					result[0] = '锁定';
        					result[1] = dlResult.锁定;
        					break;
        				case 2:
        					result[0] = '说明会';
        					result[1] = dlResult.说明会;
        					break;
        				case 3:
        					result[0] = '重复登门';
        					result[1] = dlResult.重复登门;
        					break;
        				case 4:
        					result[0] = '新客户';
        					result[1] = dlResult.新客户;
        					break;
        			}
        			dlResults[i] = result;
        		}
        		
        		// 标题
        		title = {
        	      text: '大连接待客户 - ' + dlCounts
        	    };
        		series= [{
        			type: 'pie',
        		    name: '客户数量',
        		    data: dlResults
        		}]; 
        		json.title = title;     
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}else {
        		// 标题
        		title = {
        	      text: '大连接待客户 - ' + dlCounts
        	    };
        		series= [{
        			type: 'pie',
        		    name: '客户数量',
        		    data: dlResults
        		}]; 
        		json.title = title;     
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}
            
        	
        	if(syResult) {
        		var syResults = new Array();
        		for(var i = 0; i < 5; i ++) {
        			var result = new Array();
        			switch(i) {
        				case 0:
        					result[0] = '成单';
        					result[1] = syResult.成单;
        					break;
        				case 1:
        					result[0] = '锁定';
        					result[1] = syResult.锁定;
        					break;
        				case 2:
        					result[0] = '说明会';
        					result[1] = syResult.说明会;
        					break;
        				case 3:
        					result[0] = '重复登门';
        					result[1] = syResult.重复登门;
        					break;
        				case 4:
        					result[0] = '新客户';
        					result[1] = syResult.新客户;
        					break;
        			}
        			syResults[i] = result;
        		}
        		// 标题
        		title = {
        	      text: '沈阳接待客户 - ' + syCounts
        	    };
        		series= [{
        			type: 'pie',
        		    name: '客户数量',
        		    data: syResults
        		}];
        		json.title = title;     
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}else {
        		// 标题
        		title = {
        	      text: '沈阳接待客户 - ' + syCounts
        	    };
        		series= [{
        			type: 'pie',
        		    name: '客户数量',
        		    data: syResults
        		}];
        		json.title = title;     
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}
            
        }
    });
}

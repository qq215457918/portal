/**
 * 文件名：clinch_performance
 * 用途：每日成交业绩相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-31
 */
// base:项目根路径, title:图表标题, series:图标数据
var base, title, series;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startDate').datepicker({format: 'yyyy-mm-dd'});
	$('#endDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 图标宽高
	var chart = {
		type: 'spline',
        width: 1000,
        height: 600
    };
	// 纵坐标
	var yAxis = {
		title: {
            text: '每日业绩（元）'
        },
        labels: {
            formatter: function () {
                return this.value;
            }
        }
	};
	// 鼠标放到图标上显示的内容格式
	var tooltip = {
		crosshairs: true,
        shared: false,
		formatter: function () {
            return '<b>' + this.x + '</b><br/>' +
                this.series.name + ': ' + this.y + '元';
        }
	};
	// 鼠标放到图标上显示的内容样式
	var plotOptions = {
		spline: {
            marker: {
                radius: 3,
                lineColor: '#7cb5ec',
                lineWidth: 1
            }
        },
        series: {  
            cursor: 'default' 
            /*events: {
            	// 为柱形图加点击事件
                click: function(e) {
                	var visitDate = e.point.category;
                	var area = e.point.series.options.area;
                	location.href= base + "report/toVisitEveryDayList?area=" + area + "&visitDate=" + visitDate;
                }
            }  */
        } 
   	};
	json.chart = chart; 
    json.tooltip = tooltip;  
    json.plotOptions = plotOptions;
    json.yAxis = yAxis;
	
    // 初始化图表数据
    getData();
    
    // 查询功能
	$(".btn-success").click(function(){
		getData();
	});
	
});

// 异步获取数据
function getData() {
    $.ajax({
        url:base + "report/ajaxClinchPerforEveryDay",
        data:{
        	startDate : $("#startDate").val(),
        	endDate : $("#endDate").val()
        },
        type:'POST',
        dataType:'json',
        success:function(data){
        	var dates = data.dates;
        	var dlResult = data.dlResult;
        	var syResult = data.syResult;
        	var totalAmounts = data.totalAmounts;
        	var dlAmounts = data.dlAmounts;
        	var syAmounts = data.syAmounts;
            
        	// 横坐标
    		xAxis = {
    			categories: dates
    		};
    		json.xAxis = xAxis;
            if(dlResult) {
        		// 标题
        		title = {
        	      text: '大连业绩 - ' + dlAmounts
        	    };
        		series= [{
        			name: '业绩',
        	        marker: {
        	            symbol: 'circle'
        	        },
        	        area: 1,
        	        data: dlResult
        		}];
        		json.title = title;     
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}else {
        		// 标题
        		title = {
        	      text: '大连业绩 - ' + dlAmounts
        	    };
        		series= [{
        			name: '业绩',
        	        marker: {
        	            symbol: 'circle'
        	        },
        	        area: 1,
        	        data: dlResult
        		}];
        		json.title = title;     
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}
        	
        	if(syResult) {
        		// 标题
        		title = {
        	      text: '沈阳业绩 - ' + syAmounts
        	    };
        		series= [{
        			name: '业绩',
        	        marker: {
        	            symbol: 'circle'
        	        },
        	        area: 1,
        	        data: syResult
        		}];
        		json.title = title;
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}else {
        		// 标题
        		title = {
        	      text: '沈阳业绩 - ' + syAmounts
        	    };
        		series= [{
        			name: '业绩',
        	        marker: {
        	            symbol: 'circle'
        	        },
        	        area: 1,
        	        data: syResult
        		}];
        		json.title = title;
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}
        }
    });
}

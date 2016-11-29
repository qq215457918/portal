/**
 * 文件名：visit_everyday
 * 用途：每日登门图表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-08
 */
// base:项目根路径, title:图表标题, series:图标数据
var base, title, series, xAxis;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startVisitDate').datepicker({format: 'yyyy-mm-dd'});
	$('#endVisitDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 图标宽高
	var chart = {
		type: 'spline',
        width: 1000,
        height: 600
    };
	// 纵坐标
	var yAxis = {
		title: {
            text: '登门数量'
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
                this.series.name + ': ' + this.y + '人';
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
                cursor: 'pointer',  
                events: {
                	// 为柱形图加点击事件
                    click: function(e) {
                    	var visitDate = e.point.category;
                    	var area = e.point.series.options.area;
                    	location.href= base + "report/toVisitEveryDayList?area=" + area + "&visitDate=" + visitDate;
                    }
                }  
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
        url:base + "report/ajaxVisitEveryDay",
        data:{
        	startVisitDate : $("#startVisitDate").val(),
        	endVisitDate : $("#endVisitDate").val()
        },
        type:'POST',
        dataType:'json',
        success:function(data){
        	var dates = data.dates;
        	var dlResult = data.dlResult;
        	var syResult = data.syResult;
        	var customerCounts = data.customerCounts;
        	var dlCounts = data.dlCounts;
        	var syCounts = data.syCounts;
            
        	// 横坐标
    		xAxis = {
    			categories: dates
    		};
    		json.xAxis = xAxis;
            if(dlResult) {
        		// 标题
        		title = {
        	      text: '大连登门数量 - ' + dlCounts
        	    };
        		series= [{
        			name: '登门数量',
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
        	      text: '大连接待客户 - ' + dlCounts
        	    };
        		series= [{
        			name: '登门数量',
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
        	      text: '沈阳接待客户 - ' + syCounts
        	    };
        		series= [{
        			name: '登门数量',
        	        marker: {
        	            symbol: 'circle'
        	        },
        	        area: 0,
        	        data: syResult
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
        			name: '登门数量',
        	        marker: {
        	            symbol: 'circle'
        	        },
        	        area: 0,
        	        data: syResult
        		}];
        		json.title = title;
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}
        }
    });
}

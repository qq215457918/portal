/**
 * 文件名：visit_everyday
 * 用途：每日登门图表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-10-08
 */
// base:项目根路径, title:图表标题, series:图标数据
var base, title, series;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startDate').datepicker();
	
	// 图标宽高
	var chart = {
		type: 'column',
        width: 400,
        height: 400
    };
	// 横坐标
	var xAxis = {
       categories: ['周一','周二','周三','周四','周五','周六','周日']
	};
	// 纵坐标
	var yAxis = {
        min: 0,
        title: {
        	text: '每日业绩（元）'         
        }
	};
	var legend = {
        enabled: false
    };
	// 鼠标放到图标上显示的内容格式
	var tooltip = {
			formatter: function () {
                return '<b>' + this.x + '</b><br/>' +
                    this.series.name + ': ' + this.y + '元';
            }
	};
	// 鼠标放到图标上显示的内容样式
	var plotOptions = {
			column: {
                stacking: 'normal'
            },
            series: {  
                cursor: 'default'  
                /*
                 * 柱形点击事件
                 * events: {
                	// 为柱形图加点击事件
                    click: function(e) {
                    	var visitDate = e.point.value;
                    	var area = e.point.area;
                    	location.href= base + "report/toVisitEveryDayList?area=" + area + "&visitDate=" + visitDate;
                    }
                } */ 
            }  
   	};
	json.chart = chart; 
    json.tooltip = tooltip;  
    json.plotOptions = plotOptions;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.legend = legend;
    
    // 初始化图表数据
    getData();
    
    // 查询功能
	$(".btn-success").click(function(){
		getData();
	});
	
});

// 根据数字判断横坐标轴显示名称
function getText(index) {
	var texts = '';
	switch(index) {
		case 0:
			texts = 'Monday';
			break;
		case 1:
			texts = 'Tuesday';
			break;
		case 2:
			texts = 'Wednesday';
			break;
		case 3:
			texts = 'Thursday';
			break;
		case 4:
			texts = 'Friday';
			break;
		case 5:
			texts = 'Saturday';
			break;
		case 6:
			texts = 'Sunday';
			break;
	}
	return texts;
}

//根据内容匹配返回JSON数据集中的数据value值
function getValue(dataSource, text) {
	var value = '';
	if(text == "Monday") {
		value = dataSource.Monday;
	}else if(text == "Tuesday") {
		value = dataSource.Tuesday;
	}else if(text == "Wednesday") {
		value = dataSource.Wednesday;
	}else if(text == "Thursday") {
		value = dataSource.Thursday;
	}else if(text == "Friday") {
		value = dataSource.Friday;
	}else if(text == "Saturday") {
		value = dataSource.Saturday;
	}else if(text == "Sunday") {
		value = dataSource.Sunday;
	}
	return value;
}

// 内容匹配返回JSON数据集中的日期value值
function getDateValue(dataSource, text) {
	var value = '';
	if(text == "Monday") {
		value = dataSource.Monday_Day;
	}else if(text == "Tuesday") {
		value = dataSource.Tuesday_Day;
	}else if(text == "Wednesday") {
		value = dataSource.Wednesday_Day;
	}else if(text == "Thursday") {
		value = dataSource.Thursday_Day;
	}else if(text == "Friday") {
		value = dataSource.Friday_Day;
	}else if(text == "Saturday") {
		value = dataSource.Saturday_Day;
	}else if(text == "Sunday") {
		value = dataSource.Sunday_Day;
	}
	return value;
}

// 异步获取数据
function getData() {
    $.ajax({
        url:base + "report/ajaxClinchPerforEveryDay",
        data:{
        	startDate : $("#startDate").val()
        },
        type:'POST',
        dataType:'json',
        success:function(data){
        	var dlResult = data.dlResult;
        	var syResult = data.syResult;
        	var totalAmounts = data.totalAmounts;
        	var dlAmounts = data.dlAmounts;
        	var syAmounts = data.syAmounts;
            
            if(dlResult) {
        		var dlResults = new Array();
        		for(var i = 0; i < 7; i ++) {
        			var texts = getText(i);
        			var datas = {
            			name : texts,
            			y : getValue(dlResult, texts),
            			value : getDateValue(dlResult, texts),
            			area : 1
            		};
        			dlResults[i] = datas;
        		}
        		// 标题
        		title = {
        	      text: '大连业绩 - ' + dlAmounts
        	    };
        		series= [{
        			type: 'column',
        		    name: '业绩',
        		    data: dlResults
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
        			type: 'column',
        		    name: '业绩',
        		    data: dlResults
        		}]; 
        		json.title = title;     
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}
        	
        	if(syResult) {
        		var syResults = new Array();
        		for(var i = 0; i < 7; i ++) {
        			var texts = getText(i);
        			var datas = {
            			name : texts,
            			y : getValue(syResult, texts),
            			value : getDateValue(dlResult, texts),
            			area : 0
            		};
        			syResults[i] = datas;
        		}
        		// 标题
        		title = {
        	      text: '沈阳业绩 - ' + syAmounts
        	    };
        		series= [{
        			type: 'column',
        		    name: '业绩',
        		    data: syResults
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
        			type: 'column',
        		    name: '业绩',
        		    data: syResults
        		}];
        		json.title = title;
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}
        }
    });
}

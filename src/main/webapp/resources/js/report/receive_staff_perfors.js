/**
 * 文件名：receive_staff_perfors
 * 用途：业务员业绩图表页相关js
 * 作者：Xia ZhengWei
 * 时间: 2016-11-02
 */
// base:项目根路径, title:图表标题, series:图标数据
var base, title, xAxis, series;
// json:图标容器
var json = {};

$(function() {
	base = $("base").attr('href');
	
	// 绑定日期事件
	$('#startDate').datepicker({format: 'yyyy-mm-dd'});
	$('#endDate').datepicker({format: 'yyyy-mm-dd'});
	
	// 图标宽高
	var chart = {
		type: 'column',
        width: 800,
        height: 400
    };
	
	// 纵坐标
	var yAxis = {
        min: 0,
        title: {
        	text: '业绩（元）'         
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
                cursor: 'default',  
                /*events: {
                	// 为柱形图加点击事件
                    click: function(e) {
                    	var visitDate = e.point.value;
                    	var area = e.point.area;
                    	location.href= base + "report/toVisitEveryDayList?area=" + area + "&visitDate=" + visitDate;
                    }
                }  */
            }  
   	};
	json.chart = chart; 
    json.tooltip = tooltip;  
    json.plotOptions = plotOptions;
    json.yAxis = yAxis;
    json.legend = legend;
    
    // 初始化图表数据
    getData();
    
    // 业务员输入框回车事件
	$("#staffName").keyup(function(event){
		if(event.keyCode == 13) {
			// 查询数据
			getData();
		}
	});
    
    // 查询功能
	$(".btn-success").click(function(){
		getData();
	});
	
});

// 异步获取数据
function getData() {
    $.ajax({
        url:base + "report/ajaxStaffPerfors",
        data:{
        	positionType : "2",	/*业务员职位列表*/
        	staffName : $("#staffName").val(),
        	startDate : $("#startDate").val(),
        	endDate : $("#endDate").val()
        },
        type:'POST',
        dataType:'json',
        success:function(data){
        	var dlResult = data.dlResult;
        	var syResult = data.syResult;
        	var dlStaffNames = data.dlStaffNames;
        	var syStaffNames = data.syStaffNames;
            
            if(dlResult) {
        		var dlResults = new Array();
        		for(var i = 0; i < dlStaffNames.length; i ++) {
        			var texts = dlStaffNames[i];
        			var datas = {
            			name : texts,
            			y : dlResult[texts],
            			value : texts
            		};
        			dlResults[i] = datas;
        		}
        		// 标题
        		title = {
        	      text: '大连业务员业绩'
        	    };
        		// 横坐标
        		xAxis = {
        	       categories: dlStaffNames
        		};
        		series= [{
        			type: 'column',
        		    name: '业绩',
        		    data: dlResults
        		}]; 
        		json.title = title;
        		json.xAxis = xAxis;
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}else {
        		// 标题
        		title = {
        	      text: '大连业务员业绩'
        	    };
        		// 横坐标
        		xAxis = {
        	       categories: dlStaffNames
        		};
        		series= [{
        			type: 'column',
        		    name: '业绩',
        		    data: dlResults
        		}]; 
        		json.title = title;  
        		json.xAxis = xAxis;
        	    json.series = series;
        	    $('#dlContainer').highcharts(json);
        	}
        	
        	if(syResult) {
        		var syResults = new Array();
        		for(var i = 0; i < syStaffNames.length; i ++) {
        			var texts = syStaffNames[i];
        			var datas = {
            			name : texts,
            			y : syResult[texts],
            			value : texts
            		};
        			syResults[i] = datas;
        		}
        		// 标题
        		title = {
        	      text: '沈阳业务员业绩'
        	    };
        		// 横坐标
        		xAxis = {
        	       categories: syStaffNames
        		};
        		series= [{
        			type: 'column',
        		    name: '业绩',
        		    data: syResults
        		}];
        		json.title = title;
        		json.xAxis = xAxis;
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}else {
        		// 标题
        		title = {
        	      text: '沈阳业务员业绩'
        	    };
        		// 横坐标
        		xAxis = {
        	       categories: syStaffNames
        		};
        		series= [{
        			type: 'column',
        		    name: '业绩',
        		    data: syResults
        		}];
        		json.title = title;
        		json.xAxis = xAxis;
        	    json.series = series;
        		$('#syContainer').highcharts(json);
        	}
        }
    });
}

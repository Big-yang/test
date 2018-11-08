function drawdanzhutu(param) {
	var config = {
			divId : "",
			y:"",
			x:"",
			title:"",
			data:{},
			width:'100%',
			height:'260'
	};
	$.extend(config,param,true);
	var maxValueFlage = config.data[0].value;
//	var minValue = config.data[0].value;
	for(var i=0;i<config.data.length;i++){
		if(config.data[i].value>maxValueFlage){
			maxValueFlage = config.data[i].value;
		}
	}
	var mychart13 = new FusionCharts("/A33Web/script/chart/Column2D.swf", "do3"+Math.floor(Math.random()*100+1), config.width,
			 config.height, "0", "0");
	var chart = {
//			"yAxisMaxValue" : Math.ceil(maxValueFlage),
			"yaxisname" : config.y,
			"xaxisname" : config.x,
			"caption" : config.title,
			"numbersuffix" : "",
			"bgcolor" : "FFFFFF,FFFFFF",
			"showborder" : "0",
			"paletteColors":"CDF090",
			"alpha":"0",
			"palette": "2",
			"showvalues": "0",
			"decimals": "3",
			"formatnumberscale": "0.00",
			"adjustDiv ": true,
			"outCnvBaseFontSize":"12"
	};
	if(maxValueFlage==0){
		chart.yAxisMaxValue = '10';
	}
	mychart13.setJSONData({
		"chart" : chart,
		"data" : config.data,
		"styles": {
		    "definition": [
		      {
		        "name": "mAnim",
		        "type": "animation",
		        "param": "_yScale",
		        "start": "0",
		        "duration": "1"
		      }
		    ],
		    "application": [
		      {
		        "toobject": "VLINES",
		        "styles": "mAnim"
		      }
		    ]
		  }
	});
	mychart13.render(config.divId);
//	 parent.autoHeight();

}

/**
 * 点击查询按钮执行方法，查询所有图表数据
 */
function search() {
	var url = ctx + "/well.do?method=getCharData";
	var data = {};
	
	//	data.orgPageA11 = orgPageA11;
	var successfun = function(data) {
		$(".report_manage_con_chart_two_c").show();
		
		drawdanzhutu(
				{
					divId : "chart1",
					y : "单井日产量",
					x : "井名称",
					data : data
				}, {});
		
	};
	var err = function() {
	};
	callajax(url, data, successfun, err);
}
//ajax
function callajax(url, data_, callBackFun, errorFun) {
	$.ajax({
				type : "POST",
				data : data_,
				url : url,
				dataType : "json",
				beforeSend : function(XMLHttpRequest) {

				},
				error : function(XMLHttpRequest, mes, exc) {
					if (errorFun != null) {
						errorFun();
					}
				},
				success : function(data_, textStatus) {
					callBackFun(data_);
				},
				complete : function(XMLHttpRequest, textStatus) {

				}
			});
}

function exportExcel(chartIndex) {
	var url = ctx + "/well.do?method=exportExcel";
	$("#fileName").val(encodeURIComponent("某作业区产量对比"));
	$("#eexcel").attr("action",url);
	$("#eexcel").attr("method","post");
	$("#eexcel").submit();
	
}
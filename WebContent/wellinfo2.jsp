<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var='ctx' value=" ${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv=X-UA-Compatible content=IE=8 />
<%@ taglib prefix='spring' uri='/WEB-INF/spring.tld'%>
<title>Well Search</title>
<script type='text/javascript' src="${ctx}/script/jquery.min.js"></script>
<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src="${ctx}/script/wellinfo2.js"></script>
<script type="text/javascript" src="${ctx}/script/chart/FusionCharts.js"></script>
<script language="javascript">
	var ctx='${ctx}';
	function getWellInfo(){
		var url = ctx+"/well.do?method=getjsonwellinfo";
		$.ajax({
			url : url,
			dataType : "json",
			success : function(data) {
				if(data==null) return;
				$("#me").html("");
				var html = "<table><th>井名</th><th>井ID</th><th>产量</th>";
				for(var i=0; i<data.length; i++){
					html += "<tr><td>"+data[i].name+"</td>" +  "<td>"+data[i].id+"</td>"+ "<td>"+data[i].value+"</td></tr>";
				}
				html += "</table>";
				$("#me").html( html );
			},
			err : function() {
				alert("get data wrong");
			}
		});
	}
</script>
</head>
<body>
<form id="eexcel">
	<input type="hidden" id="fileName" name="fileName"/>
</form>
	<a>刷新局部信息</a><button onclick="getWellInfo()">GO!!!</button><br/>
	<div><a>-----------------------------------</a>
		<div id="me">
		</div>
	</div>
	<div class="report_manage_con_chart" style="display: block;">
				<div class="report_manage_con_chart_one">
					<div class="report_manage_con_chart_two_time"
						style="height: 180px;">
						<div class="ow_main_opt_print3">
							<a style="margin-right: 10px;height:29px;">产量柱状图</a><button style="line-height: 24px;" onclick="search()">GO!!!</button><a class="" id="iot_excel" href="javascript:void(0);"
								onclick="exportExcel(1)" style="float:right;margin-right: 10px;height:29px;">导出Excel</a>
						</div>
					</div>
					<div class="report_manage_con_chart_two_c" id="chart1"
						style="margin-top: -150px;display:none;"></div>
				</div>
	</div>
</body>
</html>

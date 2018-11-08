<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
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
<title>Well Info</title>
</head>
<body onload="onload()">
	<table><th>井名</th><th>井ID</th><th>产量</th>
	<c:forEach  items="${wellinfo}" var="item">
		<tr>
			<td>${item.name}</td>
			<td>${item.id}</td>
			<td>${item.dailyProd}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>

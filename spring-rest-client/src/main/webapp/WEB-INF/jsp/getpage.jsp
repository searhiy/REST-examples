<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Get Item</h1>

<c:if test="${empty item}">
	No records found!
</c:if>

<c:if test="${!empty item}">
	<table style="border: 1px solid #333">
		<tr>
		<td style="width: 100px">Id</td>
		<td>${item.id}</td>
		</tr>
		
		<tr>
		<td>First Name</td>
		<td>${item.name}</td>
		</tr>
		
		<tr>
		<td>Last Name</td>
		<td>${item.param1}</td>
		</tr>
		
		<tr>
		<td>Money</td>
		<td>${item.param2}</td>
		</tr>
	</table>
</c:if>

</body>
</html>
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
<h1>Items</h1>

<c:url var="addUrl" value="/items/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
	<thead style="background:greenyellow">
		<tr>
			<th>Name</th>
			<th>Param1</th>
			<th>Param2</th>
			<th colspan="4"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${items}" var="item">
			<c:url var="editUrl" value="/items/update?id=${item.id}" />
			<c:url var="deleteUrl" value="/items/delete?id=${item.id}" />
			<c:url var="getUrl" value="/items/get?id=${item.id}" />
		<tr>
			<td><c:out value="${item.name}" /></td>
			<td><c:out value="${item.param1}" /></td>
			<td><c:out value="${item.param2}" /></td>
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
			<td><a href="${getUrl}">Get</a></td>
		</tr>
	</c:forEach>
	<a href="${addUrl}">Add Item</a>
	</tbody>
</table>

<c:if test="${empty items}">
	There are currently no items in the list. <a href="${addUrl}">Add</a> a item.
</c:if>

</body>
</html>
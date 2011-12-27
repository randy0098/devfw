<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>短信记录查询</title>
</head>
<body>
	<a href="/devfw/message/message_insert.jsp" style="float: right">增加</a>
	<table width="100%" border="1">
		<tr><th>id</th><th>sender</th><th>receiver</th><th>content</th><th>msg_time</th><th>操作</th></tr>
		<c:forEach var="message" items="${messages}">
			<tr>
				<td>${message.id}</td>
				<td>${message.sender}</td>
				<td>${message.receiver}</td>
				<td>${message.content}</td>
				<td>${message.msg_time}</td>
				<td>
					<a href="/devfw/message/message_selectOne.do?id=${message.id}"/>修改</a>
					<a href="message_insert.jsp">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
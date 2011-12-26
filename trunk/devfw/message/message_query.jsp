<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr><th>ID</th><th>SENDER</th><th>RECEIVER</th><th>CONTENT</th><th>MSG_TIME</th></tr>
		<tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td></tr>
		
		<c:forEach var="message" items="${messages}">
			<c:out value="${message.content}"/><br/>
		</c:forEach>
	</table>
</body>
</html>
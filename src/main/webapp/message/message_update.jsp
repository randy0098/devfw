<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改短信信息</title>
</head>
<body>
	<form action="message.do?method=message_update" method="post">
		<table border="1" width="100%">
			<tr>
				<td width="30%">发送者：</td>
				<td width="70%"><input type="text" name="sender" value="${messageVO.sender}"></td>
			</tr>
			<tr>
				<td>接收者：</td>
				<td><input type="text" name="receiver" value="${messageVO.receiver}"></td>
			</tr>	
			<tr>
				<td>内容：</td>
				<td><textarea rows="3" cols="30" name="content" >${messageVO.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input type="submit" value="保存"></td>
			</tr>	
		</table>
		<input type="hidden" name="id" value="${messageVO.id}">
		<input type="hidden" name="msg_time" value="${messageVO.msg_time}">	
	</form>
</body>
</html>
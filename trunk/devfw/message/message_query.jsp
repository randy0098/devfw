<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>短信记录查询</title>
</head>
<script type="text/javascript">
	//删除时进行确认
	function deleteCheck(){
		return confirm("确定删除此记录？");
	}
	
	//转到第几页
	function goToPage(){
		var pageIndex = document.getElementById("pageIndex").value;
		window.location = "/devfw/message/message_page.do?action=go&currentPageIndex="+pageIndex;
	}
	
	//转向的页面数只能为数字
	function checkNum(e){
		if (!/^\d+$/.test(e.value))
	    {
	        e.value = /^\d+/.exec(e.value);
	    }
	    return false;
	}
</script>
<body>
	<a href="/devfw/message/message_insert.jsp" style="float: right">增加</a>
	<table width="100%" border="1">
		<tr><th>id</th><th>sender</th><th>receiver</th><th>content</th><th>msg_time</th><th>操作</th></tr>
		<c:forEach var="message" items="${page.records}">
			<tr>
				<td>${message.id}</td>
				<td>${message.sender}</td>
				<td>${message.receiver}</td>
				<td>${message.content}</td>
				<td>${message.msg_time}</td>
				<td>
					<a href="/devfw/message/message_selectOne.do?id=${message.id}"/>修改</a>
					<a href="/devfw/message/message_delete.do?id=${message.id}" onclick="return confirm('确定删除此记录？')">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6">
				<a href="/devfw/message/message_page.do?action=goToFirst">首页</a>
				<a href="/devfw/message/message_page.do?action=goToLast">尾页</a>
				<a href="/devfw/message/message_page.do?action=back&currentPageIndex=${page.currentPageIndex}">上一页</a>
				<a href="/devfw/message/message_page.do?action=next&currentPageIndex=${page.currentPageIndex}">下一页</a>
				转到第<input type="text" id="pageIndex">页
				<input type="button" value="go" onclick="goToPage()">
				每页显示${page.pageRecordNum}条
				第${page.currentPageIndex}/${page.totalPage}页			
			</td>
		</tr>
	</table>
</body>
</html>
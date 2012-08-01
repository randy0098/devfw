<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%> 
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
	
	//分页跳转
	function paging(action){
		var url = "message.do?method=message_page&action=";
		//首页
		if(action == "goToFirst"){
			url = url + "goToFirst";
		}
		//尾页
		else if(action == "goToLast"){
			url = url + "goToLast";
		}
		//上一页
		else if(action == "back"){
			url = url + "back&currentPageIndex="+${page.currentPageIndex};
		}
		//下一页
		else if(action == "next"){
			url = url + "next&currentPageIndex="+${page.currentPageIndex};
		}
		//转到第几页
		else if(action == "go"){
			var pageIndex = document.getElementById("pageIndex").value;
			if(isNaN(parseInt(pageIndex)) == true){
				alert("请输入正确的页数！");
				return;
			}else{
				url = url + "go&currentPageIndex="+pageIndex;
			}
		}
		f1.action = url;
		f1.submit();
	}
</script>
<body>
	<form action="message.do?method=message_query" name="f1" method="post" >
		<table width="100%" >
			<tr>
				<td>sender：</td>
				<td>
					<input type="text" name="sender" value="${param.sender}">
					<input type="hidden" name="option" value="sender-eq">
				</td>
				<td>receiver：</td>
				<td>
					<input type="text" name="receiver" value="${param.receiver}">
					<input type="hidden" name="option" value="receiver-like">
				</td>
			</tr>
			<tr>
				<td>msg_time：</td>
				<td>
					<input type="text" name="mintime" value="${param.mintime}">至：<input type="text" name="maxtime" value="${param.maxtime}">
					<input type="hidden" name="option" value="mintime-ge">
					<input type="hidden" name="option" value="maxtime-le">
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center" >
					<input type="submit" value="查询">
					<input type="reset" value="重填">
				</td>
			</tr>
		</table>
		
		<a href="message/message_insert.jsp" style="float: right">增加</a>
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
						<a href="message.do?method=message_selectOne&id=${message.id}"/>修改</a>
						<a href="message.do?method=message_delete&id=${message.id}" onclick="return confirm('确定删除此记录？')">删除</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">
					<a href="" onclick="paging('goToFirst');return false">首页</a>
					<a href="" onclick="paging('goToLast');return false">尾页</a>
					<a href="" onclick="paging('back');return false">上一页</a>
					<a href="" onclick="paging('next');return false">下一页</a>
					转到第<input type="text" id="pageIndex" onkeypress="return event.keyCode>=48&&event.keyCode<=57" onpaste="return !clipboardData.getData('text').match(/\D/)" ondragenter="return false" style="ime-mode:Disabled">页
					<input type="button" value="go" onclick="paging('go')">
					每页显示${page.pageRecordNum}条
					第${page.currentPageIndex}/${page.totalPage}页			
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
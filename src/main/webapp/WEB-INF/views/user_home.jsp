<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%String id = (String)session.getAttribute("id");
String name = (String)session.getAttribute("name");
%>
<script src="js/list.js"></script>
<title>환영합니다! ${name } 님!</title>
</head>
<body>
	<h1>${name } (${id })의 방명록</h1>
	<br> 방명록 전체 수 : ${count }
	<br>
	<br>
	
	<c:forEach items="${list}" var="guestbook">
	
	${guestbook.userId }(${guestbook.name })<br>
	${guestbook.content }<br>
	${guestbook.regdate }<br>
<br>
	</c:forEach>
	<br>

	<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
		<a href="list?start=${pageIndex}">${status.index +1 }</a>&nbsp; &nbsp;
</c:forEach>

	<br>
	<br>
	${guestbook.userId }
	<form method="post" action="write?user=${user}">
		<textarea id="content" name="content" cols="60" rows="6" minlength="1"></textarea>
		<br> <input id="button" type="submit" value="등록" disabled="true">
	</form>
	<a href="logout">로그아웃</a>
</body>
</html>
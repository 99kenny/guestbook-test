<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/list.js"></script>
<title>방명록 목록</title>
</head>
<body>

	<h1>방명록</h1>
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
	<form method="post" action="write">
		<textarea id="content" name="content" cols="60" rows="6" minlength="1"></textarea>
		<br> <input id="button" type="submit" value="등록" disabled="true">
	</form>
	<a href="logout">로그아웃</a>
</body>
</html>
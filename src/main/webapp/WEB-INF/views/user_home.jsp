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

<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/user_home.css">
<title>환영합니다! ${name } 님!</title>
</head>
<body>
	<main>
		<aside id="profile">
			<h1>유기농바나나칩</h1>
			<img>프로필 사진</img>
			<p>유기농바나나칩 맛있쪙</p>	
		</aside>
		
		<article id="content">
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
		</article>
		
		<nav id="nav">
			<a>방명록</a>
			<a>게시판</a>
			<a>친구목록</a>
			<a>마이페이지</a>
		</nav>
		
	</main>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="js/list.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" accept-charset="utf-8" name="login_info" method="post">
		아이디 : <input type ="text" name="id" maxlength="12"/><br>
		비밀번호: <input type ="password" name="password" maxlength="20"/><br>
		<input type="submit"/>
		<a href="./signup">회원가입</a>
	</form>
</body>
</html>
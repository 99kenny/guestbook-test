<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="signup" accept-charset="utf-8" name="signup_info" method="post">
		아이디(8-12자) : <input type ="text" name="id" minlength="8" maxlength="12"/> <br>
		비밀번호(8-20자) : <input type ="password" name="password" minlength="8" maxlength="20"/><br>
		비밀번호 확인 : <input type="password" name="pwd_confirm" minlength="8" maxlength="12"/><h4 id="pwd_confirm"></h4>
		이름 : <input type="text" name="name" minlength="2" maxlength="10"/><br>
		이메일 : <input type="email" name="email" minlength="10" maxlength="32"/><br> 
		<input type="submit" value="회원가입"/>
	</form>
</body>
</html>
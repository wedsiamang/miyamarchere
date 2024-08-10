<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Admin"%>
<!DOCTYPE html>
<html>
<head>
<%
Admin adLogin = (Admin) session.getAttribute("adLogin");
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/AdminLoginServlet" method="post">
		<div class="mb-3">
			<label for="adminName" class="form-label">adminName</label> <input
				type="text" class="form-control" name="adminName">
		</div>
		<div class="mb-3">
			<label for="adminPass" class="form-label">パスワード</label> <input
				type="password" class="form-control" name="adminPass">
		</div>

		<button type="submit" class="btn new-btn new-btn:hover">ログイン</button>
	</form>
	<br>
	<a class="btn btn--yellow btn--cubic" href="/IndexServlet">TOPへ</a>
</body>
</html>
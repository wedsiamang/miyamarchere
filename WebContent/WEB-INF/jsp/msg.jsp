<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList,pack.CheckParameter"%>
<%
String Msg = (String) request.getAttribute("Msg");
ArrayList<String> err = (ArrayList<String>) request.getAttribute("err");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="css/msg.css">
</head>
<body>
	<div class="msg">
		<%
		if (Msg != null) {
		%>
		<%=Msg%>
		<%
		}
		%>
		<%
		for (String er : err) {
		%>
		<%=er%>
		<br>
		<%
		}
		%>
	</div>
</body>
</html>
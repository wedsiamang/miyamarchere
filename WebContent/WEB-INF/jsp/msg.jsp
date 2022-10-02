<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String Msg=(String)request.getAttribute("Msg");
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/msg.css">
</head>

<body>
<div class="msg">
	<% if (Msg != null) { %>
						<%= Msg %>
					<% } %>

</div>
</body>
</html>
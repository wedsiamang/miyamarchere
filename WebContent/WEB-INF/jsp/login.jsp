<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import ="model.Shop,java.util.List" %>
   <%
   Shop login = (Shop)session.getAttribute("login");
   List<Shop>shopList=(List<Shop>)request.getAttribute("shopList");
   %>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/halfHeader.css">
	<link href= "https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel= "stylesheet" integrity= "sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">"
	<title>ログイン</title>
</head>
<body>
	<div class="headbox">
		<div class="visual">
			<h2>ミヤマルシェア</h2>
		</div>
	</div>
	<div class="footerFixed">
  		<div class="main">
			<div class="mx-auto" style="width: 300px;">
				<br><br><br>
				<h1 class="mb-3" style="text-align: center">Login</h1>
				<br>
				<form action="/miyama1/LoginServlet" method="post">
					<div class="mb-3">
						<label for="shop_name" class="form-label">店舗名</label>
						<select class="list" class="form-control"  name="shop_name"placeholder="店舗名を選択してください">
							<option value="選択してください">選択してください</option>
							<%for(Shop s:shopList){ %>
								<option value="<%=s.getShop_name()%>"><%=s.getShop_name() %></option>
							<%} %>
						</select>		
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">パスワード</label>
						<input type="password" class="form-control"  name="password" required>
					</div>
					<jsp:include page="/WEB-INF/jsp/msg.jsp" />
					<button type="submit" class= "btn new-btn new-btn:hover">ログイン</button>
				</form>
			<br><a  class="btn btn--yellow btn--cubic"href="/miyama1/RegisterShopServlet">新規登録画面へ</a> 
			<br><a  class="btn btn--yellow btn--cubic"href="/miyama1/IndexServlet">TOPへ</a> 
			</div>
		</div>
	</div>
</body>
</html>